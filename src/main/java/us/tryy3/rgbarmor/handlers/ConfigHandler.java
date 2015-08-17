package us.tryy3.rgbarmor.handlers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import us.tryy3.rgbarmor.Main;
import us.tryy3.rgbarmor.objects.Animation;
import us.tryy3.rgbarmor.objects.Armor;
import us.tryy3.rgbarmor.objects.PlayerRGB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tryy3 on 2015-07-24.
 */
public class ConfigHandler
{
    //TODO: Maybe add load functions from animation/player handler?

    public static void save(Animation animation)
    {
        FileConfiguration config = YamlConfiguration.loadConfiguration(animation.getFile());

        config.set("Name", animation.getName());
        config.set("Ticks", animation.getTicks());
        config.createSection("Enchantments", animation.getEnchantments());
        List<String> list = new ArrayList<>();
        for (List<Integer> i: animation.getRgb())
        {
            String s = i.get(0) + "." + i.get(1) + "," + i.get(2);
            list.add(s);
        }
        config.set("RGB", list);

        try
        {
            config.save(animation.getFile());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void save(Player player)
    {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(Main.file + "/players.yml"));
        String playerUUID = "Players." + player.getUniqueId();

        PlayerRGB playerRGB = PlayerHandler.getPlayer(player.getUniqueId().toString());

        Armor helmet = playerRGB.getHelmet();
        Armor chestplate = playerRGB.getChestplate();
        Armor leggings = playerRGB.getLeggings();
        Armor boots = playerRGB.getBoots();

        config.set(playerUUID + ".helmet.animation", helmet.getAnimation().getUniqueName());
        config.set(playerUUID + ".helmet.ticks", helmet.getTicks());
        config.set(playerUUID + ".helmet.active", helmet.isActive());

        config.set(playerUUID + ".chestplate.animation", chestplate.getAnimation().getUniqueName());
        config.set(playerUUID + ".chestplate.ticks", chestplate.getTicks());
        config.set(playerUUID + ".chestplate.active", chestplate.isActive());

        config.set(playerUUID + ".leggings.animation", leggings.getAnimation().getUniqueName());
        config.set(playerUUID + ".leggings.ticks", leggings.getTicks());
        config.set(playerUUID + ".leggings.active", leggings.isActive());


        config.set(playerUUID + ".boots.animation", boots.getAnimation().getUniqueName());
        config.set(playerUUID + ".boots.ticks", boots.getTicks());
        config.set(playerUUID + ".boots.active", boots.isActive());

        try
        {
            config.save(new File(Main.file + "/players.yml"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
