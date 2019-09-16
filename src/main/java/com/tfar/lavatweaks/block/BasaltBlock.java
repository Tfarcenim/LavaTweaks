package com.tfar.lavatweaks.block;

import com.tfar.lavatweaks.LavaTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.Random;

public class BasaltBlock extends Block {
  public BasaltBlock() {
    super(Material.ROCK);
  }

  @Nonnull
  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return Item.getItemFromBlock(LavaTweaks.Objects.BASALT_COBBLESTONE);
  }
}
