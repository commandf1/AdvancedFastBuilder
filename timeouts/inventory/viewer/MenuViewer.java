package space.commandf1.fastbuilder.inventory.viewer;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;
import space.commandf1.fastbuilder.inventory.action.EventController;
import space.commandf1.fastbuilder.inventory.button.Button;
import space.commandf1.fastbuilder.inventory.menu.Menu;
import space.commandf1.fastbuilder.utils.PlayerUtils;

public interface MenuViewer<T extends Menu> {
    T getMenu();

    void setMenu(T menu);

    default <E extends HumanEntity> void view(E player) {
        Menu menu = getMenu();
        Inventory inventory = menu.createInventory();

        AdvancedFastBuilderPlugin.getInstance().getServer().getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onInventoryClose(InventoryCloseEvent event) {
                if (!PlayerUtils.isSamePlayer(event.getPlayer(), player)) {
                    return;
                }

                HandlerList.unregisterAll(this);
                event.getPlayer().closeInventory();
            }

            @EventHandler
            public void onInventoryClick(InventoryClickEvent event) {
                if (!PlayerUtils.isSamePlayer(event.getWhoClicked(), player)) {
                    return;
                }

                for (Button button : menu.getButtons()) {
                    if (button.getSlot() == event.getRawSlot()) {
                        button.getAction().getAction().run(event.getWhoClicked(), new EventController(event));
                        return;
                    }
                }

                event.setCancelled(true);
            }
/*
            @EventHandler
            public void onInventoryMove(InventoryMoveItemEvent event) {
                event.setCancelled(true);
            }

            @EventHandler
            public void onInventoryClick(InventoryClickEvent event) {

                ItemStack item = event.getCurrentItem();
                int slot = event.getSlot();

                if (slot < 0) {
                    event.setCancelled(true);
                    return;
                }

                for (Button button : menu.getButtons()) {
                    if (slot == button.getSlot() && Objects.equals(item, button.getItem())) {
                        button.getAction().getAction().run(event.getWhoClicked());
                        event.setCancelled(true);
                    }
                }
            }*/
        }, AdvancedFastBuilderPlugin.getInstance());
        player.openInventory(inventory);
    }
}
