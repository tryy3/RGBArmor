package us.tryy3.rgbarmor.handlers;

import org.bukkit.Bukkit;
import us.tryy3.rgbarmor.objects.Animation;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * Created by tryy3 on 2015-07-19.
 */
public class AnimationHandler
{
    private static HashMap<String, Animation> animations;

    public static void init(File aniDir)
    {
        animations = new HashMap<>();
        if (!aniDir.exists())
        {
            Bukkit.getLogger().log(Level.SEVERE, "Animation directory can't be found.");
            return;
        }
        if (aniDir.listFiles().length <= 0)
        {
            Bukkit.getLogger().log(Level.SEVERE, "Can't find any animations.");
            return;
        }

        for (File ani : aniDir.listFiles())
        {
            animations.put(ani.getName().replaceAll(".yml", ""), new Animation(ani));
        }
    }

    public static HashMap<String, Animation> getAnimations ()
    {
        return animations;
    }

    public static Animation getAnimation(String string)
    {
        return animations.get(string);
    }

    public static void setAnimations (HashMap<String, Animation> ani)
    {
        animations=ani;
    }

    public static void addAnimation (String str, Animation animation)
    {
        animations.put(str, animation);
    }
}
