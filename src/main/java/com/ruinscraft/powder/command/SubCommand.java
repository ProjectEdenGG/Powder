package com.ruinscraft.powder.command;

import org.bukkit.entity.Player;

public interface SubCommand {

	String[] getLabels();

	void command(Player player, String label, String[] args);

}
