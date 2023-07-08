package space.commandf1.fastbuilder.task;

import me.jumper251.replay.api.ReplayAPI;
import me.jumper251.replay.replaysystem.Replay;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;
import space.commandf1.fastbuilder.config.configs.ReplayConfig;
import space.commandf1.fastbuilder.config.configs.SettingsConfig;
import space.commandf1.fastbuilder.ingame.InGame;
import space.commandf1.fastbuilder.playerdata.PlayerData;
import space.commandf1.fastbuilder.playerdata.PlayerDataManager;
import space.commandf1.fastbuilder.utils.LocationUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameTask extends BukkitRunnable {
    private final InGame inGame;
    private final long startTime;
    private long elapsedTime;
    private Replay replay = null;

    public static final List<GameTask> tasks = new ArrayList<>();

    public static GameTask getGameTaskByInGame(InGame inGame) {
        for (GameTask task : tasks) {
            if (task.getInGame().equals(inGame)) {
                return task;
            }
        }

        return null;
    }

    public GameTask(InGame inGame) {
        this.inGame = inGame;
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = startTime;

        if (ReplayConfig.ENABLE_REPLAY.getValue()) {
            this.replay = ReplayAPI.getInstance().recordReplay(
                    inGame.getPlayer().getName()
                            + "-" + inGame.getGame().getArena().getName()
                            + "-" + System.currentTimeMillis()
                    , this.inGame.getPlayer()
            );
        }

        this.runTaskTimer(AdvancedFastBuilderPlugin.getInstance(), 0, 2);

        tasks.add(this);
    }

    @Override
    public void run() {
        elapsedTime = System.currentTimeMillis() - startTime;

        if (!inGame.isStarted() || inGame.getPlayer() == null || inGame.getGame() == null || elapsedTime >= SettingsConfig.MAX_TIME.getValue() || LocationUtils.isPlayerInRegion(this.inGame.getGame().getFirst(), this.inGame.getGame().getSecond(), inGame.getPlayer()) || LocationUtils.isFallingIntoVoid(inGame.getPlayer())) {
            tasks.remove(this);
            this.inGame.cancel();
            this.cancel();
            return;
        }

        if (LocationUtils.getBlockBelowPlayer(this.getInGame().getPlayer()).getType().equals(Material.getMaterial(SettingsConfig.FINISH_BLOCK.getValue()))) {
            PlayerData playerData = PlayerDataManager.getPlayerDataByPlayer(this.inGame.getPlayer());
            boolean playerDataExists;
            if (playerData == null) {
                playerDataExists = false;
                playerData = new PlayerData(inGame.getPlayer());
            } else {
                playerDataExists = true;
            }
            if (playerData.getBest() > this.elapsedTime) {
                playerData.setBest(this.elapsedTime);
            }

            if (playerDataExists) {
                PlayerDataManager.replacePlayerData(playerData);
                try {
                    PlayerDataManager.saveChanges();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // PlayerDataManager.getNewPlayerdatas().add(playerData);


            this.inGame.finish();
            if (ReplayConfig.ENABLE_REPLAY.getValue()) {
                replay.getRecorder().stop(true);
            }
            this.cancel();
        }
    }

    public InGame getInGame() {
        return inGame;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}
