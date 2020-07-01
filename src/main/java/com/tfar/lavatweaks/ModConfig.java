package com.tfar.lavatweaks;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Config(modid = LavaTweaks.MODID)
@Mod.EventBusSubscriber
public class ModConfig {

  @Config.Name("Preferred basalt")
  public static String basalt = "lavatweaks:basalt";

  @Config.Name("Preferred basalt cobblestone")
  public static String basalt_cobblestone = "lavatweaks:basalt_cobblestone";

  @Config.Name("Enable Basalt Generators")
  public static boolean enable_basalt = true;

  @Config.Name("Enable Lava Proximity Burning")
  public static boolean enable_proximity_burning = true;

  @SubscribeEvent
  public static void configchanged(ConfigChangedEvent e){
    if (e.getModID().equals(LavaTweaks.MODID)){
      ConfigManager.sync(LavaTweaks.MODID, Config.Type.INSTANCE);
      EventHandler.basalt = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(basalt));
      EventHandler.basalt_cobblestone = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(basalt_cobblestone));
    }
  }
}
