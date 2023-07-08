package space.commandf1.capi.manager.managers;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import space.commandf1.capi.manager.Manager;

@Deprecated
public class ListenerManager implements Manager {
    private static final ListenerManager instance = new ListenerManager();

    public static ListenerManager getInstance() {
        return instance;
    }

    public void registerListeners(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}
