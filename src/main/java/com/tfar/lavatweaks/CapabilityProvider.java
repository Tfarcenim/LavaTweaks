package com.tfar.lavatweaks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityProvider implements ICapabilitySerializable<NBTTagCompound> {

  private PlayerLavaHandler playerLavaHandler = new PlayerLavaHandler();

  @Override
  public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
    return capability == PlayerProperties.PLAYER_NETHER_TIMER;
  }

  @Override
  public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
    if (capability == PlayerProperties.PLAYER_NETHER_TIMER) {
      return (T) playerLavaHandler;
    }
    return null;
  }

  @Override
  public NBTTagCompound serializeNBT() {
    NBTTagCompound nbt = new NBTTagCompound();
    playerLavaHandler.save(nbt);
    return nbt;
  }

  @Override
  public void deserializeNBT(NBTTagCompound nbt) {
    playerLavaHandler.load(nbt);
  }
}

