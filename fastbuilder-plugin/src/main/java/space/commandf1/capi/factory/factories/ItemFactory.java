package space.commandf1.capi.factory.factories;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import space.commandf1.capi.factory.Factory;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;

public class ItemFactory implements Serializable, Factory<ItemStack> {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemFactory() throws Exception {
        Class<?> itemStackClass = ItemStack.class;
        Constructor<?> constructor = itemStackClass.getDeclaredConstructor();
        this.itemStack = (ItemStack) constructor.newInstance();
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemFactory(Material type, int amount) {
        this.itemStack = new ItemStack(type, amount);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemFactory(Material type, int amount, int damage) {
        this.itemStack = new ItemStack(type, amount, (short) damage);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemFactory(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemFactory setDisplayName(String name) {
        this.itemMeta.setDisplayName(name);
        this.setItemMeta();
        return this;
    }

    public ItemFactory setItemLore(String[] lore) {
        this.itemMeta.setLore(new ArrayList<>(Arrays.asList(lore)));
        this.setItemMeta();
        return this;
    }

    public ItemFactory addEnchant(Enchantment enchantment, int i, boolean b) {
        this.itemMeta.addEnchant(enchantment, i, b);
        this.setItemMeta();
        return this;
    }

    public ItemFactory addItemFlags(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        this.setItemMeta();
        return this;
    }

    public ItemFactory setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemFactory setType(Material type) {
        this.itemStack.setType(type);
        return this;
    }

    private void setItemMeta() {
        this.itemStack.setItemMeta(this.itemMeta);
    }

    @Override
    public ItemStack build() {
        return itemStack;
    }
}
