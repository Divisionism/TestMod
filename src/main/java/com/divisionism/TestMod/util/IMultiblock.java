package com.divisionism.TestMod.util;

import net.minecraft.block.BlockState;
import net.minecraft.world.World;

public interface IMultiblock {

    BlockState state(World worldIn);
    int height(World worldIn);
    int length(World worldIn);
    int width(World worldIn);
}
