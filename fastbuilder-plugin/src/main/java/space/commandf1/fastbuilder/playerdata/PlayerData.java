package space.commandf1.fastbuilder.playerdata;

import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.UUID;

public class PlayerData implements Serializable {
    private final UUID uuid;
    private long best = -1;

    public PlayerData(Player player) {
        this.uuid = player.getUniqueId();
    }

    public long getBest() {
        return best;
    }

    public void setBest(long best) {
        this.best = best;
    }

    public UUID getUuid() {
        return uuid;
    }
}
