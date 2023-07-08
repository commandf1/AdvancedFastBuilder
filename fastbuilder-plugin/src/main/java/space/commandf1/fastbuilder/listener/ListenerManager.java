package space.commandf1.fastbuilder.listener;

import org.bukkit.event.Listener;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;
import space.commandf1.fastbuilder.listener.listeners.BlockListener;
import space.commandf1.fastbuilder.listener.listeners.EntityListener;
import space.commandf1.fastbuilder.listener.listeners.PlayerListener;
import space.commandf1.fastbuilder.listener.listeners.WorldListener;

public class ListenerManager {
    private static final Listener[] listeners = { new BlockListener(), new EntityListener(), new PlayerListener(), new WorldListener() };
    public static void init() {
        for (Listener listener : listeners) {
            AdvancedFastBuilderPlugin.getInstance().getServer().getPluginManager().registerEvents(listener, AdvancedFastBuilderPlugin.getInstance());
        }
    }
    /*
    public static void init() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<?> clazz : ClassUtils.getAllClassesInPackage("space.commandf1.fastbuilder.listener.listeners")) {
            if (clazz.isAssignableFrom(Listener.class)) {
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                AdvancedFastBuilderPlugin.getInstance().getServer().getPluginManager().registerEvents((Listener) constructor.newInstance(), AdvancedFastBuilderPlugin.getInstance());
            }
        }
    }*/
}
