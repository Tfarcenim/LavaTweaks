package com.tfar.lavatweaks.block;

import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class TweakedFlowingLava extends BlockDynamicLiquid {
  public TweakedFlowingLava() {
    super(Material.LAVA);
  }

  @Override
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    super.updateTick(worldIn, pos, state, rand);
  }

  @Override
  public TweakedFlowingLava disableStats() {
    return (TweakedFlowingLava) super.disableStats();
  }
}
