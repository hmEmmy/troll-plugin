package me.emmy.plugin.command;

import me.emmy.plugin.api.command.BaseCommand;
import me.emmy.plugin.api.command.CommandArgs;
import me.emmy.plugin.api.command.annotation.CommandData;
import me.emmy.plugin.menu.PlayersMenu;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Troll
 * @since 23/03/2025
 */
public class PlayersCommand extends BaseCommand {
    @CommandData(name = "players", permission = "troll.command.players", usage = "/players", description = "Open a menu of players")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        new PlayersMenu().openMenu(player);
    }
}
