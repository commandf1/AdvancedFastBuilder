package space.commandf1.fastbuilder.game;

import org.bukkit.Location;
import space.commandf1.fastbuilder.utils.LocationUtils;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static final List<Game> games = new ArrayList<>();

    public static Game getGameByLocation(Location location) {
        for (Game game : games) {
            if (LocationUtils.isLocationInRegion(game.getFirst(), game.getSecond(), location)) {
                return game;
            }
        }

        return null;
    }

    public static List<Game> getGames() {
        return games;
    }
}
