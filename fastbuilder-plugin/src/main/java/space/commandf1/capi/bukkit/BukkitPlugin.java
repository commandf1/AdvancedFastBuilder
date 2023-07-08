package space.commandf1.capi.bukkit;

import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import space.commandf1.capi.manager.managers.CommandManager;
import space.commandf1.capi.manager.managers.PlayerManager;
import space.commandf1.capi.manager.managers.PluginManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public abstract class BukkitPlugin extends JavaPlugin {
    @Override
    public final void onEnable() {
        try {
            this.onStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void onDisable() {
        try {
            this.onExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void onLoad() {
        try {
            this.onSetup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void onSetup() throws Exception;
    public abstract void onStart() throws Exception;
    public abstract void onExit() throws Exception;

    public void mkdirs(String... dirs) {
        for (String dir : dirs) {
            new File(this.getDataFolder(), dir).mkdirs();
        }
    }

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public CommandManager getCommandManager() {
        return CommandManager.getInstance();
    }

    public PlayerManager getPlayerManager() {
        return PlayerManager.getInstance();
    }

    public PluginManager getPluginManager() {
        return PluginManager.getInstance();
    }

    @Override
    public void saveResource(String resourcePath, boolean replace) {
        resourcePath = resourcePath.replace('\\', '/');
        InputStream in = this.getResource(resourcePath);
        if (in == null) {
            return;
            // throw new IllegalArgumentException("插件配置文件损坏，请重新下载插件!" + '\n' + "The plugin file may have been broken, please re-download the plugin.");
        }
        File outFile = new File(this.getDataFolder(), resourcePath);
        int lastIndex = resourcePath.lastIndexOf(47);
        File outDir = new File(this.getDataFolder(), resourcePath.substring(0, Math.max(lastIndex, 0)));
        if (!outDir.exists() && !outDir.mkdirs()) {
            return;
            // throw new IllegalArgumentException("无法创建目录" + "\n" + "Can not create the dir.");
        }
        try {
            if (!(outFile.exists() && !replace)) {
                OutputStream out = Files.newOutputStream(outFile.toPath());
                byte[] buf = new byte[1024];

                int len;
                while((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                out.close();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Deprecated
    public PluginCommand getCommand(String name) {
        return super.getCommand(name);
    }

    @Override
    @Deprecated
    public void saveDefaultConfig() {
        super.saveDefaultConfig();
    }

    @Override
    @Deprecated
    public void saveConfig() {
        super.saveConfig();
    }

    @Override
    @Deprecated
    public void reloadConfig() {
        super.reloadConfig();
    }

    @Override
    @Deprecated
    public FileConfiguration getConfig() {
        return super.getConfig();
    }
}
