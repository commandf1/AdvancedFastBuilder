package space.commandf1.capi.factory.factories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import space.commandf1.capi.factory.Factory;

import java.io.Serializable;
import java.util.function.Consumer;

public class CustomInventoryFactory implements Serializable, Factory<Inventory> {
    private final Inventory inventory;

    public CustomInventoryFactory(String title, int size) {
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public CustomInventoryFactory setItem(int i, ItemStack itemStack) {
        this.inventory.setItem(i, itemStack);
        return this;
    }

    public CustomInventoryFactory forEach(Consumer<? super ItemStack> action) {
        this.inventory.forEach(action);
        return this;
    }

    public CustomInventoryFactory fullItem(ItemStack itemStack) {
        for (int i = 0; i < this.inventory.getSize(); i++) {
            this.inventory.setItem(i, itemStack);
        }
        return this;
    }

    @Override
    public Inventory build() {
        return this.inventory;
    }
}
