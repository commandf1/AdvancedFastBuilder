package space.commandf1.fastbuilder.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class LocationUtils {
    public static boolean isLocationInRegion(Location loc1, Location loc2, Location target) {
        double minX = Math.min(loc1.getX(), loc2.getX());
        double minY = Math.min(loc1.getY(), loc2.getY());
        double minZ = Math.min(loc1.getZ(), loc2.getZ());

        double maxX = Math.max(loc1.getX(), loc2.getX());
        double maxY = Math.max(loc1.getY(), loc2.getY());
        double maxZ = Math.max(loc1.getZ(), loc2.getZ());

        if (!target.getWorld().equals(loc1.getWorld()) || !target.getWorld().equals(loc2.getWorld())) {
            return false;
        }

        return target.getX() >= minX && target.getX() <= maxX &&
                target.getY() >= minY && target.getY() <= maxY &&
                target.getZ() >= minZ && target.getZ() <= maxZ;
    }

    public static boolean isFallingIntoVoid(Location location) {
        return location.getY() <= 0;
    }

    public static boolean isFallingIntoVoid(Player player) {
        return isFallingIntoVoid(player.getLocation());
    }

    public static boolean isPlayerInRegion(Location loc1, Location loc2, Player player) {
        return isLocationInRegion(loc1, loc2, player.getLocation());
    }

    public static boolean isBlockInRegion(Location loc1, Location loc2, Block block) {
        return isLocationInRegion(loc1, loc2, block.getLocation());
    }

    public static Block getBlockBelowPlayer(Player player) {
        return player.getLocation().getBlock().getRelative(BlockFace.DOWN);
    }

}
