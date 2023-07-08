package space.commandf1.fastbuilder.inventory.action.impl;

import space.commandf1.fastbuilder.inventory.IButtonAction;
import space.commandf1.fastbuilder.inventory.action.Action;
import space.commandf1.fastbuilder.inventory.button.Button;

public class ButtonAction implements Action {
    private final Button button;
    private IButtonAction action;

    public ButtonAction(Button button, IButtonAction action) {
        this.button = button;
        this.action = action;
    }

    public Button getButton() {
        return button;
    }

    @Override
    public IButtonAction getAction() {
        return action;
    }

    @Override
    public void setAction(IButtonAction action) {
        this.action = action;
    }
}

