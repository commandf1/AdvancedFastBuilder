package space.commandf1.fastbuilder.inventory.viewer.impl;

import space.commandf1.fastbuilder.inventory.menu.impl.NormalMenu;
import space.commandf1.fastbuilder.inventory.viewer.MenuViewer;

public class NormalMenuViewer implements MenuViewer<NormalMenu> {
    private NormalMenu menu;

    public NormalMenuViewer(NormalMenu menu) {
        this.menu = menu;
    }

    @Override
    public NormalMenu getMenu() {
        return menu;
    }

    @Override
    public void setMenu(NormalMenu menu) {
        this.menu = menu;
    }
}
