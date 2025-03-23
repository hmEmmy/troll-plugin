package me.emmy.plugin.util;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

/**
 * @author Emmy
 * @project Troll
 * @since 23/03/2025
 */
@UtilityClass
public class CC {
    /**
     * Translate a string with the '&' character to a string with the ChatColor character
     *
     * @param string The string to translate
     * @return The translated string
     */
    public String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}