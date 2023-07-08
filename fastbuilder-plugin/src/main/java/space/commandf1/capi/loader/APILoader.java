package space.commandf1.capi.loader;

import org.bukkit.plugin.java.JavaPlugin;

public class APILoader {
    private static JavaPlugin plugin;

    public static void init(JavaPlugin plugin) {
        APILoader.plugin = plugin;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(JavaPlugin plugin) {
        APILoader.plugin = plugin;
    }
}
