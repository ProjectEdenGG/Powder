package com.ruinscraft.powder.command.subcommand;

import lombok.Data;
import org.bukkit.entity.Player;

import com.ruinscraft.powder.command.SubCommand;
import com.ruinscraft.powder.util.PowderUtil;

@Data
public class HelpCommand implements SubCommand {

	private String[] labels = {"help"};

	@Override
	public void command(Player player, String label, String[] args) {
		int page;
		try {
			page = Integer.parseInt(args[1]);
		} catch (Exception e) {
			page = 1;
		}

		PowderUtil.helpMessage(player, label, page);
	}

}
