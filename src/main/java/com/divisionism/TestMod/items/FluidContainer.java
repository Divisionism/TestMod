package com.divisionism.TestMod.items;

import com.divisionism.TestMod.util.Fluids;
import com.divisionism.TestMod.util.IModFluidContainer;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class FluidContainer extends Item implements IModFluidContainer {

    int id = 3;
    public static BlockState getLookingBlock;
    Fluids[] FluidList = Fluids.values();

    Fluids fluid_containing = FluidList[id];

    public FluidContainer(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        Vec3d lookingAt = playerIn.getLookVec();
        getLookingBlock = worldIn.getBlockState(new BlockPos(0, 0, 0).add(lookingAt.x, lookingAt.y, lookingAt.z));

        System.out.println(getLookingBlock.toString());

        return new ActionResult<ItemStack>(ActionResultType.PASS, this.getDefaultInstance());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Containing fluid: " + fluid_containing));
        tooltip.add(new StringTextComponent(currentFill() + " / " + maxCap() + " mL"));
        tooltip.add(new StringTextComponent("Looking at block " + getLookingBlock.toString()));
    }

    @Override
    public int maxCap() {
        return 1000;
    }

    @Override
    public int currentFill() {
        return 0;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    @Override
    public boolean canOutput() {
        return false;
    }

    @Override
    public int fillRate() {
        return 1000;
    }

    @Override
    public int flowRate() { return 1000; }
}
