package me.vesder.blazeyRedStonePvP.listeners;

import me.vesder.blazeyRedStonePvP.commands.subcommands.SetCommand;
import me.vesder.blazeyRedStonePvP.utils.TextUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.vesder.blazeyRedStonePvP.utils.TextUtils.getIntConfig;
import static me.vesder.blazeyRedStonePvP.utils.TextUtils.getStringConfig;

public class InteractListener implements Listener {

    private final List<String> gadgets;

    public InteractListener() {
        gadgets = new ArrayList<>(SetCommand.getSetCmdArgs());
        gadgets.remove("Frame");
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent event) {

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        TextUtils.checkGadgetAtLocation(Objects.requireNonNull(event.getClickedBlock()).getLocation(), gadgets).ifPresent(gadget -> {

            Player player = event.getPlayer();
            event.setCancelled(true);

            if (gadget.equals("RepairAnvil")) {

                player.sendMessage("Anvil");

                return;
            }

            int takeAmount = getIntConfig("Gadgets." + gadget + ".amount");
            Material takeMat = Material.matchMaterial(getStringConfig("Gadgets." + gadget + ".take"));
            ItemStack giveItem = new ItemStack(Objects.requireNonNull(Material.matchMaterial(getStringConfig("Gadgets." + gadget + ".give"))));

            if (!player.getInventory().contains(Objects.requireNonNull(takeMat), takeAmount)) {

                return;
            }

            // Check if the player's inventory has space, if not drop the item near the player
            if (player.getInventory().firstEmpty() == -1) {
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), giveItem); // Drop item at the killer's location
            } else {
                player.getInventory().addItem(giveItem); // Add the item to the killer's inventory
            }

        });

    }


}
