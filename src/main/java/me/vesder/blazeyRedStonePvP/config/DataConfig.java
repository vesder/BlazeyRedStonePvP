package me.vesder.blazeyRedStonePvP.config;

import me.vesder.blazeyRedStonePvP.BlazeyRedStonePvP;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class DataConfig {

    private final static DataConfig instance = new DataConfig();

    private File file;
    private YamlConfiguration config;

    private DataConfig() {

    }

    public void load() {

        file = new File(BlazeyRedStonePvP.getPlugin().getDataFolder(), "data.yml");

        if (!file.exists()) {
            BlazeyRedStonePvP.getPlugin().saveResource("data.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception ignored) {

        }

    }

    public void save() {

        try {
            config.save(file);
        } catch (Exception ignored) {

        }

    }

    public void set(String path, Object value) {
        config.set(path, value);

        save();
    }

    public List<String> getStringListData(String path) {

        return config.getStringList(path);
    }

    public static DataConfig getDataConfig() {
        return instance;
    }
}
