package me.vesder.blazeyRedStonePvP.listeners;

import me.vesder.blazeyRedStonePvP.commands.subcommands.SetCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.vesder.blazeyRedStonePvP.utils.MessageUtils.sendInvFullMsg;
import static me.vesder.blazeyRedStonePvP.utils.TextUtils.*;

public class InteractListener implements Listener {

    private final List<String> gadgets;

    public InteractListener() {
        gadgets = new ArrayList<>(SetCommand.getSetCmdArgs());
        gadgets.remove("Frame");
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent event) {

        if (event.getHand() != EquipmentSlot.HAND || event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Location clickedLocation = Objects.requireNonNull(event.getClickedBlock()).getLocation();
        checkGadgetAtLocation(clickedLocation, gadgets)
                .ifPresent(gadget -> {

                    Player player = event.getPlayer();
                    event.setCancelled(true);

                    if (gadget.equals("RepairAnvil")) {

                        Location centerLocation = clickedLocation.clone().add(0.50, 1, 0.50);

                        Item droppedItem = event.getClickedBlock().getWorld()
                                .dropItem(centerLocation, player.getInventory().getItemInMainHand());

                        droppedItem.setPickupDelay(Integer.MAX_VALUE);
                        droppedItem.setVelocity(new Vector(0, 0, 0));

                        spawnParticles(player.getWorld(), Particle.BLOCK_CRACK, List.of(

                                centerLocation,
                                clickedLocation.clone().add(0.75, 1, 0.25),
                                clickedLocation.clone().add(0.25, 1, 0.75)

                        ), Material.ANVIL.createBlockData());

                        return;
                    }

                    int takeAmount = getIntConfig("Gadgets." + gadget + ".amount");
                    Material takeMat = Material.matchMaterial(getStringConfig("Gadgets." + gadget + ".take"));
                    ItemStack giveItem = new ItemStack(Objects.requireNonNull(Material.matchMaterial(getStringConfig("Gadgets." + gadget + ".give"))));

                    if (!player.getInventory().contains(Objects.requireNonNull(takeMat), takeAmount)) {

                        player.sendMessage(color(getStringConfig("Gadgets." + gadget + ".message"), "amount", takeAmount));
                        return;
                    }

                    if (player.getInventory().firstEmpty() == -1) {
                        sendInvFullMsg(player);
                        return;
                    }

                    player.getInventory().removeItem(new ItemStack(takeMat, takeAmount));
                    player.getInventory().addItem(giveItem);
                    playSoundFromString(player, getStringConfig("Gadgets." + gadget + ".sound"));

                });

    }


}
