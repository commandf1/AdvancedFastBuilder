package space.commandf1.fastbuilder.gui;

import com.cryptomorin.xseries.XMaterial;
import me.jumper251.replay.filesystem.saving.ReplaySaver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import space.commandf1.capi.factory.factories.ItemFactory;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;
import space.commandf1.fastbuilder.config.configs.MessageConfig;
import space.commandf1.fastbuilder.utils.PlayerUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Deprecated
public class ReplayMenu {
    public void view(Player player) {
        Inventory inventory = this.createInventory();

        assert XMaterial.RED_STAINED_GLASS.parseMaterial() != null;
        ItemStack red = new ItemStack(XMaterial.RED_STAINED_GLASS.parseMaterial(), 1);
        assert XMaterial.GREEN_STAINED_GLASS.parseMaterial() != null;
        ItemStack green = new ItemStack(XMaterial.GREEN_STAINED_GLASS.parseMaterial(), 1);

        List<ItemStack> items = new ArrayList<>();
        for (String replay : ReplaySaver.getReplays()) {
            String[] args = replay.split("-");
            if (args[0].equals(player.getName())) {
                assert XMaterial.REDSTONE_LAMP.parseMaterial() != null;
                items.add(new ItemFactory(new ItemStack(XMaterial.REDSTONE_LAMP.parseMaterial(), 1))
                        .setDisplayName("§e§l" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(args[2]))
                        .setItemLore(new String[]{
                                "§cArena: " + args[1]
                        })
                        .build());
            }
        }

        AdvancedFastBuilderPlugin.getInstance().registerListeners(new Listener() {
            private int page = 1;
            private final Map<List<ItemStack>, Integer> maps = new HashMap<List<ItemStack>, Integer>() {{
                int i = 1;
                List<ItemStack> items1 = new ArrayList<>();
                for (int i1 = 0; i1 < items.size(); i1++, i ++) {
                    if (i > 28) {
                        i = 1;
                        put(items1, page);
                        items1 = new ArrayList<>();
                        page ++;
                    }
                    items1.add(items.get(i));
                }
            }};
            // 28

            @EventHandler
            public void onClick(InventoryClickEvent event) {
                if (!PlayerUtils.isSamePlayer(event.getWhoClicked(), player)) return;
                if (Objects.equals(event.getCurrentItem(), red)) {
                    if (page > 1) {
                        Inventory page = createInventory();
                        // page.addItem(maps.);
                    }
                }
                event.setCancelled(true);
            }

            @EventHandler
            public void onClose(InventoryCloseEvent event) {
                if (!PlayerUtils.isSamePlayer(event.getPlayer(), player)) return;
                HandlerList.unregisterAll(this);
            }
        });
    }

    private Inventory createInventory() {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, MessageConfig.REPLAY_MENU_TITLE.getValue());
        assert XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial() != null;
        ItemStack black = new ItemStack(XMaterial.BLACK_STAINED_GLASS_PANE.parseMaterial(), 1);
        assert XMaterial.RED_STAINED_GLASS.parseMaterial() != null;
        ItemStack red = new ItemStack(XMaterial.RED_STAINED_GLASS.parseMaterial(), 1);
        assert XMaterial.GREEN_STAINED_GLASS.parseMaterial() != null;
        ItemStack green = new ItemStack(XMaterial.GREEN_STAINED_GLASS.parseMaterial(), 1);
        for (int i = 0; i < 10; i++) {
            inventory.addItem(black);
        }


        inventory.setItem(17, black);
        inventory.setItem(18, black);
        inventory.setItem(26, black);
        inventory.setItem(27, black);
        inventory.setItem(35, black);
        inventory.setItem(36, black);
        inventory.setItem(44, black);
        inventory.setItem(45, black);
        inventory.setItem(46, black);
        inventory.setItem(47, black);
        inventory.setItem(48, red);
        inventory.setItem(49, black);
        inventory.setItem(50, black);
        inventory.setItem(51, black);
        inventory.setItem(52, green);
        inventory.setItem(53, black);
        inventory.setItem(54, black);

        return inventory;
    }
}
