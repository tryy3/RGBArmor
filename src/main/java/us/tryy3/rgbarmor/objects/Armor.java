package us.tryy3.rgbarmor.objects;

import us.tryy3.rgbarmor.handlers.AnimationHandler;

/**
 * Created by tryy3 on 2015-07-19.
 */
public class Armor
{
    private Animation animation;
    private int ticks;
    private boolean isActive = false;
    private int position = 0;

    public Armor()
    {
        this.animation = new Animation(AnimationHandler.getAnimation("default"));
        this.ticks = animation.getTicks();
    }

    public Armor(Animation animation)
    {
        this.ticks = animation.getTicks();
        this.animation = animation;
    }
    public Armor(Animation animation, int ticks)
    {
        this.ticks = ticks;
        this.animation = animation;
    }
    public Armor(Animation animation, int ticks, boolean isActive)
    {
        this.ticks = ticks;
        this.animation = animation;
        this.isActive = isActive;
    }

    public Animation getAnimation ()
    {
        return animation;
    }

    public void setAnimation (Animation animation)
    {
        this.animation=new Animation(animation);
        this.setTicks(animation.getTicks());
    }

    public int getTicks ()
    {
        return ticks;
    }

    public void setTicks (int ticks)
    {
        this.ticks=ticks;
    }

    public void decreaseTicks ()
    {
        if ((this.ticks--) <= 0)
        {
            this.ticks = animation.getTicks();
        }
    }

    public boolean isActive ()
    {
        return isActive;
    }

    public void setActive (boolean isActive)
    {
        this.isActive=isActive;
    }

    public void toggle ()
    {
        isActive = (isActive) ? false : true;
    }

    public int getPosition ()
    {
        return position;
    }

    public void setPosition (int position)
    {
        this.position=position;
    }

    public void increasePosition ()
    {
        if ((this.position++) >= (animation.getRgb().size() -1))
        {
            this.position = 0;
        }
    }
}
