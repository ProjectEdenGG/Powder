package com.ruinscraft.powder.model.tracker;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.ruinscraft.powder.PowderPlugin;
import com.ruinscraft.powder.integration.PlotSquaredHandler;

public interface Tracker {

	Tracker.Type getType();

	void refreshLocation();

	Location getCurrentLocation();

	boolean hasControl(Player player);

	UUID getCreator();

	default String getFormattedLocation() {
		Location location = getCurrentLocation();
		if (PowderPlugin.get().hasPlotSquared()) {
			PlotSquaredHandler plotsquared = PowderPlugin.get().getPlotSquaredHandler();
			String formatted = plotsquared.getFormattedLocation(location);
			if (formatted != null) return formatted;
		}
		return (location.getBlockX() + "x " + location.getBlockY() + "y " + location.getBlockZ() + "z");
	}

	enum Type {
		ENTITY,
		STATIONARY
	}

}
