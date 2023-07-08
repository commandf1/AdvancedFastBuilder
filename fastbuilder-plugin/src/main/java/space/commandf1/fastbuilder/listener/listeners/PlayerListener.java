package space.commandf1.fastbuilder.listener.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import space.commandf1.fastbuilder.playerdata.PlayerData;
import space.commandf1.fastbuilder.playerdata.PlayerDataManager;
import space.commandf1.fastbuilder.utils.ScoreboardUtils;

import java.io.IOException;

public class PlayerListener implements Listener {
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) throws IOException {
        Player player = event.getPlayer();

        PlayerData playerData = PlayerDataManager.getPlayerDataByPlayer(player);
        if (playerData == null) {
            playerData = new PlayerData(player);
        }

        PlayerDataManager.getNewPlayerdatas().add(playerData);
        PlayerDataManager.saveChanges();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        ScoreboardUtils.disableScoreboard(event.getPlayer());
    }
}
