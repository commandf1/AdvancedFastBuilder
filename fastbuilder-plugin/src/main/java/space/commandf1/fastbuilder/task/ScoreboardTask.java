package space.commandf1.fastbuilder.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;
import space.commandf1.fastbuilder.config.configs.SettingsConfig;
import space.commandf1.fastbuilder.ingame.InGame;
import space.commandf1.fastbuilder.ingame.InGameManager;
import space.commandf1.fastbuilder.playerdata.PlayerData;
import space.commandf1.fastbuilder.playerdata.PlayerDataManager;
import space.commandf1.fastbuilder.utils.ScoreboardUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScoreboardTask extends BukkitRunnable {

    private final String scoreboardTitle;
    private final List<String> scoreboardLines;

    public ScoreboardTask() {
        if (SettingsConfig.ENABLE_SCOREBOARD.getValue()) {
            this.scoreboardTitle = SettingsConfig.SCOREBOARD_TITLE.getValue();
            this.scoreboardLines = SettingsConfig.getScoreboard().getValue();
            this.runTaskTimer(AdvancedFastBuilderPlugin.getInstance(), 0, 1);
        } else {
            this.scoreboardTitle = null;
            this.scoreboardLines = null;
        }
    }

    public static void createScoreboardForPlayer(Player player, String title, List<String> lines, Long first, Long second, Long third, Long time, Long playerBest) {
        if (title == null || lines == null || player == null || playerBest == null) {
            return;
        }

        List<String> scoreboardLines = new ArrayList<>();
        for (String line : lines) {
            String score = line
                    .replace("%player%", player.getName())
                    .replace("%player_best%", playerBest == -1 ? "null" : String.valueOf(playerBest))
                    .replace("%leader_first%", first == null || first == -1 ? "null" : String.valueOf(first))
                    .replace("%leader_second%", second == null || second == -1 ? "null" : String.valueOf(second))
                    .replace("%leader_third%", third == null || third == -1 ? "null" : String.valueOf(third))
                    .replace("%time%", time == null ? "null" : String.valueOf(time));
            scoreboardLines.add(score);
        }

        ScoreboardUtils.createScoreboard(player, title, scoreboardLines);
    }

    @Override
    public void run() {
        if (scoreboardTitle == null || scoreboardLines == null) {
            return;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            InGame inGame = InGameManager.getInGame(player);
            if (inGame != null) {
                PlayerData playerData = PlayerDataManager.getPlayerDataByPlayer(player);
                Long playerBest = playerData == null ? -1 : playerData.getBest();

                List<Long> leaderboards = PlayerDataManager.getPlayerdatas().stream()
                        .filter(Objects::nonNull)
                        .map(PlayerData::getBest)
                        .sorted(Collections.reverseOrder())
                        .limit(3)
                        .collect(Collectors.toList());

                Long first = leaderboards.size() >= 1 ? leaderboards.get(0) : null;
                Long second = leaderboards.size() >= 2 ? leaderboards.get(1) : null;
                Long third = leaderboards.size() >= 3 ? leaderboards.get(2) : null;

                Long time = Objects.requireNonNull(GameTask.getGameTaskByInGame(inGame)).getElapsedTime();

                createScoreboardForPlayer(player, scoreboardTitle, scoreboardLines, first, second, third, time, playerBest);
            }
        }
    }
}