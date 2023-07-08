package space.commandf1.fastbuilder.game;

import org.bukkit.Location;
import space.commandf1.fastbuilder.arena.Arena;

public class Game {
    private final Arena arena;
    private final int num;
    private final Location first, second, centre;

    public Game(Arena arena, int num, Location first, Location second, Location centre) {
        this.arena = arena;
        this.num = num;
        this.first = first;
        this.second = second;
        this.centre = centre;
    }

    public Arena getArena() {
        return arena;
    }

    public int getNum() {
        return num;
    }

    public Location getFirst() {
        return first;
    }

    public Location getSecond() {
        return second;
    }

    public Location getCentre() {
        return centre;
    }
}
