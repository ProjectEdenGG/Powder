package com.ruinscraft.powder.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;

import com.ruinscraft.powder.models.trackers.EntityTracker;
import com.ruinscraft.powder.models.trackers.PlayerTracker;
import com.ruinscraft.powder.models.trackers.Tracker;
import com.ruinscraft.powder.models.trackers.TrackerType;

public class PowderTask {

	// name associated with this PowderTask (null if no player)
	private String name;
	// TrackerType associated with the Powders in this PowderTask
	private TrackerType trackerType;
	// Powder associated with this PowderTask
	private Map<Powder, Tracker> powders;

	public PowderTask(String name, Powder powder, Tracker tracker) {
		this.name = name;
		this.powders = new HashMap<Powder, Tracker>();
		this.powders.put(powder.clone(), tracker);
		trackerType = tracker.getType();
	}

	public PowderTask(Powder powder, Tracker tracker) {
		this.name = null;
		this.powders = new HashMap<Powder, Tracker>();
		this.powders.put(powder.clone(), tracker);
		trackerType = tracker.getType();
	}

	public Set<UUID> getUUIDsIfExist() {
		Set<UUID> uuids = new HashSet<UUID>();
		for (Tracker tracker : this.powders.values()) {
			if (getUUIDIfExist(tracker) != null) {
				uuids.add(getUUIDIfExist(tracker));
			}
		}
		return uuids;
	}

	public UUID getUUIDIfExist(Tracker unknownTracker) {
		if (trackerType == TrackerType.ENTITY) {
			EntityTracker tracker = (EntityTracker) unknownTracker;
			return tracker.getEntityUUID();
		} else if (trackerType == TrackerType.PLAYER) {
			PlayerTracker tracker = (PlayerTracker) unknownTracker;
			return tracker.getUUID();
		}
		return null;
	}

	public Set<UUID> cancel() {
		Set<UUID> uuidsToSave = getUUIDsIfExist();
		this.powders.clear();
		return uuidsToSave;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getFirstLocation() {
		for (Powder powder : powders.keySet()) {
			return powders.get(powder).getCurrentLocation();
		}
		return null;
	}

	public List<Location> getLocations() {
		List<Location> locations = new ArrayList<Location>();
		for (Tracker tracker : powders.values()) {
			locations.add(tracker.getCurrentLocation());
		}
		return locations;
	}

	public Map<Powder, Tracker> getPowders() {
		return powders;
	}

	public boolean addPowder(Powder powder, Tracker tracker) {
		if (!tracker.getType().equals(trackerType)) {
			return false;
		}
		this.powders.put(powder.clone(), tracker);
		return false;
	}

	public boolean removePowder(Powder powder) {
		for (Powder otherPowder : getPowders().keySet()) {
			if (powder.getName().equals(otherPowder.getName())) {
				return powders.remove(otherPowder) != null;
			}
		}
		return false;
	}

}
