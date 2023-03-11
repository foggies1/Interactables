package dev.foggies.interactables.impl;

import dev.foggies.interactables.Interactables;
import dev.foggies.interactables.base.Interactable;
import me.lucko.helper.item.ItemStackBuilder;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TestInteractableItem extends Interactable {

    public TestInteractableItem(Interactables plugin) {
        super(
                plugin,
                plugin.getStorage(),
                "test_interactable_item",
                "Test Interactable Item",
                List.of("This is a test interactable item."),
                15
        );
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if(action != Action.RIGHT_CLICK_AIR) return;
        player.setGameMode(GameMode.CREATIVE);
        event.setCancelled(true);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);
        event.setCancelled(true);
    }

    @Override
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.ADVENTURE);
    }

    @Override
    public void onDrop(PlayerDropItemEvent event) {
        Location location = event.getItemDrop().getLocation();
        location.getWorld().spawnParticle(org.bukkit.Particle.FLAME, location, 10);
    }

    @Override
    public void onPickup(PlayerAttemptPickupItemEvent event) {
        Player player = event.getPlayer();
        player.getLocation().getWorld().strikeLightning(player.getLocation());
        event.setCancelled(true);
    }

    @Override
    public ItemStack buildInteractableItem() {
        return ItemStackBuilder.of(transformToInteractable())
                .name(this.getDisplayName())
                .lore(this.getLore())
                .data(this.getModelData())
                .build();
    }


}
