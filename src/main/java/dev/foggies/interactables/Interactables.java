package dev.foggies.interactables;

import dev.foggies.interactables.events.InteractableListener;
import dev.foggies.interactables.storage.InteractableStorage;
import lombok.Getter;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import org.bukkit.plugin.java.JavaPlugin;


@Getter
public final class Interactables extends ExtendedJavaPlugin {

    private InteractableStorage storage;

    @Override
    public void enable() {
        this.storage = new InteractableStorage(this);
        new InteractableListener(this, this.storage);
    }

    @Override
    public void disable() {
        // Plugin shutdown logic
    }
}
