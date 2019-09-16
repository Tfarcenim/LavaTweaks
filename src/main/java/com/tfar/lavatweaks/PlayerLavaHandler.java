package com.tfar.lavatweaks;

import net.minecraft.nbt.NBTTagCompound;

public class PlayerLavaHandler {

  public int netherTimeLimit = 20 * 300;
  //private int ticksInNether = 0;
  private int ticksNearLava = 0;

  public void tickLava(){
    ticksNearLava++;
  }

  public void resetLava(){
    ticksNearLava = 0;
  }

  boolean fire = false;

  void timesUp(){fire = true;}

  void extinguish(){fire = false;}

  public boolean shouldBeOnFire(){
    return ticksNearLava >= 20 * 5 || fire;
  }

  public void save(NBTTagCompound compound) {
    compound.setBoolean("fire", fire);
    compound.setInteger("ticksNearLava", ticksNearLava);
  }

  public void load(NBTTagCompound compound) {
    fire = compound.getBoolean("fire");
    ticksNearLava = compound.getInteger("ticksNearLava");
  }
}
