package space.commandf1.capi.stats;

import org.bukkit.Bukkit;

public final class PluginStats {
    private PluginStats() {}

    public static final boolean IS_PLACEHOLDERAPI_INSTALL = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

}
