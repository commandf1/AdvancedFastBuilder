package space.commandf1.fastbuilder.inventory.menu.impl;

import space.commandf1.fastbuilder.inventory.button.Button;
import space.commandf1.fastbuilder.inventory.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class NormalMenu implements Menu {
    private String title;
    private int length, width;
    private final List<Button> buttons = new ArrayList<>();

    public NormalMenu(String title, int length, int width) {
        this.title = title;
        this.length = length;
        this.width = width;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
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
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public List<Button> getButtons() {
        return this.buttons;
    }
}
