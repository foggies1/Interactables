package dev.foggies.interactables.storage;

import dev.foggies.interactables.base.Interactable;
import dev.foggies.interactables.Interactables;
import dev.foggies.interactables.impl.TestInteractableItem;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InteractableStorage {

   @Getter
   private final String INTERACTABLE_KEY = "interactable";
    private final Interactables plugin;
    private final Map<String, Interactable> interactableItems = new ConcurrentHashMap<>();

    public InteractableStorage(Interactables plugin) {
        this.plugin = plugin;
        addInteractable(new TestInteractableItem(plugin));
    }

    public Optional<Interactable> getInteractable(ItemStack itemStack) {
        PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(this.plugin, INTERACTABLE_KEY);
        return pdc.has(namespacedKey, PersistentDataType.STRING)
                ? Optional.of(getInteractable(pdc.get(namespacedKey, PersistentDataType.STRING)))
                : Optional.empty();
    }

    public void addInteractable(Interactable interactable) {
        this.interactableItems.put(interactable.getIdentifier(), interactable);
    }

    public Interactable getInteractable(String identifier) {
        return this.interactableItems.get(identifier);
    }

    public Map<String, Interactable> getInteractableItems() {
        return this.interactableItems;
    }


}
