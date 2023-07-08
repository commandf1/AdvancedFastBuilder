package space.commandf1.fastbuilder.inventory.menu.impl;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import space.commandf1.capi.factory.factories.ItemFactory;
import space.commandf1.fastbuilder.inventory.IButtonAction;
import space.commandf1.fastbuilder.inventory.action.EventController;
import space.commandf1.fastbuilder.inventory.action.impl.ButtonAction;
import space.commandf1.fastbuilder.inventory.button.Button;
import space.commandf1.fastbuilder.inventory.button.impl.NormalButton;
import space.commandf1.fastbuilder.inventory.menu.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageMenu implements Menu {
    private String title;
    private int length, width;
    private final List<Button> buttons = new ArrayList<>();
    private ItemStack placeholder, next = new ItemFactory(Material.STAINED_GLASS_PANE, 1, 5).build(), last = new ItemFactory(Material.STAINED_GLASS_PANE, 1, 14).build();
    private final Map<ItemStack, ButtonAction> objects = new HashMap<>();

    public PageMenu(String title, int length, int width, ItemStack placeholder) {
        this.title = title;
        this.length = length;
        this.width = width;
        this.placeholder = placeholder;
        this.placePlaceHolder();
    }

    private void placePlaceHolder() {
        for (int i = 0; i < this.length; i++) {
            if (i == 0) {
                NormalButton nextButton = new NormalButton(this.next, 0);
                nextButton.setAction(new ButtonAction(nextButton, new IButtonAction() {
                    @Override
                    public <T extends HumanEntity> void run(T player, EventController controller) {

                        controller.setCancelled(true);
                    }
                }));
                buttons.add(nextButton);
                continue;
            }

            if (i == this.length - 1) {
                NormalButton lastButton = new NormalButton(this.last, i);
                buttons.add(lastButton);
                continue;
            }
            buttons.add(new NormalButton(this.placeholder, i));
        }

        for (int i = 1; i < width; i++) {
            buttons.add(new NormalButton(this.placeholder, i * this.length));
        }

        for (int i = 1; i < width; i++) {
            buttons.add(new NormalButton(this.placeholder, this.length * (i + 1) - 1));
        }

        for (int i = width * length - 1; i > width * length - 1 - (length - 1); i--) {
            buttons.add(new NormalButton(this.placeholder, i));
        }
    }
    
    public ItemStack getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(ItemStack placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public List<Button> getButtons() {
        return buttons;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public Map<ItemStack, ButtonAction> getObjects() {
        return objects;
    }

    public ItemStack getLast() {
        return last;
    }

    public void setLast(ItemStack last) {
        this.last = last;
    }

    public ItemStack getNext() {
        return next;
    }

    public void setNext(ItemStack next) {
        this.next = next;
    }
}
