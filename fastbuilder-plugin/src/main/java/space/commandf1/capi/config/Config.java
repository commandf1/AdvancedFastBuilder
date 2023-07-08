package space.commandf1.capi.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@SuppressWarnings("unused")
public class Config implements Serializable {
    private final File config;
    private YamlConfiguration yaml;

    public Config(File config) {
        this.config = config;
        this.yaml = YamlConfiguration.loadConfiguration(this.config);
    }

    public Config(String config, Plugin plugin) {
        this.config = new File(plugin.getDataFolder(), config);
        this.yaml = YamlConfiguration.loadConfiguration(this.config);
    }

    public void save() throws IOException {
        this.yaml.save(this.config);
    }

    public void saveWithoutException() {
        try {
            this.yaml.save(this.config);
        } catch (IOException ignored) {}
    }

    public YamlConfiguration getConfig() {
        return this.yaml;
    }

    public void reloadConfig() {
        this.yaml = YamlConfiguration.loadConfiguration(this.config);
    }

    public void setWithoutException(String path, Object value) {
        this.yaml.set(path, value);
        this.saveWithoutException();
    }

    public void set(String path, Object value) throws IOException {
        this.yaml.set(path, value);
        this.save();
    }
}