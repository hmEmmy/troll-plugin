package me.emmy.plugin.api.menu.pagination.impl.button;

import me.emmy.plugin.api.menu.pagination.impl.menu.FilterablePaginatedMenu;
import me.emmy.plugin.api.menu.pagination.PageFilter;
import lombok.AllArgsConstructor;
import me.emmy.plugin.api.menu.Button;
import me.emmy.plugin.util.CC;
import me.emmy.plugin.util.ItemBuilder;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PageFilterButton<T> extends Button {
    private FilterablePaginatedMenu<T> menu;

    @Override
    public ItemStack getButtonItem(Player player) {
        if (menu.getFilters() == null || menu.getFilters().isEmpty()) {
            return new ItemStack(Material.AIR);
        }

        List<String> lore = new ArrayList<>();
        lore.add("&7&m---------------------------------");

        for (PageFilter filter : menu.getFilters()) {
            String color;
            String decoration = "";
            String icon;

            if (filter.isEnabled()) {
                color = ChatColor.GREEN.toString();
                icon = StringEscapeUtils.unescapeJava("✓");
            } else {
                color = ChatColor.RED.toString();
                icon = StringEscapeUtils.unescapeJava("✗");
            }

            if (menu.getFilters().get(menu.getScrollIndex()).equals(filter)) {
                decoration = ChatColor.YELLOW + StringEscapeUtils.unescapeJava("» ") + " ";
            }

            lore.add(decoration + color + icon + " " + filter.getName());
        }

        lore.add("&7&m---------------------------------");
        lore.add("&eLeft click to scroll.");
        lore.add("&eRight click to toggle a filter.");
        lore.add("&7&m---------------------------------");

        return new ItemBuilder(Material.HOPPER)
                .name("&7Filters")
                .lore(lore)
                .build();
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        if (menu.getFilters() == null || menu.getFilters().isEmpty()) {
            player.sendMessage(ChatColor.RED + "There are no filters.");
        } else {
            if (clickType == ClickType.LEFT) {
                if (menu.getScrollIndex() == menu.getFilters().size() - 1) {
                    menu.setScrollIndex(0);
                } else {
                    menu.setScrollIndex(menu.getScrollIndex() + 1);
                }
            } else if (clickType == ClickType.RIGHT) {
                PageFilter<T> filter = menu.getFilters().get(menu.getScrollIndex());
                filter.setEnabled(!filter.isEnabled());
            }
        }
    }

    @Override
    public boolean shouldUpdate(Player player, ClickType clickType) {
        return true;
    }

}
