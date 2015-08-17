package us.tryy3.rgbarmor.handlers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import us.tryy3.rgbarmor.objects.Animation;
import us.tryy3.rgbarmor.objects.Armor;
import us.tryy3.rgbarmor.objects.PlayerRGB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tryy3 on 2015-07-19.
 */
public class PlayerHandler
{
    private static List<PlayerRGB> players = new ArrayList<>();

    public static void init(File playerFile)
    {
        if (!playerFile.exists())
        {
            Bukkit.getLogger().info("Player file does not exists, creating it.");
            try
            {
                playerFile.createNewFile();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } else
        {
            FileConfiguration playerConfig=YamlConfiguration.loadConfiguration(playerFile);
            players = new ArrayList<>();
            if (playerConfig.contains("Players"))
            {
                for (String playerObj : (playerConfig.getConfigurationSection("Players").getValues(false)).keySet())
                {
                    PlayerRGB playerRGB=new PlayerRGB(playerObj);

                    if (playerConfig.contains("Players." + playerObj + ".helmet"))
                    {
                        Animation animation=AnimationHandler.getAnimation(playerConfig.getString("Players." + playerObj + ".helmet.animation"));
                        int ticks=playerConfig.getInt("Players." + playerObj + ".helmet.ticks");
                        boolean acitve=playerConfig.getBoolean("Players." + playerObj + ".helmet.active");
                        playerRGB.setHelmet(new Armor(animation, ticks, acitve));
                    } else
                    {
                        playerRGB.setHelmet(new Armor());
                    }

                    if (playerConfig.contains("Players." + playerObj + ".chestplate"))
                    {
                        Animation animation=AnimationHandler.getAnimation(playerConfig.getString("Players." + playerObj + ".chestplate.animation"));
                        int ticks=playerConfig.getInt("Players." + playerObj + ".chestplate.ticks");
                        boolean acitve=playerConfig.getBoolean("Players." + playerObj + ".chestplate.active");
                        playerRGB.setChestplate(new Armor(animation, ticks, acitve));
                    } else
                    {
                        playerRGB.setChestplate(new Armor());
                    }

                    if (playerConfig.contains("Players." + playerObj + ".leggings"))
                    {
                        Animation animation=AnimationHandler.getAnimation(playerConfig.getString("Players." + playerObj + ".leggings.animation"));
                        int ticks=playerConfig.getInt("Players." + playerObj + ".leggings.ticks");
                        boolean acitve=playerConfig.getBoolean("Players." + playerObj + ".leggings.active");
                        playerRGB.setLeggings(new Armor(animation, ticks, acitve));
                    } else
                    {
                        playerRGB.setLeggings(new Armor());
                    }

                    if (playerConfig.contains("Players." + playerObj + ".boots"))
                    {
                        Animation animation=AnimationHandler.getAnimation(playerConfig.getString("Players." + playerObj + ".boots.animation"));
                        int ticks=playerConfig.getInt("Players." + playerObj + ".boots.ticks");
                        boolean acitve=playerConfig.getBoolean("Players." + playerObj + ".boots.active");
                        playerRGB.setBoots(new Armor(animation, ticks, acitve));
                    } else
                    {
                        playerRGB.setBoots(new Armor());
                    }
                    players.add(playerRGB);
                }
            }
        }
    }

    public static List<PlayerRGB> getPlayers ()
    {
        return players;
    }

    public static void setPlayers (List<PlayerRGB> pl)
    {
        players=pl;
    }

    public static PlayerRGB getPlayer(UUID uuid)
    {
        return getPlayer(uuid.toString());
    }

    public static PlayerRGB getPlayer(String uuid)
    {
        for (PlayerRGB playerRGB : players)
        {
            if (playerRGB.getUUID().equals(uuid))
            {
                return playerRGB;
            }
        }
        return new PlayerRGB(uuid);
    }

    public static void addPlayer(PlayerRGB playerRGB)
    {
        players.add(playerRGB);
    }
}
