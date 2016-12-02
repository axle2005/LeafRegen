package io.github.axle2005.LeafRegen;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.filter.type.Include;
import org.spongepowered.api.scheduler.Scheduler;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.world.BlockChangeFlag;

public class ListenerBlockBreak implements EventListener<ChangeBlockEvent> {

	LeafRegen plugin;

	public ListenerBlockBreak(LeafRegen plugin) {
		this.plugin = plugin;
	}

	@Override
	@Include({ ChangeBlockEvent.Decay.class, ChangeBlockEvent.Break.class })
	public void handle(ChangeBlockEvent event) throws Exception {
		@SuppressWarnings("unused")
		Task restore;
		Scheduler scheduler = Sponge.getScheduler();
		Task.Builder taskBuilder = scheduler.createTaskBuilder();

			
		if (!(event.getTransactions().isEmpty())) {
			BlockSnapshot leaf = event.getTransactions().get(0).getOriginal();
			String block = leaf.getState().getType().getId();
			if (block.equals("minecraft:leaves") || block.equals("minecraft:log")) {
				if(!(leaf.getCreator().isPresent()))
				{
			restore = taskBuilder.execute(() -> restore(leaf)).async().delayTicks(200).submit(plugin);
				}
			}
		}
	}

	private void restore(BlockSnapshot block) {
		if (block.getLocation().get().getBlockType().getId().equals("minecraft:air")) {
			block.restore(true, BlockChangeFlag.NONE);
		} 

	}
}