package com.ruinscraft.powder.model.tracker;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.ruinscraft.powder.PowderPlugin;

public interface Tracker {

	Tracker.Type getType();

	void refreshLocation();

	Location getCurrentLocation();

	boolean hasControl(Player player);

	UUID getCreator();

	default String getFormattedLocation() {
		Location location = getCurrentLocation();
		return (location.getBlockX() + "x " + location.getBlockY() + "y " + location.getBlockZ() + "z");
	}

	enum Type {
		ENTITY,
		STATIONARY
	}

}
