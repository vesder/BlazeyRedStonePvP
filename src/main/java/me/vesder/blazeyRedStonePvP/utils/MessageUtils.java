package me.vesder.blazeyRedStonePvP.utils;

import net.kyori.adventure.text.Component;

import java.util.List;

import static me.vesder.blazeyRedStonePvP.utils.TextUtils.color;

public class MessageUtils {

    private static final String prefix = "<#59FFD0>BRS > <reset>";

    public static String onlyPlayerMsg() {

        return "Only players can use this command";
    }

    public static Component blockNotFoundMsg() {

        return color(prefix + "<gradient:#F3904F:#CB2D3E>Block Mord Nazar Yaft Nashod</gradient>");
    }

    public static Component gadgetSetMsg(String gadgetName) {

        return color(prefix + "<gradient:#FFE259:#FFA751>" + gadgetName + " Ba Movafaghit Set Shod !</gradient>");
    }

    public static Component gadgetAlreadySetMsg(String gadgetName) {

        return color(prefix + "<gradient:#F3904F:#CB2D3E>Darhal Hazer " + gadgetName + " Roy In Block Set Shode !</gradient>");
    }

    public static Component subCmdHelpMsg(String syntax, List<String> arguments) {

        return color(prefix + "<gradient:#F3904F:#CB2D3E>" + syntax + "\nAvailable SubCommands : " + String.join(", ", arguments) + "</gradient>");
    }

}
