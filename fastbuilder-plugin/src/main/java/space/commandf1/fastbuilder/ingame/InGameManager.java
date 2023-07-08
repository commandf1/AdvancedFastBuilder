package space.commandf1.fastbuilder.ingame;

import org.bukkit.entity.Player;
import space.commandf1.fastbuilder.utils.PlayerUtils;

import java.util.ArrayList;
import java.util.List;

public class InGameManager {
    private static final List<InGame> inGames = new ArrayList<>();

    public static List<InGame> getInGames() {
        return inGames;
    }

    public static void removeInGame(InGame inGame) {
        inGames.removeIf(inGame::equals);
    }

    public static InGame getInGame(Player player) {
        for (InGame inGame : inGames) {
            if (PlayerUtils.isSamePlayer(inGame.getPlayer(), player)) {
                return inGame;
            }
        }

        return null;
    }
}
