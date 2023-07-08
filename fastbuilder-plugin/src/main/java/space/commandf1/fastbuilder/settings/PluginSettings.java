package space.commandf1.fastbuilder.settings;

public class PluginSettings {
    public static final boolean HAS_BEEN_INITED;

    static {
        HAS_BEEN_INITED = true; // !PluginUtils.isFirstLoad();
    }
}
