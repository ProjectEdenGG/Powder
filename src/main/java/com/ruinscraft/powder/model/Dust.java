package com.ruinscraft.powder.model;

import com.ruinscraft.powder.PowdersCreationTask;
import com.ruinscraft.powder.model.particle.PowderParticle;
import lombok.Data;
import org.bukkit.Location;

@Data
public class Dust implements PowderElement {

	// the PowderParticle associated with this Dust
	private PowderParticle powderParticle;
	// the limit radius that this Dust can be spawned in in
	private double radius;
	// the mean height of where the Dust should be spawned
	private double height;
	// the distance in y value up/down from getHeight() where
	private double ySpan;
	// when to start displaying this Dust
	private int startTime;
	// after how many ticks should it repeat?
	private int repeatTime;
	// set maximum iterations (0 if infinite)
	private int lockedIterations;
	// the next tick to iterate
	private int nextTick;

	// iterations so far
	private int iterations;

	public Dust(Dust dust) {
		this.powderParticle = dust.getPowderParticle();
		this.radius = dust.getRadius();
		this.height = dust.getHeight();
		this.ySpan = dust.getYSpan();
		this.startTime = dust.getStartTime();
		this.repeatTime = dust.getRepeatTime();
		this.lockedIterations = dust.getLockedIterations();
		this.iterations = 0;
	}

	public Dust(PowderParticle powderParticle, double radius, double height,
				double ySpan, int startTime, int repeatTime, int lockedIterations) {
		this.powderParticle = powderParticle;
		this.radius = radius;
		this.height = height;
		this.ySpan = ySpan;
		this.startTime = startTime;
		this.repeatTime = repeatTime;
		this.lockedIterations = lockedIterations;
		this.iterations = 0;
	}

	@Override
	public void setStartTime(int startTime) {
		this.startTime = startTime;
		this.nextTick = startTime;
	}

	@Override
	public void iterate() {
		iterations++;
		this.nextTick = PowdersCreationTask.getCurrentTick() + getRepeatTime();
	}

	@Override
	public void setStartingTick() {
		this.nextTick = PowdersCreationTask.getCurrentTick() + getStartTime();
	}

	@Override
	public Dust clone() {
		return new Dust(this);
	}

	// creates this Dust at the designated location
	@Override
	public void create(Location location) {
		if (powderParticle.getData() != null && powderParticle.getData() instanceof Double) {
			double extra = (Double) powderParticle.getData();
			location.getWorld().spawnParticle(
					powderParticle.getParticle(), location.add(
							(Math.random() - .5) * (2 * getRadius()),
							((Math.random() - .5) * getYSpan()) + getHeight() - .625,
							(Math.random() - .5) * (2 * getRadius())),
					powderParticle.getAmount(),
					powderParticle.getXOff() / 255, powderParticle.getYOff() / 255,
					powderParticle.getZOff() / 255, extra);
		} else {
			location.getWorld().spawnParticle(
					powderParticle.getParticle(), location.add(
							(Math.random() - .5) * (2 * getRadius()),
							((Math.random() - .5) * getYSpan()) + getHeight() - .625,
							(Math.random() - .5) * (2 * getRadius())),
					powderParticle.getAmount(),
					powderParticle.getXOff() / 255, powderParticle.getYOff() / 255,
					powderParticle.getZOff() / 255, powderParticle.getData());
		}
	}

}
