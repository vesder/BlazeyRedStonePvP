package me.vesder.blazeyRedStonePvP.utils;

import org.bukkit.entity.Player;

import java.util.List;

import static me.vesder.blazeyRedStonePvP.utils.TextUtils.color;

public class MessageUtils {

    private static final String prefix = "<#59FFD0>BRS > <reset>";

//    public static String onlyPlayerMsg() {
//
//        return "Only players can use this command";
//    }

    public static void sendBlockNotFoundMsg(Player player) {

        player.sendMessage(color(prefix + "<gradient:#F3904F:#CB2D3E>Block Mord Nazar Yaft Nashod</gradient>"));
    }

    public static void sendGadgetSetMsg(Player player, String gadgetName) {

        player.sendMessage(color(prefix + "<gradient:#FFE259:#FFA751>" + gadgetName + " Ba Movafaghit Set Shod !</gradient>"));
    }

    public static void sendGadgetAlreadySetMsg(Player player, String gadgetName) {

        player.sendMessage(color(prefix + "<gradient:#F3904F:#CB2D3E>Darhal Hazer " + gadgetName + " Roy In Block Set Shode !</gradient>"));
    }

    public static void sendSubCmdHelpMsg(Player player, String syntax, List<String> arguments) {

        player.sendMessage(color(prefix + "<gradient:#F3904F:#CB2D3E>" + syntax + "\nAvailable SubCommands : " + String.join(", ", arguments) + "</gradient>"));
    }

    public static void sendInvFullMsg(Player player) {

        player.sendMessage(color(prefix + "<gradient:#F3904F:#CB2D3E>INV FULL</gradient>"));
    }

}
