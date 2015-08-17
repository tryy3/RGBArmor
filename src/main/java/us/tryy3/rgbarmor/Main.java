package us.tryy3.rgbarmor;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import us.tryy3.rgbarmor.commands.RGBCommand;
import us.tryy3.rgbarmor.handlers.AnimationHandler;
import us.tryy3.rgbarmor.handlers.PlayerHandler;

import java.io.File;

/**
 * Created by tryy3 on 2015-07-19.
 */
public class Main extends JavaPlugin
{
    public static File file;

    @Override
    public void onEnable ()
    {
        BukkitScheduler scheduler = getServer().getScheduler();
        file = getDataFolder();
        super.onEnable();
        initCommands();

        scheduler.scheduleSyncDelayedTask(this, new Runnable()
        {
            @Override
            public void run ()
            {
                initConfig();
            }
        }, 5L);

        scheduler.scheduleSyncDelayedTask(this, new Runnable()
        {
            @Override
            public void run ()
            {
                initTimer();
            }
        }, 10L);
    }

    @Override
    public void onDisable ()
    {
        super.onDisable();
    }

    public void initConfig ()
    {
        this.saveDefaultConfig();
        File aniDir = new File(getDataFolder() + "/animations");
        if (!aniDir.exists())
        {
            saveResource("animations/rainbow.yml", false);
        }

        if (!(new File(aniDir + "/default.yml").exists()))
        {
            saveResource("animations/default.yml", false);
        }
        AnimationHandler.init(new File(getDataFolder() + "/animations"));
        PlayerHandler.init(new File(getDataFolder() + "/players.yml"));
    }

    public void initTimer ()
    {
        new Timer().runTaskTimer(this, 0L, 1L);
    }

    public void initCommands ()
    {
        getCommand("rgbarmor").setExecutor(new RGBCommand());
    }
}
