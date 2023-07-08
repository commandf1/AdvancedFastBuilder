package space.commandf1.fastbuilder.inventory.action;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EventController {
    private final InventoryClickEvent event;

    public EventController(InventoryClickEvent event) {
        this.event = event;
    }

    public void setCancelled(boolean cancelled) {
        this.event.setCancelled(cancelled);
    }

    public boolean isCancelled() {
        return this.event.isCancelled();
    }

    public ClickType getClickType() {
        return this.event.getClick();
    }
}
