package space.commandf1.fastbuilder.inventory;

import org.bukkit.entity.HumanEntity;
import space.commandf1.fastbuilder.inventory.action.EventController;

public interface IButtonAction {
    <T extends HumanEntity> void run(T player, EventController controller);
}
