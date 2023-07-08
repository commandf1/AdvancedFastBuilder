package space.commandf1.fastbuilder.api;

import org.bukkit.Bukkit;
import space.commandf1.fastbuilder.api.exception.APINotEnableException;

/**
 * The api interface api
 * @author commandf1
 * @since 1.0
 * */
public interface FastBuilderAPI {
    /**
     * Get API instance
     * @since 1.0
     * @author commandf1
     * */
    static FastBuilderAPI getAPI() {
        FastBuilderAPI api = Bukkit.getServicesManager().getRegistration(FastBuilderAPI.class).getProvider();
        if (api == null) {
            throw new APINotEnableException();
        }

        return api;
    }

    /**
     * Get the version of the plugin
     * @since 1.0
     * @author commandf1
     * */
    String getVersion();
}
