package me.emmy.plugin;

import lombok.Getter;
import me.emmy.plugin.api.command.CommandFramework;
import me.emmy.plugin.api.menu.MenuListener;
import me.emmy.plugin.command.CaptureCommand;
import me.emmy.plugin.command.PlayersCommand;
import me.emmy.plugin.command.PushCommand;
import me.emmy.plugin.command.StrikeCommand;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Troll extends JavaPlugin {

    @Getter
    private static Troll instance;

    private CommandFramework commandFramework;

    @Override
    public void onEnable() {
        instance = this;

        this.commandFramework = new CommandFramework(this);
        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);

        new StrikeCommand();
        new PushCommand();
        new CaptureCommand();

        new PlayersCommand();
    }

    @Override
    public void onDisable() {

    }
}
