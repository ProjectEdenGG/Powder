package com.ruinscraft.powder.model.tracker;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.ruinscraft.powder.PowderPlugin;

public class StationaryTracker implements Tracker {

	private Location location;
	private UUID creator;

	public StationaryTracker(Location location, UUID creator) {
		this.location = location;
		this.creator = creator;
	}

	@Override
	public Tracker.Type getType() {
		return Tracker.Type.STATIONARY;
	}

	@Override
	public void refreshLocation() {
	}

	@Override
	public Location getCurrentLocation() {
		return location;
	}

	public UUID getCreator() {
		return creator;
	}

	@Override
	public boolean hasControl(Player possibleOwner) {
		return getCreator().equals(possibleOwner.getUniqueId());
	}

}
