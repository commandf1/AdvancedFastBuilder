package space.commandf1.fastbuilder.joinitem;

import org.bukkit.entity.HumanEntity;

public interface Action {
    <T extends HumanEntity> void run(T player);
}
