package me.vesder.blazeyRedStonePvP;

import org.bukkit.plugin.java.JavaPlugin;

import static me.vesder.blazeyRedStonePvP.utils.TextUtils.color;
import static org.bukkit.Bukkit.getConsoleSender;

public final class BlazeyRedStonePvP extends JavaPlugin {

    private static BlazeyRedStonePvP plugin;

    public static BlazeyRedStonePvP getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {

        // Register the plugin instance
        plugin = this;

        // Save the default configuration if it doesn't already exist
//        saveDefaultConfig();

        // Create and start the CombatLog task for periodic updates of BossBars
//        DamageListener damageListener = new DamageListener();
//        damageListener.startCombatLogTask(); // Start the periodic update task for BossBars

        // Register events (Listeners) for various classes
//        getPluginManager().registerEvents(damageListener, this);
//        getPluginManager().registerEvents(new InteractListener(), this);
//        getPluginManager().registerEvents(new TaxSystem(), this);

        // Register commands
//        Objects.requireNonNull(getCommand("vent-add")).setExecutor(new AddVentCommand());

        // Check if the config was just created (i.e. it didn't exist before)

        // Print a startup message in the server console
        getConsoleSender().sendMessage(color("<dark_purple>=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★"));
        getConsoleSender().sendMessage(color("<dark_purple>       BlazeyRedStonePvP  "));
        getConsoleSender().sendMessage(""); // Blank line for readability
        getConsoleSender().sendMessage(color("<dark_purple>      V:1.0.0    "));
        getConsoleSender().sendMessage(color("<dark_purple>      Made By @Vesder      "));
        getConsoleSender().sendMessage(color("<dark_purple>  Join Our Discord For Support"));
        getConsoleSender().sendMessage(color("<dark_purple>=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= ★"));

    }

}