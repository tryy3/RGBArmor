package us.tryy3.rgbarmor.objects;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tryy3 on 2015-07-19.
 */
public class Animation
{
    private String uniqueName = "";
    private String name = "";
    private int ticks = 1;
    private HashMap<String, HashMap<String, Integer>> enchantments = new HashMap<>();
    private List<List<Integer>> rgb = new ArrayList<>();
    private File file;

    public Animation(File file)
    {
        this.file = file;
        FileConfiguration config =YamlConfiguration.loadConfiguration(file);
        setUniqueName(file.getName().replaceAll(".yml", ""));
        setName(config.getString("Name"));
        setTicks(config.getInt("Ticks"));

        HashMap <String, HashMap<String, Integer>> hm = new HashMap<>();
        Map <String, Object> hashMap = config.getConfigurationSection("Enchantments").getValues(false);
        for (Map.Entry<String, Object> entry: hashMap.entrySet())
        {
            Map <String, Object> hash = config.getConfigurationSection("Enchantments." + entry.getKey()).getValues(false);
            HashMap<String, Integer> integerHashMap = new HashMap<>();
            for (Map.Entry<String, Object> ent : hash.entrySet())
            {
                integerHashMap.put(ent.getKey(), (Integer) ent.getValue());
            }
            hm.put(entry.getKey(), integerHashMap);
        }
        setEnchantments(hm);

        for (Object str : config.getList("RGB"))
        {
            List<Integer> list = new ArrayList<>();
            for (String s : ((String) str).split(","))
            {
                list.add(Integer.parseInt(s));
            }
            rgb.add(list);
        }
    }

    public Animation (Animation animation)
    {
        this.setUniqueName(animation.getUniqueName());
        this.setName(animation.getName());
        this.setTicks(animation.getTicks());
        this.setEnchantments(animation.getEnchantments());
        this.setRgb(animation.getRgb());
        this.file = animation.getFile();
    }

    public void setName (String name)
    {
        this.name=name;
    }

    public String getName ()
    {
        return name;
    }

    public void setUniqueName (String uniqueName)
    {
        this.uniqueName=uniqueName;
    }

    public String getUniqueName () {
        return uniqueName;
    }

    public void setEnchantments (HashMap<String, HashMap<String, Integer>> enchantments)
    {
        this.enchantments=enchantments;
    }

    public HashMap<String, HashMap<String, Integer>> getEnchantments ()
    {
        return enchantments;
    }

    public void setRgb (List<List<Integer>> rgb)
    {
        this.rgb=rgb;
    }

    public List<List<Integer>> getRgb ()
    {
        return rgb;
    }

    public void setTicks (int ticks)
    {
        this.ticks=ticks;
    }

    public int getTicks ()
    {
        return ticks;
    }

    public List<Integer> getCurrentRGB (int position)
    {
        return rgb.get(position);
    }

    public File getFile ()
    {
        return file;
    }
}
