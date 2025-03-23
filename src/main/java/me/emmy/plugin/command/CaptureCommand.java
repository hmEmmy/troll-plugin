package me.emmy.plugin.command;

import me.emmy.plugin.api.command.BaseCommand;
import me.emmy.plugin.api.command.CommandArgs;
import me.emmy.plugin.api.command.annotation.CommandData;
import me.emmy.plugin.util.CC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * @author Emmy
 * @project Troll
 * @since 23/03/2025
 */
public class CaptureCommand extends BaseCommand {
    @CommandData(name = "capture", permission = "troll.command.capture", usage = "/capture <player>", description = "Capture a player")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length == 0) {
            player.sendMessage(CC.translate("&cUsage: /capture <player>"));
            return;
        }

        Player target = player.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CC.translate("&cPlayer not found"));
            return;
        }

        target.teleport(player.getLocation());

        Location location = player.getLocation();
        World world = location.getWorld();

        int px = location.getBlockX();
        int py = location.getBlockY();
        int pz = location.getBlockZ();

        for (int x = px - 2; x <= px + 2; x++) {
            for (int y = py - 2; y <= py + 3; y++) {
                for (int z = pz - 2; z <= pz + 2; z++) {
                    if (x == px - 2 || x == px + 2 || y == py - 2 || y == py + 3 || z == pz - 2 || z == pz + 2) {
                        if (world.getBlockAt(x, y, z).getType() == Material.AIR) {
                            world.getBlockAt(x, y, z).setType(Material.GLASS);
                        }
                    }
                }
            }
        }

        player.sendMessage(CC.translate("&aYou have captured &b" + target.getName() + "&a."));
        target.sendMessage(CC.translate("&cYou have been captured by &4" + player.getName() + "&c."));
    }
}