package me.vesder.blazeyRedStonePvP.commands.subcommands;

import me.vesder.blazeyRedStonePvP.commands.SubCommand;
import me.vesder.blazeyRedStonePvP.config.DataConfig;
import me.vesder.blazeyRedStonePvP.utils.MessageUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.vesder.blazeyRedStonePvP.utils.TextUtils.color;

public class SetCommand extends SubCommand {

    List<String> arguments = List.of(

            "RedstoneConverter",
            "GoldConverter",
            "EmeraldConverter",
            "RepairAnvil",
            "Frame"
    );

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

        if (args.length == 1 || !args[1].matches(String.join("|", arguments))) {

            player.sendMessage(color(
                    "<gradient:#F3904F:#CB2D3E>Available gadgets : \n" +
                            String.join(", ", arguments) +
                            "\n" + getSyntax() + "</gradient>"
            ));

            return;
        }

        try {

            List<String> dataNewList = new ArrayList<>(DataConfig.getInstance().getStringListData(args[1]));

            Location blockLoc = player.getTargetBlockExact(5).getLocation();

            if (args[1].equalsIgnoreCase("Frame")) {

                if (!(player.getTargetEntity(5) instanceof ItemFrame frame)) {

                    player.sendMessage(MessageUtils.blockNotFound());
                    return;

                }


                try {

                    dataNewList.add(
                            blockLoc.getWorld().getName() + "/" +
                                    blockLoc.getBlockX() + "/" +
                                    blockLoc.getBlockY() + "/" +
                                    blockLoc.getBlockZ() + "/" +
                                    frame.getItem().getType() + "/" +
                                    Integer.parseInt(args[2])
                    );

                } catch (Exception ex) {

                    dataNewList.add(
                            blockLoc.getWorld().getName() + "/" +
                                    blockLoc.getBlockX() + "/" +
                                    blockLoc.getBlockY() + "/" +
                                    blockLoc.getBlockZ() + "/" +
                                    frame.getItem().getType() + "/" +
                                    5
                    );

                }

            } else if (args[1].equalsIgnoreCase("RepairAnvil")) {

                if (player.getTargetBlockExact(5).getType() != Material.ANVIL) {
                    player.sendMessage(MessageUtils.blockNotFound());
                    return;
                }

                dataNewList.add(
                        blockLoc.getWorld().getName() + "/" +
                                blockLoc.getBlockX() + "/" +
                                blockLoc.getBlockY() + "/" +
                                blockLoc.getBlockZ()
                );

            } else {

                dataNewList.add(
                        blockLoc.getWorld().getName() + "/" +
                                blockLoc.getBlockX() + "/" +
                                blockLoc.getBlockY() + "/" +
                                blockLoc.getBlockZ()
                );
            }

            DataConfig.getInstance().set(args[1], dataNewList);
            player.sendMessage(MessageUtils.gadgetSet(args[1]));

        } catch (NullPointerException ex) {
            player.sendMessage(MessageUtils.blockNotFound());
        }

    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {

        if (args.length == 2) {

            return arguments;
        }

        if (args.length == 3 && args[1].equalsIgnoreCase("Frame")) {

            return List.of("5");
        }

        return List.of();
    }
}
