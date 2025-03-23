package me.emmy.plugin.command;

import me.emmy.plugin.api.command.BaseCommand;
import me.emmy.plugin.api.command.CommandArgs;
import me.emmy.plugin.api.command.annotation.CommandData;
import me.emmy.plugin.util.CC;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Troll
 * @since 23/03/2025
 */
public class StrikeCommand extends BaseCommand {
    @CommandData(name = "strike", permission = "troll.command.strike", usage = "/strike <player>", description = "Strike a player with lightning")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length < 1) {
            player.sendMessage(CC.translate("&cUsage: /strike <player>"));
            return;
        }

        Player target = this.troll.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CC.translate("&cPlayer not found."));
            return;
        }

        target.getWorld().strikeLightning(target.getLocation());
        player.sendMessage(CC.translate("&aYou have struck &b" + target.getName() + " &awith lightning."));
        target.sendMessage(CC.translate("&cYou have been struck by &4" + player.getName() + " &cwith lightning."));
    }
}