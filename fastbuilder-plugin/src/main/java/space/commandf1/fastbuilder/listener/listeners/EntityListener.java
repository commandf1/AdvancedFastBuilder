package space.commandf1.fastbuilder.listener.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Player player = event.getEntity().getType().equals(EntityType.PLAYER) ? (Player) event.getEntity() : null;
        if (player == null) {
            return;
        }
        player.setHealth(player.getMaxHealth());
        event.setCancelled(true);
    }
}
