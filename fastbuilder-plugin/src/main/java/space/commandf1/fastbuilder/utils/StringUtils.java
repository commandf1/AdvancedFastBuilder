package space.commandf1.fastbuilder.utils;

import java.util.List;

public class StringUtils {
    public static String toMinecraftColor(String text) {
        return text.replace("&", "§").replace("§§","&");
    }

    public static List<String> toMinecraftColor(List<String> list) {
        list.replaceAll(StringUtils::toMinecraftColor);
        return list;
    }
}
