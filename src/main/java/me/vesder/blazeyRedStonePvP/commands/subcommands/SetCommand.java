package me.vesder.blazeyRedStonePvP.commands.subcommands;

import me.vesder.blazeyRedStonePvP.commands.SubCommand;
import me.vesder.blazeyRedStonePvP.config.DataConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.vesder.blazeyRedStonePvP.utils.MessageUtils.sendSubCmdHelpMsg;
import static me.vesder.blazeyRedStonePvP.utils.MessageUtils.sendGadgetAlreadySetMsg;
import static me.vesder.blazeyRedStonePvP.utils.MessageUtils.sendGadgetSetMsg;
import static me.vesder.blazeyRedStonePvP.utils.MessageUtils.sendBlockNotFoundMsg;
import static me.vesder.blazeyRedStonePvP.utils.TextUtils.checkGadgetAtLocation;

public class SetCommand extends SubCommand {

    private static final List<String> arguments = List.of(

            "RedstoneConverter",
            "GoldConverter",
            "EmeraldConverter",
            "RepairAnvil",
            "Frame"
    );

    public static List<String> getSetCmdArgs() {
        return arguments;
    }

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

            sendSubCmdHelpMsg(player, getSyntax(), arguments);

            return;
        }

        try {

            List<String> dataNewList = new ArrayList<>(DataConfig.getInstance().getStringListData(args[1]));

            Location blockLoc = Objects.requireNonNull(player.getTargetBlockExact(5)).getLocation();

            checkGadgetAtLocation(blockLoc, arguments).ifPresentOrElse(
                    gadget -> sendGadgetAlreadySetMsg(player, gadget),
                    () -> {

                        if (args[1].equalsIgnoreCase("Frame")) {

                            if (!(player.getTargetEntity(5) instanceof ItemFrame frame)) {

                                sendBlockNotFoundMsg(player);
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

                            Material blockType = Objects.requireNonNull(player.getTargetBlockExact(5)).getType();

                            if (blockType != Material.ANVIL && blockType != Material.CHIPPED_ANVIL && blockType != Material.DAMAGED_ANVIL) {
                                sendBlockNotFoundMsg(player);
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
                        sendGadgetSetMsg(player, args[1]);
                    });

        } catch (NullPointerException ex) {
            sendBlockNotFoundMsg(player);
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
