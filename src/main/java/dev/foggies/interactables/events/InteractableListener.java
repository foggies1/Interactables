package dev.foggies.interactables.events;

import dev.foggies.interactables.base.Interactable;
import dev.foggies.interactables.Interactables;
import dev.foggies.interactables.storage.InteractableStorage;
import me.lucko.helper.Events;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class InteractableListener implements Listener {

    private final Interactables plugin;
    private final InteractableStorage storage;

    public InteractableListener(Interactables plugin, InteractableStorage storage) {
        this.plugin = plugin;
        this.storage = storage;

        Events.subscribe(PlayerInteractEvent.class)
                .handler(event -> {
                    Player player = event.getPlayer();
                    ItemStack itemInHand = player.getInventory().getItemInMainHand();
                    Optional<Interactable> optional = this.storage.getInteractable(itemInHand);
                    if(optional.isEmpty()) return;
                    Interactable interactable = optional.get();
                    interactable.onInteract(event);
                });

        Events.subscribe(BlockPlaceEvent.class)
                .handler(event -> {
                    Player player = event.getPlayer();
                    ItemStack itemInHand = player.getInventory().getItemInMainHand();
                    Optional<Interactable> optional = this.storage.getInteractable(itemInHand);
                    if(optional.isEmpty()) return;
                    Interactable interactable = optional.get();
                    interactable.onPlace(event);
                });

        Events.subscribe(BlockBreakEvent.class)
                .handler(event -> {
                    Player player = event.getPlayer();
                    ItemStack itemInHand = player.getInventory().getItemInMainHand();
                    Optional<Interactable> optional = this.storage.getInteractable(itemInHand);
                    if(optional.isEmpty()) return;
                    Interactable interactable = optional.get();
                    interactable.onBreak(event);
                });

        Events.subscribe(PlayerDropItemEvent.class)
                .handler(event -> {
                    ItemStack pickedUpItem = event.getItemDrop().getItemStack();
                    Optional<Interactable> optional = this.storage.getInteractable(pickedUpItem);
                    if(optional.isEmpty()) return;
                    Interactable interactable = optional.get();
                    interactable.onDrop(event);
                });

        Events.subscribe(PlayerAttemptPickupItemEvent.class)
                .handler(event -> {
                    ItemStack pickedUpItem = event.getItem().getItemStack();
                    Optional<Interactable> optional = this.storage.getInteractable(pickedUpItem);
                    if(optional.isEmpty()) return;
                    Interactable interactable = optional.get();
                    interactable.onPickup(event);
                });

    }




}
