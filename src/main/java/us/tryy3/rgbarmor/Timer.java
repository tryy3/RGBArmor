package us.tryy3.rgbarmor;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import us.tryy3.rgbarmor.handlers.PlayerHandler;
import us.tryy3.rgbarmor.objects.Armor;
import us.tryy3.rgbarmor.objects.PlayerRGB;

import java.util.List;
import java.util.UUID;

/**
 * Created by tryy3 on 2015-07-19.
 */
public class Timer extends BukkitRunnable
{
    @Override
    public void run ()
    {
        if (PlayerHandler.getPlayers() != null)
        {
            for (PlayerRGB playerRGB : PlayerHandler.getPlayers())
            {
                Player player=Bukkit.getPlayer(UUID.fromString(playerRGB.getUUID()));
                if (player != null)
                {
                    if (player.isOnline())
                    {
                        Armor helmet=playerRGB.getHelmet();
                        Armor chestplate=playerRGB.getChestplate();
                        Armor leggings=playerRGB.getLeggings();
                        Armor boots=playerRGB.getBoots();
                        PlayerInventory inventory=player.getInventory();

                        if (helmet.isActive() &&
                                inventory.getHelmet() != null &&
                                inventory.getHelmet().getType().equals(Material.LEATHER_HELMET))
                        {
                            changeArmorColor(inventory.getHelmet(), helmet);
                        }

                        if (chestplate.isActive() &&
                                inventory.getChestplate() != null &&
                                inventory.getChestplate().getType().equals(Material.LEATHER_CHESTPLATE))
                        {
                            changeArmorColor(inventory.getChestplate(), chestplate);
                        }

                        if (leggings.isActive() &&
                                inventory.getLeggings() != null &&
                                inventory.getLeggings().getType().equals(Material.LEATHER_LEGGINGS))
                        {
                            changeArmorColor(inventory.getLeggings(), leggings);
                        }

                        if (boots.isActive() &&
                                inventory.getBoots() != null &&
                                inventory.getBoots().getType().equals(Material.LEATHER_BOOTS))
                        {
                            changeArmorColor(inventory.getBoots(), boots);
                        }
                    }
                }
            }
        }
    }

    public void changeArmorEnchantments(ItemStack itemStack, Armor armor)
    {

    }

    public void changeArmorName(ItemStack itemStack, Armor armor)
    {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', armor.getAnimation().getName()));
        itemStack.setItemMeta(itemMeta);
    }

    public void changeArmorColor(ItemStack itemStack, Armor armor)
    {
        if (armor.getTicks() <= 0)
        {
            changeColor(itemStack, armor.getAnimation().getCurrentRGB(armor.getPosition()));
            armor.increasePosition();
            changeArmorName(itemStack, armor);
            changeArmorEnchantments(itemStack, armor);
        }
        armor.decreaseTicks();
    }

    public ItemStack changeColor(ItemStack itemStack, List<Integer> list)
    {
        return changeColor(itemStack, list.get(0), list.get(1), list.get(2));
    }

    public ItemStack changeColor(ItemStack itemStack, int red, int green, int blue)
    {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(Color.fromBGR(red, green, blue));
        itemStack.setItemMeta(leatherArmorMeta);
        return itemStack;
    }
}
