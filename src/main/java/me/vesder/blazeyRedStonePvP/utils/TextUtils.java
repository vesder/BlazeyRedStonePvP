package me.vesder.blazeyRedStonePvP.utils;

import me.vesder.blazeyRedStonePvP.BlazeyRedStonePvP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

import static me.vesder.blazeyRedStonePvP.config.DataConfig.getDataConfig;

public class TextUtils {

    public static Component color(String string) {

        return MiniMessage.miniMessage().deserialize(string);

    }

    public static String getStringConfig(String path) {

        return BlazeyRedStonePvP.getPlugin().getConfig().getString(path);

    }

    public static List<String> getStringListConfig(String path) {

        return BlazeyRedStonePvP.getPlugin().getConfig().getStringList(path);

    }

    public static int getIntConfig(String path) {

        return BlazeyRedStonePvP.getPlugin().getConfig().getInt(path);

    }

    public static ConfigurationSection getConfigSection(String path) {

        return BlazeyRedStonePvP.getPlugin().getConfig().getConfigurationSection(path);

    }

    public static ConfigurationSection getMatFromConfig(String path) {

        return BlazeyRedStonePvP.getPlugin().getConfig().getConfigurationSection(path);

    }

    public static void playSoundFromString(Player player, String sound) {

        if (sound.isEmpty()) {
            return;
        }

        player.playSound(player.getLocation(), Sound.valueOf(sound.toUpperCase()), 1.0f, 1.0f);
    }


    public static Optional<String> checkGadgetAtLocation(Location locationToCheck, List<String> gadgetList) {

        for (String gadget : gadgetList) {
            for (String locationEntry : getDataConfig().getStringListData(gadget)) {

                String[] splitArray = locationEntry.split("/");

                Location gadgetLocation = new Location(
                        Bukkit.getWorld(splitArray[0]),
                        Integer.parseInt(splitArray[1]),
                        Integer.parseInt(splitArray[2]),
                        Integer.parseInt(splitArray[3])
                );

                if (gadgetLocation.equals(locationToCheck)) {
                    return Optional.of(gadget);
                }

            }
        }

        return Optional.empty();
    }
}
