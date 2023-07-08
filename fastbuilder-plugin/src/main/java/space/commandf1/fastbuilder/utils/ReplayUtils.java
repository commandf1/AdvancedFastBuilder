package space.commandf1.fastbuilder.utils;

import me.jumper251.replay.filesystem.ConfigManager;
import me.jumper251.replay.filesystem.saving.DefaultReplaySaver;
import me.jumper251.replay.filesystem.saving.ReplaySaver;
import me.jumper251.replay.replaysystem.Replay;
import me.jumper251.replay.replaysystem.utils.ReplayCleanup;
import me.jumper251.replay.utils.ReplayManager;

import java.util.HashMap;

public class ReplayUtils {
    public static void register() {
        // ConfigManager.loadConfigs();
        ReplayManager.register();

        ReplaySaver.register(new DefaultReplaySaver());


        if (ConfigManager.CLEANUP_REPLAYS > 0) {
            ReplayCleanup.cleanupReplays();
        }
    }

    public static void exit() {
        for (Replay replay : new HashMap<>(ReplayManager.activeReplays).values()) {
            if (replay.isRecording() && replay.getRecorder().getData().getActions().size() > 0) {
                replay.getRecorder().stop(ConfigManager.SAVE_STOP);
            }
        }
    }
}
