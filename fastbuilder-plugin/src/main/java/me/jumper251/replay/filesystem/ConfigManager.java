package me.jumper251.replay.filesystem;

import me.jumper251.replay.replaysystem.recording.optimization.ReplayQuality;
import space.commandf1.fastbuilder.config.configs.ReplayConfig;
import space.commandf1.fastbuilder.config.configs.SettingsConfig;

public class ConfigManager {

	public static int MAX_LENGTH, CLEANUP_REPLAYS = -1;

	public static boolean RECORD_BLOCKS = true, REAL_CHANGES = false;
	public static boolean RECORD_ITEMS = true, RECORD_ENTITIES = false;
	public static boolean RECORD_CHAT = false;
	public static boolean SAVE_STOP = false, RECORD_STARTUP = false, USE_OFFLINE_SKINS, HIDE_PLAYERS = true, ADD_PLAYERS = false;
	public static boolean WORLD_RESET = true;

	public static ReplayQuality QUALITY = ReplayQuality.HIGH;

	public static String DEATH_MESSAGE = "", LEAVE_MESSAGE = "", CHAT_FORMAT = "", JOIN_MESSAGE = "";

	public static void loadConfigs() {
		long time = SettingsConfig.MAX_TIME.getValue() / 1000L;
		MAX_LENGTH = (int) time;// ReplayConfig.MAX_LENGTH.getValue();
		USE_OFFLINE_SKINS = ReplayConfig.OFFLINE_SKINS.getValue();
	}

    /*
	public static void loadConfigs() {
		if(!sqlFile.exists()){
			sqlCfg.set("host", "localhost");
			sqlCfg.set("port", 3306);
			sqlCfg.set("username", "username");
			sqlCfg.set("database", "database");
			sqlCfg.set("password", "password");
			sqlCfg.set("prefix", "");

			try {
				sqlCfg.save(sqlFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (!file.exists()) {
			LogUtils.log("Creating Config files...");
			
			cfg.set("general.max_length", 3600);
			cfg.set("general.record_on_startup", false);
			cfg.set("general.save_on_stop", false);
			cfg.set("general.use_mysql", false);
			cfg.set("general.use_offline_skins", true);
			cfg.set("general.quality", "high");
			cfg.set("general.cleanup_replays", -1);
			cfg.set("general.hide_players", false);
			cfg.set("general.add_new_players", false);	
			cfg.set("general.update_notifications", true);
			
			cfg.set("general.death_message", "&6{name} &7died.");
			cfg.set("general.quit_message", "&6{name} &7left the game.");
			cfg.set("general.join_message", "&6{name} &7joined the game.");

			cfg.set("replaying.world.reset_changes", false);
			
			cfg.set("recording.blocks.enabled", true);
			cfg.set("recording.blocks.real_changes", true);
			cfg.set("recording.entities.enabled", false);
			cfg.set("recording.entities.items.enabled", true);
			cfg.set("recording.chat.enabled", false);
			cfg.set("recording.chat.format", "&r<{name}> {message}");


			try {
				cfg.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		ItemConfig.loadConfig();
		
		loadData(true);
		
	}
	*/
    /*
	public static void loadData(boolean initial) {
		MAX_LENGTH = cfg.getInt("general.max_length");
		SAVE_STOP = cfg.getBoolean("general.save_on_stop");
		RECORD_STARTUP = cfg.getBoolean("general.record_on_startup", false);
		USE_OFFLINE_SKINS = cfg.getBoolean("general.use_offline_skins");
		QUALITY = ReplayQuality.valueOf(cfg.getString("general.quality", "high").toUpperCase());
		HIDE_PLAYERS = cfg.getBoolean("general.hide_players");
		CLEANUP_REPLAYS = cfg.getInt("general.cleanup_replays", -1);
		ADD_PLAYERS = cfg.getBoolean("general.add_new_players");
		if (initial ) USE_DATABASE = cfg.getBoolean("general.use_mysql");

		DEATH_MESSAGE = cfg.getString("general.death_message");
		LEAVE_MESSAGE = cfg.getString("general.quit_message");
		JOIN_MESSAGE = cfg.getString("general.join_message");
		
		WORLD_RESET = cfg.getBoolean("replaying.world.reset_changes", false);
		
		CHAT_FORMAT = cfg.getString("recording.chat.format");
		RECORD_BLOCKS = cfg.getBoolean("recording.blocks.enabled");
		REAL_CHANGES = cfg.getBoolean("recording.blocks.real_changes");
		RECORD_ITEMS = cfg.getBoolean("recording.entities.items.enabled");
		RECORD_ENTITIES = cfg.getBoolean("recording.entities.enabled");
		RECORD_CHAT = cfg.getBoolean("recording.chat.enabled");

		if (USE_DATABASE) {
			
			String host = sqlCfg.getString("host");
			int port = sqlCfg.getInt("port", 3306);
			String username = sqlCfg.getString("username");
			String database = sqlCfg.getString("database");
			String password = sqlCfg.getString("password");
			String prefix = sqlCfg.getString("prefix", "");

			MySQLDatabase mysql = new MySQLDatabase(host, port, database, username, password, prefix);
			DatabaseRegistry.registerDatabase(mysql);
			DatabaseRegistry.getDatabase().getService().createReplayTable();
			
		}


		ItemConfig.loadData();
	}
	
	public static void reloadConfig() {
		try {
			cfg.load(file);
			ItemConfig.cfg.load(ItemConfig.file);
		} catch (InvalidConfigurationException | IOException e) {
			e.printStackTrace();
		}

		loadData(false);
	}
	*/
}
