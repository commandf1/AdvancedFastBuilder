package space.commandf1.fastbuilder.config.configs;

import space.commandf1.fastbuilder.config.Config;
import space.commandf1.fastbuilder.config.ConfigValue;

public class ReplayConfig implements Config {
    public static final ConfigValue<Boolean> ENABLE_REPLAY = new ConfigValue<>("general.enable", true, "Enable replay");
    public static final ConfigValue<Boolean> OFFLINE_SKINS = new ConfigValue<>("general.use_offline_skins", true, "If enabled in offline mode, the plugin will fetch the skin data from Mojang");

    @Override
    public String getName() {
        return "replay.yml";
    }
}
