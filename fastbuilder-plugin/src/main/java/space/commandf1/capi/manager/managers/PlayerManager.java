package space.commandf1.capi.manager.managers;

import org.bukkit.entity.Player;
import space.commandf1.capi.manager.Manager;

import java.lang.reflect.Method;

public class PlayerManager implements Manager {
    private static final PlayerManager instance = new PlayerManager();

    public static PlayerManager getInstance() {
        return instance;
    }

    @SuppressWarnings("unused")
    public void sendTitle(Player player, String first, String second) {
        Class<?> playerClass = player.getClass();
        try {
            Method sendTitle = playerClass.getMethod("sendTitle", String.class, String.class);
            sendTitle.invoke(player, first, second);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void kick(Player player) {
        player.kickPlayer("");
    }
}
