package me.vesder.blazeyRedStonePvP.commands.subcommands;

import me.vesder.blazeyRedStonePvP.commands.SubCommand;
import me.vesder.blazeyRedStonePvP.config.DataConfig;
import org.bukkit.Location;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.vesder.blazeyRedStonePvP.utils.TextUtils.color;

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

        if (args.length == 1 || !args[1].matches("RedstoneConverter|GoldConverter|EmeraldConverter|RepairAnvil|Frame")) {
            player.sendMessage("ERROR");
            return;
        }

        try {

            List<String> dataNewList = new ArrayList<>(DataConfig.getInstance().getStringListData(args[1]));

            Location blockLoc = player.getTargetBlockExact(5).getLocation();

            if (args[1].equalsIgnoreCase("Frame")) {
                if (player.getTargetEntity(5) instanceof ItemFrame frame) {

                    dataNewList.add(
                            blockLoc.getWorld().getName() + "/" +
                                    blockLoc.getBlockX() + "/" +
                                    blockLoc.getBlockY() + "/" +
                                    blockLoc.getBlockZ() + "/" +
                                    frame.getItem().getType()
                    );

                } else {
                    player.sendMessage(color("<gradient:#F3904F:#CB2D3E>Block Mord Nazar Yaft Nashod</gradient>"));
                    return;
                }

            } else {

                dataNewList.add(
                        blockLoc.getWorld().getName() + "/" +
                        blockLoc.getBlockX() + "/" +
                        blockLoc.getBlockY() + "/" +
                        blockLoc.getBlockZ()
                );
            }

            DataConfig.getInstance().set(args[1],dataNewList);
            player.sendMessage(color("<gradient:#FFE259:#FFA751>" + args[1] + " Ba Movafaghit Set Shod !</gradient>"));

        }catch (NullPointerException ex) {
            player.sendMessage(color("<gradient:#F3904F:#CB2D3E>Block Mord Nazar Yaft Nashod</gradient>"));
        }

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
