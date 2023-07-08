package space.commandf1.fastbuilder.config.configs;

import org.bukkit.Material;
import space.commandf1.fastbuilder.config.Config;
import space.commandf1.fastbuilder.config.ConfigValue;
import space.commandf1.fastbuilder.utils.StringUtils;

import java.util.Arrays;
import java.util.List;

public class SettingsConfig implements Config {

    public static final ConfigValue<Long> MAX_TIME = new ConfigValue<>("max-time", 120000L, "The maximum building time for players(ms).");
    public static final ConfigValue<Boolean> ENABLE_FIREWORK = new ConfigValue<>("enable-firework", true, "When the player passes through, launch a firework");
    public static final ConfigValue<String> FINISH_BLOCK = new ConfigValue<>("finish-block", Material.GOLD_BLOCK.toString(), "The block which can let a player win");
    public static final ConfigValue<Boolean> ENABLE_SCOREBOARD = new ConfigValue<>("enable-scoreboard", true, "Enable scoreboard");
    public static final ConfigValue<String> SCOREBOARD_TITLE = new ConfigValue<>("scoreboard-title", "§6§lFastBuilder", "Scoreboard title");
    private static final ConfigValue<List<String>> SCOREBOARD = new ConfigValue<>("scoreboard", Arrays.asList(
            "&7-----------------------------",
            "",
            "&b&lPlayer:",
            "&8》 &7%player%",
            "",
            "&b&lPersonal Best Record:",
            "&8》 &7%player_best%",
            "",
            "&b&lLeaderboard:",
            "&8》 &7%leader_first%",
            "&8》 &7%leader_second%",
            "&8》 &7%leader_third%",
            "",
            "&b&lTime:",
            "&8》 &7%time%",
            "",
            "&7-----------------------------"
            ), "Scoreboard");

    public static ConfigValue<List<String>> getScoreboard() {
        SCOREBOARD.setValue(StringUtils.toMinecraftColor(SCOREBOARD.getValue()));
        return SCOREBOARD;
    }

    @Override
    public String getName() {
        return "settings.yml";
    }
}
