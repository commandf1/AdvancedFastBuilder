package space.commandf1.fastbuilder;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import space.commandf1.capi.bukkit.BukkitPlugin;
import space.commandf1.fastbuilder.arena.ArenaManager;
import space.commandf1.fastbuilder.config.ConfigManager;
import space.commandf1.fastbuilder.config.configs.ItemsConfig;
import space.commandf1.fastbuilder.joinitem.Action;
import space.commandf1.fastbuilder.joinitem.JoinItem;
import space.commandf1.fastbuilder.listener.ListenerManager;
import space.commandf1.fastbuilder.playerdata.PlayerDataManager;
import space.commandf1.fastbuilder.settings.PluginSettings;
import space.commandf1.fastbuilder.task.ScoreboardTask;
import space.commandf1.fastbuilder.utils.ReplayUtils;
import space.commandf1.fastbuilder.utils.ScoreboardUtils;

import java.util.logging.Logger;

public class AdvancedFastBuilderPlugin extends BukkitPlugin {
    private static Logger logger;
    private static AdvancedFastBuilderPlugin instance;

    public static AdvancedFastBuilderPlugin getInstance() {
        return instance;
    }

    public static Logger getPluginLogger() {
        return logger;
    }

    @Override
    public void onSetup() throws Exception {
    }

    @Override
    public void onStart() throws Exception {
        instance = this;
        logger = this.getLogger();

        long startTime = System.currentTimeMillis();

        logger.info("AdvancedFastBuilder Made By commandf1");
        logger.info("Version => " + this.getDescription().getVersion());
        logger.info("Start to load plugin...");

        this.setupConfigs();
        ListenerManager.init();
        ReplayUtils.register();
        PlayerDataManager.init();
        ArenaManager.init();
        new ScoreboardTask();
        this.setupJoinItem();

        logger.info("Loaded successfully. (" + (System.currentTimeMillis() - startTime) + " ms)");
    }

    private void setupJoinItem() {
        JoinItem replayItem = new JoinItem(ItemsConfig.REPLAY_ITEM.getValue(), 6, new Action() {
            @Override
            public <T extends HumanEntity> void run(T player) {

            }
        });
        replayItem.register();
    }

    private void setupConfigs() {
        if (PluginSettings.HAS_BEEN_INITED) {
            ConfigManager.init();
        }

        me.jumper251.replay.filesystem.ConfigManager.loadConfigs();
    }

    @Deprecated
    private void setupDepends() {
        final String[] softDepends = "PlaceholderAPI|cCore".split("\\|");
        final String[] depends = "ProtocolLib|AdvancedReplay".split("\\|");

        for (String softDepend : softDepends) {
            Plugin plugin = this.getPluginManager().getPluginManager().getPlugin(softDepend);
            if (plugin == null) {
                logger.warning(softDepend + " has not been installed.");
            }
        }

        for (String depend : depends) {
            Plugin plugin = this.getPluginManager().getPluginManager().getPlugin(depend);
            if (plugin == null) {
                logger.severe(depend + " has not been installed, unloading...");
                this.getPluginManager().disablePlugin(this);
                this.onDisable();
                return;
            }
        }
    }

    @Override
    public void onExit() throws Exception {
        instance = null;
        logger = null;

        if (PluginSettings.HAS_BEEN_INITED) {
            ConfigManager.save();
        }

        ReplayUtils.exit();
        ArenaManager.saveChanges();
        PlayerDataManager.saveChanges();
        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardUtils.disableScoreboard(player);
        }
    }

    public static void main(String[] args) {}
}
