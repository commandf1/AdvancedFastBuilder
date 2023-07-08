package space.commandf1.fastbuilder.utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class EntityUtils {
    public static void spawnFireWorks(Location location) {
        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        FireworkEffect.Type type = FireworkEffect.Type.values()[new Random().nextInt(FireworkEffect.Type.values().length)];
        Color color = Color.fromRGB(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
        Color fade = Color.fromRGB(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
        FireworkEffect effect = FireworkEffect.builder()
                .flicker(new Random().nextBoolean())
                .withColor(color)
                .withFade(fade)
                .with(type)
                .trail(new Random().nextBoolean())
                .build();
        meta.addEffect(effect);
        meta.setPower(1);
        firework.setFireworkMeta(meta);
    }
}
