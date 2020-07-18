package com.divisionism.TestMod.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SuperTool extends Item {

    public SuperTool(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        List<BlockPos> Blocks = new ArrayList<>();

        Blocks.add(playerIn.getPosition().add(0, -1, 0));
        Blocks.add(playerIn.getPosition().add(0, -1, 1));
        Blocks.add(playerIn.getPosition().add(0, -1, -1));
        Blocks.add(playerIn.getPosition().add(1, -1, 0));
        Blocks.add(playerIn.getPosition().add(-1, -1, 0));
        Blocks.add(playerIn.getPosition().add(1, 0, 1));
        Blocks.add(playerIn.getPosition().add(1, 0, -1));
        Blocks.add(playerIn.getPosition().add(-1, 0, 1));
        Blocks.add(playerIn.getPosition().add(-1, 0, -1));

        for (BlockPos bp : Blocks) {
            worldIn.destroyBlock(bp, true);
        }

        Blocks.clear();

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
