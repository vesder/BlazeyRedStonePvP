package me.vesder.blazeyRedStonePvP.utils;

import me.vesder.blazeyRedStonePvP.BlazeyRedStonePvP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

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

}
