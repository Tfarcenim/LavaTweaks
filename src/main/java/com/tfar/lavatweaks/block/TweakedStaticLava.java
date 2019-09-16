package com.tfar.lavatweaks.block;

import com.tfar.lavatweaks.EventHandler;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class TweakedStaticLava extends BlockStaticLiquid {
  public TweakedStaticLava() {
    super(Material.LAVA);
  }

  @Override
  public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
    super.updateTick(world, pos, state, rand);
    if (world.isAirBlock(pos.up()) && world.provider.getDimension() != -1 && Math.random() < .7)
      world.setBlockState(pos, EventHandler.basalt.getDefaultState());
    for (EnumFacing dir : EnumFacing.VALUES) {
      IBlockState state1 = world.getBlockState(pos.offset(dir));
      if (state1.getMaterial() == Material.GROUND || state1.getMaterial() == Material.GRASS || state1.getBlock() == Blocks.STONE ||  state1.getBlock() == Blocks.COBBLESTONE)
        world.setBlockState(pos.offset(dir), EventHandler.basalt.getDefaultState());
    }
  }

  @Override
  public TweakedStaticLava disableStats() {
    return (TweakedStaticLava) super.disableStats();
  }
}
