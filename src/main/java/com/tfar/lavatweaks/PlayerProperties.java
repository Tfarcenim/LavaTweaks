package com.tfar.lavatweaks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties {

    @CapabilityInject(PlayerLavaHandler.class)
    public static Capability<PlayerLavaHandler> PLAYER_NETHER_TIMER;

    public static PlayerLavaHandler getPlayerNether(EntityPlayer player) {
      return player.getCapability(PLAYER_NETHER_TIMER, null);
    }
  }
