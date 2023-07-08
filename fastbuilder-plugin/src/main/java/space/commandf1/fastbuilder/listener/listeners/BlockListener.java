package space.commandf1.fastbuilder.listener.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import space.commandf1.fastbuilder.game.Game;
import space.commandf1.fastbuilder.game.GameManager;
import space.commandf1.fastbuilder.ingame.InGame;
import space.commandf1.fastbuilder.ingame.InGameManager;
import space.commandf1.fastbuilder.utils.LocationUtils;

import static space.commandf1.fastbuilder.permission.PermissionList.ADMIN_PERMISSION;

public class BlockListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(ADMIN_PERMISSION) && !player.getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location location = event.getBlock().getLocation();

        Game game = GameManager.getGameByLocation(location);
        InGame inGame = InGameManager.getInGame(player);
        if ((!player.hasPermission(ADMIN_PERMISSION) && !player.getGameMode().equals(GameMode.CREATIVE)) && (inGame == null || game == null)) {
            event.setCancelled(true);
            return;
        }

        if (game == null) {
            return;
        }

        if (inGame == null) {
            // inGame = new InGame(player, game);
            event.setCancelled(true);
            return;
        }

        boolean inRegion = LocationUtils.isBlockInRegion(game.getFirst(), game.getSecond(), block);
        if (!inRegion) {
            event.setCancelled(true);
        }

        if (!inGame.isStarted()) {
            inGame.setStarted(true);
        }

        /*
        InGame inGame = InGameManager.getInGame(player);
        if (inGame == null && !player.hasPermission(ADMIN_PERMISSION) && !player.getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
            return;
        }

        LocationUtils.isBlockInRegion()*/
    }
}
