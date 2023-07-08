package space.commandf1.fastbuilder.arena;

import org.bukkit.World;
import space.commandf1.fastbuilder.game.Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Arena implements Serializable {
    private final String name;
    private final World world;
    private final List<Game> games = new ArrayList<>();

    public Arena(String name, World world) {
        this.name = name;
        this.world = world;
    }

    public String getName() {
        return name;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public String toString() {
        return "Arena{world=" + this.world + ",name=" + this.name + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        }
        return  obj.toString().equals(this.toString());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        int hash = 114514;
        hash = 19 * hash + (this.world != null ? this.world.hashCode() : 0);
        hash = 19 * hash + name.hashCode();
        return hash;
    }

    public List<Game> getGames() {
        return games;
    }
}
