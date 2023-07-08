package space.commandf1.fastbuilder.inventory.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import space.commandf1.fastbuilder.inventory.button.Button;

import java.util.List;

public interface Menu {
    String getTitle();

    void setTitle(String title);

    int getLength();

    int getWidth();

    void setLength(int length);

    void setWidth(int width);

    List<Button> getButtons();

    default Inventory createInventory() {
        Inventory inv = Bukkit.createInventory(
                null,
                this.getLength() * this.getWidth(),
                this.getTitle());

        for (Button button : this.getButtons()) {
            inv.setItem(button.getSlot(), button.getItem());
        }

        return inv;
    }
}
