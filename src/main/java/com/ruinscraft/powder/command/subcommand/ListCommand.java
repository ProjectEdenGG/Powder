package com.ruinscraft.powder.command.subcommand;

import lombok.Data;
import org.bukkit.entity.Player;

import com.ruinscraft.powder.PowderHandler;
import com.ruinscraft.powder.PowderPlugin;
import com.ruinscraft.powder.command.SubCommand;
import com.ruinscraft.powder.util.PowderUtil;

@Data
public class ListCommand implements SubCommand {

	private String[] labels = {"list"};

	@Override
	public void command(Player player, String label, String[] args) {
		PowderHandler powderHandler = PowderPlugin.get().getPowderHandler();

		// elements contained within a page of a list
		int pageLength = PowderPlugin.get().getConfig().getInt("pageLength");

		int page;
		try {
			page = Integer.parseInt(args[1]);
		} catch (Exception e) {
			page = 1;
		}

		PowderUtil.listPowders(player, powderHandler.getPowders(),
				" list ", page, pageLength, label);
	}

}
