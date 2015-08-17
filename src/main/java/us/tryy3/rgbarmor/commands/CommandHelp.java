package us.tryy3.rgbarmor.commands;

import java.util.HashMap;

/**
 * Created by tryy3 on 2015-07-22.
 */
public class CommandHelp
{
    private HashMap<String, HashMap<String, String>> commands;

    public CommandHelp()
    {
        commands = new HashMap<>();
        addCommand("helmet", "Control helmet functions", "helmet [toggle|animations [animation name]]", "rgbarmor.helmet.toggle");
        addCommand("chestplate", "Control chestplate functions", "chestplate [toggle|animations [animation name]]", "rgbarmor.chestplate.toggle");
        addCommand("leggings", "Control leggings functions", "leggings [toggle|animations [animation name]]", "rgbarmor.leggings.toggle");
        addCommand("boots", "Control boots functions", "boots [toggle|animations [animation name]]", "rgbarmor.boots.toggle");
        addCommand("all", "Control all functions", "all [toggle|animations [animation name]]", "rgbarmor.all.toggle");
        addCommand("help", "help command", "help [cmd]", "rgbarmor.help");
    }

    private void addCommand(String name, String helpCommand, String usage, String permission)
    {
        HashMap<String, String> help = new HashMap<>();

        help.put("help", helpCommand);
        help.put("usage", usage);
        help.put("perm", permission);
        commands.put(name, help);
    }

    public HashMap<String, HashMap<String, String>> get()
    {
        return this.commands;
    }

    public HashMap<String, String> getCommand(String name)
    {
        return this.commands.get(name);
    }
}
