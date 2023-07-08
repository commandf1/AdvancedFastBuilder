package space.commandf1.fastbuilder.playerdata;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import space.commandf1.fastbuilder.AdvancedFastBuilderPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PlayerDataManager {
    private static final List<PlayerData> playerdatas = new ArrayList<>();
    private static final File playerdatasFile = new File(AdvancedFastBuilderPlugin.getInstance().getDataFolder(), "playerdata");
    private static final List<PlayerData> newPlayerdatas = new ArrayList<>();

    public static List<PlayerData> getPlayerdatas() {
        return playerdatas;
    }

    public static void init() {
        if (!playerdatasFile.exists()) {
            playerdatasFile.mkdirs();
        }

        playerdatas.clear();

        File[] files = playerdatasFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.getName().toLowerCase().endsWith(".playerdata")) {
                    continue;
                }
                try (BukkitObjectInputStream in = new BukkitObjectInputStream(Files.newInputStream(file.toPath()))) {
                    playerdatas.add((PlayerData) in.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void replacePlayerData(PlayerData thePlayerData) {
        for (int i = 0; i < getPlayerdatas().size(); i++) {
            PlayerData playerData = playerdatas.get(i);
            if (playerData.getUuid().equals(thePlayerData.getUuid())) {
                playerdatas.set(i, thePlayerData);
            }
        }
    }

    public static PlayerData getPlayerDataByPlayer(Player player) {
        for (PlayerData playerdata : getPlayerdatas()) {
            if (playerdata.getUuid().equals(player.getUniqueId())) {
                return playerdata;
            }
        }

        return null;
    }

    public static void saveChanges() throws IOException {
        for (PlayerData playerData : newPlayerdatas) {
            File newPlayerDataFile = new File(playerdatasFile, Bukkit.getOfflinePlayer(playerData.getUuid()).getName() + ".playerdata");
            BukkitObjectOutputStream objectOutputStream = new BukkitObjectOutputStream(Files.newOutputStream(newPlayerDataFile.toPath()));
            objectOutputStream.writeObject(playerData);
            objectOutputStream.close();
        }

        newPlayerdatas.clear();

        init();
    }

    public static List<PlayerData> getNewPlayerdatas() {
        return newPlayerdatas;
    }
}
