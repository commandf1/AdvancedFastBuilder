package space.commandf1.fastbuilder.arena;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ArenaManager {
    private static final List<Arena> arenas = new ArrayList<>();
    private static final File arenasFile = new File(AdvancedFastBuilderPlugin.getInstance().getDataFolder(), "arenas");
    private static final List<Arena> newArenas = new ArrayList<>();

    public static void registerArena(Arena arena) {
        newArenas.add(arena);
    }

    public static void init() {
        if (!arenasFile.exists()) {
            arenasFile.mkdirs();
        }

        File[] files = arenasFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.getName().toLowerCase().endsWith(".arena")) {
                    continue;
                }
                try (BukkitObjectInputStream in = new BukkitObjectInputStream(Files.newInputStream(file.toPath()))) {
                    arenas.add((Arena) in.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void saveChanges() throws IOException {
        for (Arena newArena : newArenas) {
            File newArenaFile = new File(arenasFile, newArena.getName() + ".arena");
            BukkitObjectOutputStream objectOutputStream = new BukkitObjectOutputStream(Files.newOutputStream(newArenaFile.toPath()));
            objectOutputStream.writeObject(newArena);
            objectOutputStream.close();
        }

        newArenas.clear();
    }

    public static List<Arena> getArenas() {
        return arenas;
    }

    public static File getArenasFile() {
        return arenasFile;
    }

    public static List<Arena> getNewArenas() {
        return newArenas;
    }
}
