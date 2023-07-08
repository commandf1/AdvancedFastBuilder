package space.commandf1.capi.manager.managers;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import space.commandf1.capi.manager.Manager;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.jar.JarFile;

public class PluginManager implements Manager {
    private static final PluginManager instance = new PluginManager();

    private final SimplePluginManager pluginManager = (SimplePluginManager) Bukkit.getPluginManager();

    public Plugin loadPlugin(File file) {
        try {
            return this.pluginManager.loadPlugin(file);
        } catch (InvalidPluginException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void unloadPlugin(Plugin plugin) {
        this.disablePlugin(plugin);

        try {
            Field pluginsField = this.pluginManager.getClass().getDeclaredField("plugins");
            pluginsField.setAccessible(true);
            List<?> plugins = (List<?>) pluginsField.get(this.pluginManager);

            Field lookupNamesField = this.pluginManager.getClass().getDeclaredField("lookupNames");
            lookupNamesField.setAccessible(true);

            Map<?, ?> lookupNames = (Map<?, ?>) lookupNamesField.get(this.pluginManager);
            Field commandMapField = this.pluginManager.getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            SimpleCommandMap commandMap = (SimpleCommandMap) commandMapField.get(this.pluginManager);

            Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
            knownCommandsField.setAccessible(true);
            Map<?, ?> knownCommands = (Map<?, ?>) knownCommandsField.get(commandMap);
            plugins.remove(plugin);
            lookupNames.remove(plugin.getName());
            Iterator<? extends Map.Entry<?, ?>> iterator = knownCommands.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<?, ?> entry = iterator.next();
                if (entry.getValue() instanceof PluginCommand) {
                    PluginCommand command = (PluginCommand) entry.getValue();
                    if (command.getPlugin().equals(plugin)) {
                        iterator.remove();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Plugin getPluginIgnoreCase(String name) {
        try {
            Field field = this.pluginManager.getClass().getDeclaredField("lookupNames");
            field.setAccessible(true);
            Map<?, ?> lookupNames = (Map<?, ?>) field.get(this.pluginManager);
            for (Object o : lookupNames.keySet()) {
                if (name.equalsIgnoreCase((String) o)) {
                    return (Plugin) lookupNames.get(o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getPluginFileByName(String name) {
        File[] plugins = this.pluginManager.getPlugins()[0].getDataFolder().getParentFile().listFiles();
        if (plugins == null) {
            return null;
        }
        for (File plugin : plugins) {
            if (plugin.isDirectory() || !plugin.getName().toLowerCase().endsWith(".jar") || !plugin.isFile()) {
                continue;
            }

            try {
                JarFile jarFile = new JarFile(plugin);
                Scanner scanner = new Scanner(jarFile.getInputStream(jarFile.getEntry("plugin.yml")));
                if (scanner.nextLine().toLowerCase().contains(name.toLowerCase())) {
                    jarFile.close();
                    return plugin;
                }

                jarFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Plugin loadPlugin(String name) {
        return this.loadPlugin(this.getPluginFileByName(name));
    }

    public void disablePlugin(Plugin plugin) {
        this.pluginManager.disablePlugin(plugin);
    }

    public void enablePlugin(Plugin plugin) {
        this.pluginManager.enablePlugin(plugin);
    }

    public SimplePluginManager getPluginManager() {
        return pluginManager;
    }

    public static PluginManager getInstance() {
        return instance;
    }
}
