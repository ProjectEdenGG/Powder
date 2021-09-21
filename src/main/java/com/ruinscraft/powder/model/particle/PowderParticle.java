package com.ruinscraft.powder.model.particle;

import lombok.Data;
import org.bukkit.Particle;

@Data
public abstract class PowderParticle implements Cloneable {

	// enum name for the PowderParticle; one single character (name does not have to exist)
	private char character;
	// the Particle assigned with the PowderParticle
	private Particle particle;
	// amount of Particles
	private int amount;
	// x-offset data
	private double xOff;
	// y-offset data
	private double yOff;
	// z-offset data
	private double zOff;
	// extra data
	private Object data;

	public PowderParticle(PowderParticle powderParticle) {
		this.character = powderParticle.getCharacter();
		this.particle = powderParticle.getParticle();
		this.amount = powderParticle.getAmount();
		this.xOff = powderParticle.getXOff();
		this.yOff = powderParticle.getYOff();
		this.zOff = powderParticle.getZOff();
		this.data = powderParticle.getData();
	}

	public PowderParticle(char character, Particle particle, int amount,
						  double xOff, double yOff, double zOff, Object data) {
		this.character = character;
		this.particle = particle;
		this.amount = amount;
		this.xOff = xOff;
		this.yOff = yOff;
		this.zOff = zOff;
		this.data = data;
	}

	public PowderParticle(Particle particle, int amount,
			double xOff, double yOff, double zOff, Object data) {
		this.character = 0;
		this.particle = particle;
		this.amount = amount;
		this.xOff = xOff;
		this.yOff = yOff;
		this.zOff = zOff;
		this.data = data;
	}

	public PowderParticle(char character, Particle particle) {
		this.character = character;
		this.particle = particle;
		this.xOff = 0;
		this.yOff = 0;
		this.zOff = 0;
		this.data = null;
	}

	@Override
	public PowderParticle clone() {
		return this;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PowderParticle particle))
			return false;

		return particle.getAmount() == this.amount
			&& particle.getCharacter() == this.character
			&& particle.getParticle() == this.particle
			&& particle.getXOff() == this.xOff
			&& particle.getYOff() == this.yOff
			&& particle.getZOff() == this.zOff
			&& particle.getData() == particle.getData();
	}

}
