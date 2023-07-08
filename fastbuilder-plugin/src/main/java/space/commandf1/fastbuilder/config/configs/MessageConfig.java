package space.commandf1.fastbuilder.config.configs;

import space.commandf1.fastbuilder.config.Config;
import space.commandf1.fastbuilder.config.ConfigValue;

public class MessageConfig implements Config {
    public static final ConfigValue<String> VICTORY_TITLE_MESSAGE = new ConfigValue<>("victory.title", "§6§lVICTORY", "Victory title");
    public static final ConfigValue<String> VICTORY_SUBTITLE_MESSAGE = new ConfigValue<>("victory.subtitle", "§e§lCongratulations!", "Victory title subtitle");
    public static final ConfigValue<String> MENU_MAIN_TITLE = new ConfigValue<>("menu.main.title", "§aGetting start");
    public static final ConfigValue<String> REPLAY_MENU_TITLE = new ConfigValue<>("menu.replay.title", "§cReplay Menu");


    @Override
    public String getName() {
        return "message.yml";
    }
}
