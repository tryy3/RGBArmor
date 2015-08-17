package us.tryy3.rgbarmor.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.tryy3.rgbarmor.handlers.AnimationHandler;
import us.tryy3.rgbarmor.handlers.ConfigHandler;
import us.tryy3.rgbarmor.handlers.PlayerHandler;
import us.tryy3.rgbarmor.objects.Armor;
import us.tryy3.rgbarmor.objects.PlayerRGB;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tryy3 on 2015-07-19.
 */
public class RGBCommand implements CommandExecutor
{
    private CommandHelp commandHelp;
    public RGBCommand()
    {
        commandHelp = new CommandHelp();
    }

    @Override
    public boolean onCommand (CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (!(commandSender instanceof Player))
        {
            commandSender.sendMessage("This command can only be ran by a Player.");
        }
        Player player = (Player) commandSender;

        if (strings.length >= 1)
        {
            if (strings[0].equalsIgnoreCase("help"))
            {
                if (strings.length == 2)
                {
                    help(s, player, strings[1]);
                    return true;
                }
                else
                {
                    help(s, player);
                    return true;
                }
            }
            if (strings.length >= 2)
            {
                if (strings[1].equalsIgnoreCase("toggle"))
                {
                    switch (strings[0])
                    {
                        case "helmet":
                            PlayerHandler.getPlayer(player.getUniqueId().toString()).getHelmet().toggle();
                            ConfigHandler.save(player);
                            return true;
                        case "chestplate":
                            PlayerHandler.getPlayer(player.getUniqueId().toString()).getChestplate().toggle();
                            ConfigHandler.save(player);
                            return true;
                        case "leggings":
                            PlayerHandler.getPlayer(player.getUniqueId().toString()).getLeggings().toggle();
                            ConfigHandler.save(player);
                            return true;
                        case "boots":
                            PlayerHandler.getPlayer(player.getUniqueId().toString()).getBoots().toggle();
                            ConfigHandler.save(player);
                            return true;
                        case "all":
                            PlayerHandler.getPlayer(player.getUniqueId().toString()).getHelmet().toggle();
                            PlayerHandler.getPlayer(player.getUniqueId().toString()).getChestplate().toggle();
                            PlayerHandler.getPlayer(player.getUniqueId().toString()).getLeggings().toggle();
                            PlayerHandler.getPlayer(player.getUniqueId().toString()).getBoots().toggle();
                            ConfigHandler.save(player);
                            return true;
                    }
                }
                else if ((strings[1].equalsIgnoreCase("animation") || strings[1].equalsIgnoreCase("animations")) && strings.length >= 3)
                {
                    switch (strings[0])
                    {
                        case "helmet":
                            changeAnimation(PlayerHandler.getPlayer(player.getUniqueId().toString()).getHelmet(), player, strings[2]);
                            ConfigHandler.save(player);
                            return true;
                        case "chestplate":
                            changeAnimation(PlayerHandler.getPlayer(player.getUniqueId().toString()).getChestplate(), player, strings[2]);
                            ConfigHandler.save(player);
                            return true;
                        case "leggings":
                            changeAnimation(PlayerHandler.getPlayer(player.getUniqueId().toString()).getLeggings(), player, strings[2]);
                            ConfigHandler.save(player);
                            return true;
                        case "boots":
                            changeAnimation(PlayerHandler.getPlayer(player.getUniqueId().toString()).getBoots(), player, strings[2]);
                            ConfigHandler.save(player);
                            return true;
                        case "all":
                            //TODO: Possibly edit this so it doesn't spam if animation doesn't exists
                            changeAnimation(PlayerHandler.getPlayer(player.getUniqueId().toString()).getHelmet(), player, strings[2]);
                            changeAnimation(PlayerHandler.getPlayer(player.getUniqueId().toString()).getChestplate(), player, strings[2]);
                            changeAnimation(PlayerHandler.getPlayer(player.getUniqueId().toString()).getLeggings(), player, strings[2]);
                            changeAnimation(PlayerHandler.getPlayer(player.getUniqueId().toString()).getBoots(), player, strings[2]);
                            ConfigHandler.save(player);
                            return true;
                    }
                }
            }
        }

        help(s, player);
        return true;
    }

    public void changeAnimation (Armor armor, Player player, String animation)
    {
        if (AnimationHandler.getAnimation(animation) == null)
        {
            player.sendMessage("This animation does not exists.");
            return;
        }

        armor.setAnimation(AnimationHandler.getAnimation(animation));
    }

    public void help (String cmd, Player player)
    {
        if (!player.hasPermission(commandHelp.getCommand("help").get("perm")))
        {
            player.sendMessage("You do not have permission for this command.");
            return;
        }

        for (Map.Entry<String, HashMap<String, String>> entry : (commandHelp.get()).entrySet())
        {
            HashMap<String, String> en = entry.getValue();

            if (!player.hasPermission(en.get("perm")))
            {
                return;
            }
            player.sendMessage("/" + cmd + " " + en.get("usage"));
        }
    }

    public void help (String cmd, Player player, String command)
    {
        Bukkit.getLogger().info(command);
        if (!player.hasPermission(commandHelp.getCommand("help").get("perm")))
        {
            player.sendMessage("You do not have permission for this command.");
            return;
        }

        if (commandHelp.getCommand(command) == null)
        {
            player.sendMessage("This argument does not exists.");
            help(cmd, player);
            return;
        }

        HashMap<String, String> comm = commandHelp.getCommand(command);

        if (!player.hasPermission(comm.get("perm")))
        {
            player.sendMessage("You do not have permission for this argument.");
            return;
        }

        player.sendMessage("/" + cmd + " " + comm.get("usage"));
    }
}
