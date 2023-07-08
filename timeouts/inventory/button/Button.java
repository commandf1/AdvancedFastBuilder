package space.commandf1.fastbuilder.inventory.button;

import org.bukkit.inventory.ItemStack;
import space.commandf1.fastbuilder.inventory.action.impl.ButtonAction;

public interface Button {
    ItemStack getItem();

    void setItem(ItemStack item);

    int getSlot();

    void setSlot(int slot);

    void setAction(ButtonAction action);

    ButtonAction getAction();
}
