package me.vesder.blazeyRedStonePvP.utils;

import net.kyori.adventure.text.Component;

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

        return color(prefix + "<gradient:#FFE259:#FFA751>Darhal Hazer" + gadgetName + " Roy In Block Set Shode Ast !</gradient>");
    }

}
