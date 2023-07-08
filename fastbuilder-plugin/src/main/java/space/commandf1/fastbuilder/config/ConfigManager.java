package space.commandf1.fastbuilder.config;

import org.bukkit.configuration.file.YamlConfiguration;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;
import space.commandf1.fastbuilder.config.configs.ItemsConfig;
import space.commandf1.fastbuilder.config.configs.MessageConfig;
import space.commandf1.fastbuilder.config.configs.ReplayConfig;
import space.commandf1.fastbuilder.config.configs.SettingsConfig;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class ConfigManager {
    private static final List<Config> configs = Arrays.asList(new SettingsConfig(), new ReplayConfig(), new MessageConfig(), new ItemsConfig());

    public static void init() {
/*        for (Class<?> clazz : ClassUtils.getAllClassesInPackage("space.commandf1.fastbuilder.config.configs")) {
            if (clazz.isAssignableFrom(Config.class)) {
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                configs.add((Config) constructor.newInstance());
            }
        }
*/
        for (Config config : configs) {
            readConfigValues(config);
        }
    }

    public static void save() {
        for (Config config : configs) {
            writeConfigValues(config);
        }
    }

    private static void readConfigValues(Config config) {
        File configFile = new File(AdvancedFastBuilderPlugin.getInstance().getDataFolder(), config.getName());
        if (!configFile.exists()) {
            if (!configFile.getParentFile().exists()) {
                configFile.getParentFile().mkdirs();
            }
            try {
                Files.createFile(configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configFile);
        Class<? extends Config> clazz = config.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = null;
            try {
                obj = field.get(config);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (!(obj instanceof ConfigValue)) {
                continue;
            }

            ConfigValue<?> configValue = (ConfigValue<?>) obj;

            if (yamlConfiguration.get(configValue.getPath()) == null) {
                yamlConfiguration.set(configValue.getPath(), configValue.getValue());
            }
        }
        try {
            yamlConfiguration.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        Class<?> clazz = config.getClass();
        Field[] fields = clazz.getDeclaredFields();
        File file = getFile(config.getName());
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        for (Field field : fields) {
            if (!field.getType().equals(ConfigValue.class)) {
                continue;
            }
            try {
                field.setAccessible(true);
                ConfigValue<?> value = (ConfigValue<?>) field.get(config);
                Object obj = yamlConfiguration.get(value.getPath());
                if (obj != null) {
                    if (value.getValue() instanceof String) {
                        obj = StringUtils.toMinecraftColor((String) obj);
                    }
                    value.setObjectValue(obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/
    }

    private static void writeConfigValues(Config config) {
        Class<?> clazz = config.getClass();
        Field[] fields = clazz.getDeclaredFields();
        File file = getFile(config.getName());
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

        for (Field field : fields) {
            if (!field.getType().equals(ConfigValue.class)) {
                continue;
            }
            try {
                ConfigValue<?> value = (ConfigValue<?>) field.get(config);
                yamlConfiguration.set(value.getPath(), value.getValue());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getFile(String name) {
        return new File(AdvancedFastBuilderPlugin.getInstance().getDataFolder(), name);
    }
}
