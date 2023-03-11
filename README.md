You can use this to create your own Items that will allow you to interact with them and run a 
variety of events.

Example

```java

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

```
