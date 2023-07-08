package space.commandf1.fastbuilder.ingame;

import org.bukkit.entity.Player;
import space.commandf1.fastbuilder.config.configs.MessageConfig;
import space.commandf1.fastbuilder.config.configs.SettingsConfig;
import space.commandf1.fastbuilder.game.Game;
import space.commandf1.fastbuilder.task.GameTask;
import space.commandf1.fastbuilder.utils.EntityUtils;

public class InGame {
    private final Player player;
    private final Game game;
    private boolean isStarted = false;

    public InGame(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isStarted() {
        return isStarted;
    }

    @SuppressWarnings("deprecation")
    public void finish() {
        this.reset();
        this.setStarted(false);
        this.player.sendTitle(MessageConfig.VICTORY_TITLE_MESSAGE.getValue(), MessageConfig.VICTORY_SUBTITLE_MESSAGE.getValue());
        if (SettingsConfig.ENABLE_FIREWORK.getValue()) {
            EntityUtils.spawnFireWorks(this.player.getLocation());
        }
    }

    public void reset() {
        this.player.teleport(this.game.getCentre());
    }

    public void cancel() {
        this.reset();
        this.setStarted(false);
    }

    public void setStarted(boolean started) {
        isStarted = started;
        if (isStarted) {
            new GameTask(this);
        }
    }
}
