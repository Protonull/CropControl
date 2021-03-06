package com.programmerdan.minecraft.cropcontrol.handler;

import com.programmerdan.minecraft.cropcontrol.CropControl;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import vg.civcraft.mc.citadel.events.ReinforcementAcidBlockedEvent;

/**
 * ACID block breaks are an interesting edge case. Citadel directly sets the reinforced block to air.
 * There are several potential break paths in this case, so we just forward the "break" to our handleBreak routine
 * as a lightweight normal break wrapper and move on with life.
 * 
 * @author ProgrammerDan
 *
 */
public class CitadelEventHandler implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void handleAcidBreak(ReinforcementAcidBlockedEvent e) {
		try {
			Location acided = e.getReinforcement().getLocation();
			
			CropControl.getPlugin().getEventHandler().onBlockBreak(new BlockBreakEvent( acided.getBlock(), e.getPlayer() ) );
		} catch (Exception g) {
			CropControl.getPlugin().warning("Failed to handle Citadel Acid Block Event:", g);
		}
	}
}
