package com.tfar.lavatweaks;

import com.tfar.lavatweaks.block.BasaltBlock;
import com.tfar.lavatweaks.block.TweakedFlowingLava;
import com.tfar.lavatweaks.block.TweakedStaticLava;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.nbt.NBTBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static com.tfar.lavatweaks.LavaTweaks.MODID;
import static com.tfar.lavatweaks.LavaTweaks.Objects.*;

@Mod.EventBusSubscriber
@Mod(modid = MODID, name = LavaTweaks.NAME, version = LavaTweaks.VERSION)
public class LavaTweaks
{
  public static final String MODID = "lavatweaks";
  public static final String NAME = "Lava Tweaks";
  public static final String VERSION = "@VERSION@";

  private static Logger logger;

  public static final Potion SWELTERING = new SwelteringPotion(true, 12624973).setPotionName("effect.sweltering");

  @EventHandler
  public void preInit(FMLPreInitializationEvent event)
  {
    CapabilityManager.INSTANCE.register(PlayerLavaHandler.class, new Capability.IStorage<PlayerLavaHandler>() {
      @Nullable
      @Override
      public NBTBase writeNBT(Capability<PlayerLavaHandler> capability, PlayerLavaHandler instance, EnumFacing side) {
        throw new UnsupportedOperationException();
      }

      @Override
      public void readNBT(Capability<PlayerLavaHandler> capability, PlayerLavaHandler instance, EnumFacing side, NBTBase nbt) {
        throw new UnsupportedOperationException();
      }
    }, () -> null);
    logger = event.getModLog();
  }

  @SubscribeEvent
  public static void blocks(RegistryEvent.Register<Block> e){
    register(new BasaltBlock().setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setTranslationKey("basalt").setHardness(1.5F).setResistance(10.0F),"basalt",e.getRegistry());
    register(new Block(Material.ROCK).setCreativeTab(CreativeTabs.BUILDING_BLOCKS).setTranslationKey("basalt_cobblestone").setHardness(2.0F).setResistance(10.0F),"basalt_cobblestone",e.getRegistry());
    register(new TweakedStaticLava().disableStats().setHardness(100.0F).setLightLevel(1.0F).setTranslationKey("lava"),"minecraft","lava",e.getRegistry());
    register(new TweakedFlowingLava().disableStats().setHardness(100.0F).setLightLevel(1.0F).setTranslationKey("flowing_lava"),"minecraft","flowing_lava",e.getRegistry());
  }

  @SubscribeEvent
  public static void items(RegistryEvent.Register<Item> e) {
    register(new ItemBlock(BASALT),"basalt",e.getRegistry());
    register(new ItemBlock(BASALT_COBBLESTONE),"basalt_cobblestone",e.getRegistry());
  }

  @SubscribeEvent
  public static void potions(RegistryEvent.Register<Potion> e) {
    register(SWELTERING,"sweltering",e.getRegistry());
  }

    @EventHandler
  public void init(FMLInitializationEvent event) {
      OreDictionary.registerOre("basalt",BASALT);
  }


  private static <T extends IForgeRegistryEntry<T>> void register(T obj, String name, IForgeRegistry<T> registry) {
    register(obj,MODID,name,registry);
  }

  private static <T extends IForgeRegistryEntry<T>> void register(T obj, String domain, String name, IForgeRegistry<T> registry) {
    registry.register(obj.setRegistryName(new ResourceLocation(domain, name)));
  }

  @SubscribeEvent
  public static void models(ModelRegistryEvent e){
    registerModelLocation(BASALT);
    registerModelLocation(BASALT_COBBLESTONE);
  }

  private static void registerModelLocation(Block block) {
    registerModelLocation(Item.getItemFromBlock(block));
  }

  private static void registerModelLocation(Item item) {
    ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
  }

  @GameRegistry.ObjectHolder(MODID)
  public static class Objects {
    public static final Block BASALT = null;
    public static final Block BASALT_COBBLESTONE = null;
  }
}
