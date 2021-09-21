package com.ruinscraft.powder.command.subcommand;

import lombok.Data;
import org.bukkit.entity.Player;

import com.ruinscraft.powder.PowderPlugin;
import com.ruinscraft.powder.command.SubCommand;
import com.ruinscraft.powder.model.Message;
import com.ruinscraft.powder.util.PowderUtil;

@Data
public class ReloadCommand implements SubCommand {

	private String[] labels = {"reload"};

	@Override
	public void command(Player player, String label, String[] args) {
		if (!(player.hasPermission("powder.reload"))) {
			PowderUtil.sendPrefixMessage(player,
					Message.GENERAL_NO_PERMISSION, label, player.getName());
			return;
		}

		if (PowderPlugin.isLoading()) {
			PowderUtil.sendPrefixMessage(player, Message.LOADING_ALREADY,
					label, player.getName());
		} else {
			PowderPlugin.get().getServer().getScheduler()
			.runTaskAsynchronously(PowderPlugin.get(), () -> {
				PowderPlugin.get().reload();
			});
		}
	}

}
