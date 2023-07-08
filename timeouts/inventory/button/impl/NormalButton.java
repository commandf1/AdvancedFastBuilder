package space.commandf1.fastbuilder.inventory.button.impl;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import space.commandf1.fastbuilder.inventory.IButtonAction;
import space.commandf1.fastbuilder.inventory.action.EventController;
import space.commandf1.fastbuilder.inventory.action.impl.ButtonAction;
import space.commandf1.fastbuilder.inventory.button.Button;

public class NormalButton implements Button {
    private ItemStack itemStack;
    private int slot;
    private ButtonAction action;

    public NormalButton(ItemStack itemStack, int slot) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.action = new ButtonAction(this,
                new IButtonAction() { @Override public <T extends HumanEntity> void run(T player, EventController controller) { controller.setCancelled(true); }} );
    }

    @Override
    public ItemStack getItem() {
        return this.itemStack;
    }

    @Override
    public void setItem(ItemStack item) {
        this.itemStack = item;
    }

    @Override
    public int getSlot() {
        return this.slot;
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public void setAction(ButtonAction action) {
        this.action = action;
    }

    @Override
    public ButtonAction getAction() {
        return this.action;
    }
}
