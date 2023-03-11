package dev.foggies.interactables.base;

import dev.foggies.interactables.Interactables;
import dev.foggies.interactables.storage.InteractableStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.lucko.helper.item.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

@AllArgsConstructor
@Getter
public abstract class Interactable {

    private final Interactables plugin;
    private final InteractableStorage storage;
    private final String identifier;
    private final String displayName;
    private final List<String> lore;
    private final int modelData;

    /**
     * Initialises the interactable item by storing the persistent
     * data within the item data so that it can be retrieved via events.
     * @return the transformed item.
     *
     * Note: MUST BE CREATED BEFORE MAKING YOUR OWN ITEM.
     */
    public ItemStack transformToInteractable(){
        return ItemStackBuilder.of(Material.STONE)
                .transformMeta(meta -> {
                    PersistentDataContainer container = meta.getPersistentDataContainer();
                    container.set(
                            new NamespacedKey(this.plugin, this.storage.getINTERACTABLE_KEY()),
                            PersistentDataType.STRING,
                            this.identifier
                    );
                }).build();
    }

    /**
     * Called when a player interacts with the item, i.e. right clicks.
     * @param event The event that was fired.
     */
    public abstract void onInteract(PlayerInteractEvent event);

    /**
     * Called when a player places this item.
     * @param event The event that was fired.
     */
    public abstract void onPlace(BlockPlaceEvent event);

    /**
     * Called when a player breaks a block while holding this item.
     * @param event The event that was fired.
     */
    public abstract void onBreak(BlockBreakEvent event);

    /**
     * Called when a player drops this item.
     * @param event The event that was fired.
     */
    public abstract void onDrop(PlayerDropItemEvent event);

    /**
     * Called when a player attempts to pick up this item.
     * @param event The event that was fired.
     */
    public abstract void onPickup(PlayerAttemptPickupItemEvent event);

    public abstract ItemStack buildInteractableItem();

}
