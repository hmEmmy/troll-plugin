package me.emmy.plugin.api.command;


import me.emmy.plugin.Troll;

public abstract class BaseCommand {
    public Troll troll;

    /**
     * Constructor for the BaseCommand class.
     */
    public BaseCommand() {
        this.troll = Troll.getInstance();
        this.troll.getCommandFramework().registerCommands(this);
    }

    /**
     * Method to be called when a command is executed.
     *
     * @param command The command.
     */
    public abstract void onCommand(CommandArgs command);
}