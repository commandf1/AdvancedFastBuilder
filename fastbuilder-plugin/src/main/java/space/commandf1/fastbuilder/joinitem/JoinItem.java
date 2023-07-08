package space.commandf1.fastbuilder.joinitem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;

import java.util.Objects;

public class JoinItem {
    private ItemStack item;
    private Action action;
    private int slot;

    private final Listener listener = new Listener() {
        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event) {
            Player player = event.getPlayer();
            player.getInventory().setItem(getSlot(), getItem());
        }

        @EventHandler
        public void onInventoryItemMove(InventoryMoveItemEvent event) {
            Inventory source = event.getSource();
            if (source.getType().equals(InventoryType.PLAYER) && event.getItem().equals(getItem())) {
                event.setCancelled(true);
            }
        }

        @EventHandler
        public void onPlayerInteract(PlayerInteractEvent event) {
            ItemStack itemStack = event.getItem();
            if (itemStack == null) {
                return;
            }

            if (Objects.equals(item, itemStack)) {
                action.run(event.getPlayer());
            }
        }
    };

    public JoinItem(ItemStack item, int slot, Action action) {
        this.item = item;
        this.action = action;
        this.slot = slot;
    }

    public void register() {
        AdvancedFastBuilderPlugin.getInstance().getServer().getPluginManager().registerEvents(this.listener, AdvancedFastBuilderPlugin.getInstance());
    }

    public void unregister() {
        HandlerList.unregisterAll(this.listener);
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}

