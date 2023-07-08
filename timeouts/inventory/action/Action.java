package space.commandf1.fastbuilder.inventory.action;

import space.commandf1.fastbuilder.inventory.IButtonAction;

public interface Action {
    IButtonAction getAction();

    void setAction(IButtonAction action);
}
