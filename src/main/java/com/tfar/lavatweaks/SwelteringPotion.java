package com.tfar.lavatweaks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SwelteringPotion extends Potion {

  private static final ResourceLocation POTIONS_LOCATION = new ResourceLocation(LavaTweaks.MODID,"textures/potion/sweltering.png");

  protected SwelteringPotion(boolean isBadEffectIn, int liquidColorIn) {
    super(isBadEffectIn, liquidColorIn);
  }

  @Override
  public boolean hasStatusIcon() {
    return true;
  }

  @Nonnull
  @Override
  public SwelteringPotion setIconIndex(int p_76399_1_, int p_76399_2_) {
    return (SwelteringPotion)super.setIconIndex(p_76399_1_, p_76399_2_);
  }

  @Nonnull
  @Override
  public List<ItemStack> getCurativeItems() {
    return new ArrayList<>();//this effect cannot be cursed with items
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void renderInventoryEffect(PotionEffect effect, Gui gui, int x, int y, float z) {
    Minecraft.getMinecraft().getTextureManager().bindTexture(POTIONS_LOCATION);
    Gui.drawModalRectWithCustomSizedTexture(x + 7, y + 7, 0, 0, 16, 16,16,16);  }


  @Override
  @SideOnly(Side.CLIENT)
  public void renderHUDEffect(PotionEffect effect, Gui gui, int x, int y, float z, float alpha) {
    Minecraft.getMinecraft().getTextureManager().bindTexture(POTIONS_LOCATION);
    Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 16, 16,16,16);  }
}
