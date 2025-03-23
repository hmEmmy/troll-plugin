package me.emmy.plugin.menu;

import lombok.AllArgsConstructor;
import me.emmy.plugin.Troll;
import me.emmy.plugin.api.menu.Button;
import me.emmy.plugin.api.menu.pagination.PaginatedMenu;
import me.emmy.plugin.util.CC;
import me.emmy.plugin.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * @author Emmy
 * @project Troll
 * @since 23/03/2025
 */
public class PlayersMenu extends PaginatedMenu {
    @Override
    public String getPrePaginatedTitle(Player player) {
        return "&c&lAll online Players";
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        List<Player> onlinePlayers = new ArrayList<>(Troll.getInstance().getServer().getOnlinePlayers());

        onlinePlayers.stream()
                .sorted(Comparator.comparing(Player::getName).reversed())
                .forEachOrdered(onlinePlayer -> {
                    buttons.put(buttons.size(), new PlayerButton(onlinePlayer));
                });

        return buttons;
    }



    @AllArgsConstructor
    private static class PlayerButton extends Button {
        private final Player player;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(Material.SKULL_ITEM)
                    .lore(
                            "",
                            "&fHealth: &c&l" + this.player.getHealth(),
                            "&fHunger: &c&l" + this.player.getFoodLevel(),
                            "&fLevel: &c&l" + this.player.getLevel(),
                            "&fGamemode: &c&l" + this.player.getGameMode().name(),
                            "&fFlying: &c&l" + this.player.isFlying(),
                            "&fOp: &c&l" + this.player.isOp(),
                            //"&fIP: &c&l" + this.player.getAddress().getAddress().getHostAddress(),
                            "&fLocation: &c&l" + this.player.getLocation().getBlockX() + ", " + this.player.getLocation().getBlockY() + ", " + this.player.getLocation().getBlockZ(),
                            "",
                            "&a&lLeft-Click to teleport!",
                            "&4&lShift-Left-Click to end their life!",
                            "&c&lRight-Click to strike with lightning!",
                            "&9&lShift-Right-Click to make them hungry!"
                    )
                    .name("&c" + this.player.getName())
                    .durability(3).setSkullOwner(this.player.getName()).build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            switch (clickType) {
                case LEFT:
                    player.teleport(this.player.getLocation());
                    player.sendMessage(CC.translate("&aYou have been teleported to &b" + this.player.getName() + "&a."));
                    player.closeInventory();
                    break;
                case SHIFT_LEFT:
                    this.player.setHealth(0.0);
                    player.sendMessage(CC.translate("&aYou have ended the life of &b" + this.player.getName() + "&a."));
                    player.closeInventory();
                    break;
                case RIGHT:
                    player.getWorld().strikeLightning(this.player.getLocation());
                    player.sendMessage(CC.translate("&aYou have struck &b" + this.player.getName() + " &awith lightning."));
                    player.closeInventory();
                    break;
                case SHIFT_RIGHT:
                    this.player.setFoodLevel(0);
                    player.sendMessage(CC.translate("&aYou have made &b" + this.player.getName() + " &ahungry."));
                    player.closeInventory();
                    break;
            }
        }
    }
}
