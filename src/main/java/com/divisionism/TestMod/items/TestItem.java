package com.divisionism.TestMod.items;

import com.divisionism.TestMod.util.IRemoteController;
import com.divisionism.TestMod.util.RemoteControllerTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TestItem extends Item implements IRemoteController {

    private boolean isActivated;

    public TestItem() { super(new Item.Properties().group(ItemGroup.MATERIALS)); }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        activate();
        isActivated = false;
        return new ActionResult<>(ActionResultType.SUCCESS, ItemStack.EMPTY);
    }

    @Override
    public RemoteControllerTypes type() { return RemoteControllerTypes.DETONATOR; }

    @Override
    public boolean sentSignal() { return isActivated; }

    @Override
    public void activate() {
        isActivated = true;
    }
}
