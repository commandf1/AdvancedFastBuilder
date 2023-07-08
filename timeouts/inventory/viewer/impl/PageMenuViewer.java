package space.commandf1.fastbuilder.inventory.viewer.impl;

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
import space.commandf1.fastbuilder.inventory.menu.impl.PageMenu;
import space.commandf1.fastbuilder.inventory.viewer.MenuViewer;
import space.commandf1.fastbuilder.utils.PlayerUtils;

public class PageMenuViewer implements MenuViewer<PageMenu> {
    private PageMenu menu;

    public PageMenuViewer(PageMenu menu) {
        this.menu = menu;
    }

    @Override
    public <E extends HumanEntity> void view(E player) {
        PageMenu menu = getMenu();
        Inventory[] inventories = menu.createInventories();
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
        }, AdvancedFastBuilderPlugin.getInstance());
        player.openInventory(inventories[0]);
    }

    @Override
    public PageMenu getMenu() {
        return menu;
    }

    @Override
    public void setMenu(PageMenu menu) {
        this.menu = menu;
    }
}
