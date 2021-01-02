package com.divisionism.TestMod.items;

import com.divisionism.TestMod.util.AbstractFluidContainer;
import com.divisionism.TestMod.util.Fluids;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class TestFluidContainer extends AbstractFluidContainer {

    private final CompoundNBT nbt = new CompoundNBT();

    public TestFluidContainer(Properties properties, float maxCap, float fillRate, float flowRate, boolean canReceive, boolean canOutput) {
        super(properties, maxCap, fillRate, flowRate, canReceive, canOutput);
        this.readFromNBT(nbt);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
        this.writeToNBT(nbt);
    }

    public void writeToNBT(CompoundNBT nbt) {
        nbt.putFloat("amount", this.currentFill());
        nbt.putInt("fluidId", this.fluidContaining().getId());
    }

    public void readFromNBT(CompoundNBT nbt) {
        this.setCurrentFill(nbt.getFloat("amount"));
        this.setFluidContaining(Fluids.values()[nbt.getInt("fluidId")]);
    }
}
