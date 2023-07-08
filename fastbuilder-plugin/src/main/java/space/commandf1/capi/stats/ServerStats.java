package space.commandf1.capi.stats;

import org.bukkit.Bukkit;

public class ServerStats {
    private ServerStats() {}

    public static final String IP = Bukkit.getServer().getIp();
    public static final int PORT = Bukkit.getServer().getPort();
    public static final String BUKKIT_VERSION = Bukkit.getServer().getBukkitVersion();

}
