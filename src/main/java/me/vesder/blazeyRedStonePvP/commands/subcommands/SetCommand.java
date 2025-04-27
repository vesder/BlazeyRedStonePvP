package me.vesder.blazeyRedStonePvP.commands.subcommands;

import me.vesder.blazeyRedStonePvP.commands.SubCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetCommand extends SubCommand {


    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "Sets the specified gadget on block you are looking at";
    }

    @Override
    public String getSyntax() {
        return "/rs set <gadget>";
    }

    @Override
    public void perform(Player player, String[] args) {

    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {

        if (args.length == 2) {

            List<String> arguments = new ArrayList<>();
            arguments.add("RedstoneConverter");
            arguments.add("GoldConverter");
            arguments.add("EmeraldConverter");
            arguments.add("RepairAnvil");
            arguments.add("Frame");

            return arguments;
        }

        return List.of();
    }
}
