package com.divisionism.TestMod.items;

import com.divisionism.TestMod.blocks.TestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TestItem extends Item {

    public TestItem() { super(new Item.Properties().group(ItemGroup.MATERIALS)); }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (TestBlock.blockPos.size() != 0) {
            TestBlock.Detonation(worldIn, playerIn);
        } else {
            System.out.println("No C4 to detonate");
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
