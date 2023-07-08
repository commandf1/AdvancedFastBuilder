package me.jumper251.replay.utils;

import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;

public class LogUtils {

	@Deprecated
	public static void log(String message){
		AdvancedFastBuilderPlugin.getInstance().getLogger().info(message);
	}

}
