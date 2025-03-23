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
public class PushCommand extends BaseCommand {
    @CommandData(name = "push", permission = "troll.command.push", usage = "/push <player>", description = "Push a player")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length < 2) {
            player.sendMessage(CC.translate("&cUsage: /push <player> <value>"));
            return;
        }

        Player target = this.troll.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CC.translate("&cPlayer not found."));
            return;
        }

        int value;
        try {
            value = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(CC.translate("&cInvalid value."));
            return;
        }

        if (value < 1) {
            player.sendMessage(CC.translate("&cValue must be greater than 0."));
            return;
        }

        target.setVelocity(player.getLocation().getDirection().multiply(value));
        player.sendMessage(CC.translate("&aYou have pushed &b" + target.getName() + " &awith a value of &b" + value + "&a."));
        target.sendMessage(CC.translate("&cYou have been pushed by &4" + player.getName() + "&c."));
    }
}