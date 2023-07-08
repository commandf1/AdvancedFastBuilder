package space.commandf1.capi.utils;

import org.bukkit.Bukkit;

public final class NMSUtils {
    private NMSUtils() {}

    public static String getVersion() {
        return Bukkit.getServer().getClass().getName().replace("net.minecraft.server", "").replace("org.bukkit.craftbukkit.", "").replace("CraftServer", "").replace(".", "");
    }

    public static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + NMSUtils.getVersion() + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
