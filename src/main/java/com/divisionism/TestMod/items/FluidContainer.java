package com.divisionism.TestMod.items;

import com.divisionism.TestMod.util.Fluids;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class FluidContainer extends Item {

    List<Integer> FluidsL = new ArrayList<>();

    public FluidContainer(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        for (Fluids i : Fluids.values()) {
            System.out.println(i.toString() + " - " + i.getId());
        }
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, this.getDefaultInstance());
    }
}
