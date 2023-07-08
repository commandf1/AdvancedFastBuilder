package space.commandf1.fastbuilder.api;

import org.bukkit.plugin.java.JavaPlugin;

public class API implements FastBuilderAPI {
    private final JavaPlugin plugin;

    public API(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getVersion() {
        return this.plugin.getDescription().getVersion();
    }
}
