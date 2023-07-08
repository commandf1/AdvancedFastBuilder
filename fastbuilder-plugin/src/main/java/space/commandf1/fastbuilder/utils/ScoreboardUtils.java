package space.commandf1.fastbuilder.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;

public class ScoreboardUtils {

    public static void createScoreboard(Player player, String title, List<String> scores) {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("sidebar", "dummy");
        objective.setDisplayName(title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for (int i = 0; i < scores.size(); i++) {
            String score = scores.get(i);
            objective.getScore(score).setScore(scores.size() - i);
        }

        player.setScoreboard(scoreboard);
    }

    public static void disableScoreboard(Player player) {
        for (Objective objective : player.getScoreboard().getObjectives()) {
            objective.unregister();
        }
    }
}
