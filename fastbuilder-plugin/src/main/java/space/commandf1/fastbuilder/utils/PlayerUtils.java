package space.commandf1.fastbuilder.utils;

import org.bukkit.entity.HumanEntity;

public class PlayerUtils {
    public static <T extends HumanEntity> boolean isSamePlayer(T player, T target) {
        if (player == null || target == null) {
            return false;
        }
        return player.getUniqueId().equals(target.getUniqueId()) && player.getName().equals(target.getName());
    }
}
