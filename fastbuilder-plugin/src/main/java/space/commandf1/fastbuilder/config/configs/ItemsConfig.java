package space.commandf1.fastbuilder.config.configs;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import space.commandf1.capi.factory.factories.ItemFactory;
import space.commandf1.fastbuilder.config.Config;
import space.commandf1.fastbuilder.config.ConfigValue;

public class ItemsConfig implements Config {

    public static final ConfigValue<ItemStack> REPLAY_ITEM = new ConfigValue<>("replay",
            new ItemFactory(Material.BOOK, 1, 0).setDisplayName("§aReplay System").build(),
            "replay item");

    @Override
    public String getName() {
        return "items.yml";
    }
}
