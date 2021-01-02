package com.divisionism.TestMod.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class MetaDataItem extends Item {

    public MetaDataItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean updateItemStackNBT(CompoundNBT nbt) {
        nbt.putInt("test", 1000);
        return super.updateItemStackNBT(nbt);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        stack.serializeNBT();
        //stack.write();
    }
}
