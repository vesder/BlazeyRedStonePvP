package me.vesder.blazeyRedStonePvP.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static me.vesder.blazeyRedStonePvP.utils.TextUtils.color;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
//        subcommands.add(new ExplodeCommand());
//        subcommands.add(new FreezeCommand());
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command");
            return true;
        }

        if (args.length == 0) {

            player.sendMessage(color("<gradient:#EF473A:#CB2D3E>======================</gradient>"));
            for (SubCommand subCommand : getSubCommands()) {
                player.sendMessage(color("<gradient:#EF473A:#CB2D3E>" + subCommand.getSyntax() + "</gradient>"));
                player.sendMessage(color("<gradient:#EF473A:#CB2D3E>" + subCommand.getDescription() + "</gradient>"));
            }
            player.sendMessage(color("<gradient:#EF473A:#CB2D3E>======================</gradient>"));

            return true;
        }

        for (SubCommand subCommand : getSubCommands()) {
            if (args[0].equalsIgnoreCase(subCommand.getName())) {
                subCommand.perform(player, args);
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of();
    }
}
