package com.divisionism.TestMod.blocks;

import com.divisionism.TestMod.util.Multiblock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TestMachine extends Block implements Multiblock {

    BlockPos bPos;

    public TestMachine(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        bPos = pos;
    }

    @Override
    public BlockState state(World worldIn) {
        return worldIn.getBlockState(bPos);
    }

    @Override
    public int height(World worldIn) {
        if (worldIn.getBlockState(bPos.up()) == state(worldIn) || worldIn.getBlockState(bPos.down()) == state(worldIn)) { return 1; }
        else { return 0; }
    }

    @Override
    public int length(World worldIn) {
        return 0;
    }

    @Override
    public int width(World worldIn) {
        return 0;
    }
}
