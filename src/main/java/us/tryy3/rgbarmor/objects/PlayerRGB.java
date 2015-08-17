package us.tryy3.rgbarmor.objects;

/**
 * Created by tryy3 on 2015-07-19.
 */
public class PlayerRGB
{
    private String UUID;
    private Armor helmet = new Armor();
    private Armor chestplate = new Armor();
    private Armor leggings = new Armor();
    private Armor boots = new Armor();

    public PlayerRGB(String UUID)
    {
        this.UUID = UUID;
    }

    public void setUUID (String UUID)
    {
        this.UUID=UUID;
    }

    public void setBoots (Armor boots)
    {
        this.boots=boots;
    }

    public void setChestplate (Armor chestplate)
    {
        this.chestplate=chestplate;
    }

    public void setHelmet (Armor helmet)
    {
        this.helmet=helmet;
    }

    public void setLeggings (Armor leggings)
    {
        this.leggings=leggings;
    }

    public String getUUID ()
    {
        return UUID;
    }

    public Armor getBoots ()
    {
        return boots;
    }

    public Armor getChestplate ()
    {
        return chestplate;
    }

    public Armor getHelmet ()
    {
        return helmet;
    }

    public Armor getLeggings ()
    {
        return leggings;
    }
}
