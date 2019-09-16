package com.tfar.lavatweaks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = LavaTweaks.MODID)
public class EventHandler {

  @SubscribeEvent
  public static void onEntityConstructing(AttachCapabilitiesEvent<Entity> event) {
    if (event.getObject() instanceof EntityPlayer) {
      if (!event.getObject().hasCapability(PlayerProperties.PLAYER_NETHER_TIMER, null)) {
        event.addCapability(new ResourceLocation(LavaTweaks.MODID, "PlayerNetherTimer"), new CapabilityProvider());
      }
    }
  }

  public static Block basalt = LavaTweaks.Objects.BASALT;
  public static Block basalt_cobblestone = LavaTweaks.Objects.BASALT_COBBLESTONE;


  @SubscribeEvent
  public static void onPlayerTick(TickEvent.PlayerTickEvent evt) {
    if (evt.phase == TickEvent.Phase.START) {
      return;
    }
    EntityPlayer player = evt.player;

    if (player.hasCapability(PlayerProperties.PLAYER_NETHER_TIMER, null)) {
      PlayerLavaHandler timer = PlayerProperties.getPlayerNether(player);

      if (player.dimension == -1 && !player.isPotionActive(MobEffects.FIRE_RESISTANCE)) {
        if (!player.isPotionActive(LavaTweaks.SWELTERING) && !player.isBurning())player.addPotionEffect(new PotionEffect(LavaTweaks.SWELTERING,timer.netherTimeLimit,0,false,false));
        else if (player.isBurning())player.removePotionEffect(LavaTweaks.SWELTERING);
      } else {
        timer.extinguish();
        player.removePotionEffect(LavaTweaks.SWELTERING);
      }
      if (checkForLava(player)) timer.tickLava();
      else timer.resetLava();
      if (timer.shouldBeOnFire()) {
        player.setFire(1);
      }
    }
  }

  public static boolean checkForLava(EntityPlayer player) {
    BlockPos pos = player.getPosition().up();
    int x = pos.getX();
    int y = pos.getY();
    int z = pos.getZ();

    for (int x1 = x - 2; x1 < x + 2; x1++)
      for (int y1 = y - 2; y1 < y + 2; y1++)
        for (int z1 = z - 2; z1 < z + 2; z1++) {
          RayTraceResult result = player.world.rayTraceBlocks(new Vec3d(x1 + .5, player.posY + player.getEyeHeight(), z1 + .5), new Vec3d(x1+ .5, y1,z1 +.5), true, false, true);
        if (result == null)continue;
          IBlockState state = player.world.getBlockState(result.getBlockPos());
          if (state.getMaterial() != Material.LAVA)continue;
          return true;
       // System.out.println(player.world.getBlockState(result.getBlockPos()));
        }
    return false;
  }

  @SubscribeEvent
  public static void basaltGenerator(BlockEvent.FluidPlaceBlockEvent e){
    if (e.getNewState().getBlock() == Blocks.COBBLESTONE)
   e.setNewState(basalt_cobblestone.getDefaultState());
    if (e.getNewState().getBlock() == Blocks.STONE)
      e.setNewState(basalt.getDefaultState());
  }

  @SubscribeEvent
  public static void potionExpire(PotionEvent.PotionExpiryEvent e) {
    if (e.getPotionEffect() != null && e.getPotionEffect().getPotion() == LavaTweaks.SWELTERING) {
      EntityLivingBase entity = e.getEntityLiving();
      if (entity instanceof EntityPlayer) {
        EntityPlayer player = (EntityPlayer) entity;
        if (player.hasCapability(PlayerProperties.PLAYER_NETHER_TIMER, null)) {
          PlayerLavaHandler timer = PlayerProperties.getPlayerNether(player);
          timer.timesUp();
        }
      }
    }
  }
}

