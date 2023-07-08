package me.jumper251.replay.replaysystem.utils;

import me.jumper251.replay.filesystem.ConfigManager;
import me.jumper251.replay.filesystem.saving.DefaultReplaySaver;
import me.jumper251.replay.filesystem.saving.ReplaySaver;
import org.bukkit.Bukkit;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class ReplayCleanup {

	public static void cleanupReplays() {
		List<String> replays = ReplaySaver.getReplays();
		
		Bukkit.getScheduler().runTaskAsynchronously(AdvancedFastBuilderPlugin.getInstance(), () -> replays.forEach(ReplayCleanup::checkAndDelete));
	}
	
	private static void checkAndDelete(String replay) {
		LocalDate creationdDate = getCreationDate(replay);
		LocalDate threshold = LocalDate.now().minusDays(ConfigManager.CLEANUP_REPLAYS);

		if (creationdDate.isBefore(threshold)) {
			// LogUtils.log("Replay " + replay + " has expired. Removing it...");
			ReplaySaver.delete(replay);
		}
	}
	
	private static LocalDate getCreationDate(String replay) {
		if (ReplaySaver.replaySaver instanceof DefaultReplaySaver) {
			return fromMillis(new File(DefaultReplaySaver.DIR, replay + ".replay").lastModified());
		}
		
		return LocalDate.now();
	}
	
	private static LocalDate fromMillis(long millis) {
		return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();

	}
}
