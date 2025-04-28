package me.vesder.blazeyRedStonePvP.utils;

import net.kyori.adventure.text.Component;

import static me.vesder.blazeyRedStonePvP.utils.TextUtils.color;

public class MessageUtils {

    private static final String prefix = "<#59FFD0>BRS > <reset>";

    public static String onlyPlayerMsg() {

        return "Only players can use this command";
    }

    public static Component blockNotFound() {

        return color(prefix + "<gradient:#F3904F:#CB2D3E>Block Mord Nazar Yaft Nashod</gradient>");
    }

    public static Component gadgetSet(String gadget) {

        return color(prefix + "<gradient:#FFE259:#FFA751>" + gadget + " Ba Movafaghit Set Shod !</gradient>");
    }

}
