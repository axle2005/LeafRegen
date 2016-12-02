package io.github.axle2005.LeafRegen;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;



@Plugin(id = "leafregen", name = "LeafRegen", version = "1.0.0")
public class LeafRegen {

	@Inject
	private Logger log;
	
	@Listener
	public void initialization(GameInitializationEvent event) {
		EventListener<ChangeBlockEvent> listener = new ListenerBlockBreak(this);
		Sponge.getEventManager().registerListener(this, ChangeBlockEvent.Break.class, listener);
	}
	
	public Logger getLogger() {
		return log;
	}
}
