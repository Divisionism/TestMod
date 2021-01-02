package com.divisionism.TestMod.util;

import com.divisionism.TestMod.ExampleMod;
import com.divisionism.TestMod.util.helpers.KeyboardHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFluidContainer extends Item implements IModFluidContainer {

    private Fluids fluidContaining;
    private float amountContaining, maxCap, fillRate, flowRate;
    private boolean canOutput, canReceive;

    public AbstractFluidContainer(Properties properties) {
        super(properties);
        fluidContaining = Fluids.NONE;
        amountContaining = 0;
        canOutput = false;
        canReceive = false;
        maxCap = 0;
        fillRate = 0;
        flowRate = 0;
    }

    public AbstractFluidContainer(Properties properties, final float maxCap, final float fillRate, final float flowRate, boolean canReceive, boolean canOutput) {
        super(properties);
        this.fluidContaining = Fluids.NONE;
        this.setMaxCap(maxCap);
        this.setFillRate(fillRate);
        this.setFlowRate(flowRate);
        this.setCanReceive(canReceive);
        this.setCanOutput(canOutput);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        IFluidState fluidLooking = getFluidLooking(worldIn, playerIn);
        if (!worldIn.isRemote) {
            if (playerIn.isCrouching() && translateFluid(fluidLooking) != fluidContaining && amountContaining < 1000) {
                amountContaining = 0;
                setFluidContaining(translateFluid(fluidLooking));
            }
            else {
                if (fluidContaining != Fluids.NONE && translateFluid(fluidLooking) == fluidContaining && canReceive()) {
                    playerIn.playSound(SoundEvents.ITEM_BOTTLE_FILL, 1.0f, 1.0f);
                    fill();
                }
                else if (amountContaining >= 1000) {
                    if (!isFloating(getLookingPos(playerIn), worldIn)) {
                        if (!worldIn.dimension.doesWaterVaporize()) { worldIn.setBlockState(getLookingPos(playerIn), translateToBlockstate(fluidContaining)); }
                        else {
                            worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5f, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
                            for (int i = 0; i < 8; i++) {
                                worldIn.addParticle(ParticleTypes.LARGE_SMOKE, (double)getLookingPos(playerIn).getX() + Math.random(), (double)getLookingPos(playerIn).getY() + Math.random(), (double)getLookingPos(playerIn).getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
                            }
                        }
                        amountContaining -= 1000;
                    }
                }
            }
        }
        return new ActionResult<>(ActionResultType.PASS, this.getDefaultInstance());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("\u00A77Storing fluid: " + fluidContaining()));
        tooltip.add(new StringTextComponent("\u00A77" + currentFill() + " / " + maxCap() + " L"));
        if (KeyboardHelper.isHoldingShift()) {
            tooltip.add(new StringTextComponent("\u00A77Fill rate: " + fillRate() + " mL/click"));
            tooltip.add(new StringTextComponent("\u00A77Output rate: " + flowRate() + " mL/sec"));
        } else
            tooltip.add(new StringTextComponent("\u00A7e\u00A7o[Hold shift for more information]"));
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (amountContaining > maxCap())
            amountContaining = maxCap();
    }

    private IFluidState getFluidLooking(World worldIn, PlayerEntity playerIn) {
        RayTraceResult result = playerIn.pick(5.0f, 1.0f, true);
        BlockPos pos = new BlockPos(result.getHitVec());
        if (worldIn.getFluidState(pos) != net.minecraft.fluid.Fluids.EMPTY.getDefaultState())
            return worldIn.getFluidState(pos);
        return null;
    }

    private BlockPos getLookingPos(PlayerEntity playerIn) {
        RayTraceResult result = playerIn.pick(5.0f, 1.0f, false);
        BlockRayTraceResult bResult = (BlockRayTraceResult) result;
        BlockPos blockPos = bResult.getPos();
        Direction dir = bResult.getFace();
        return blockPos.offset(dir);
    }

    private Fluids translateFluid(@Nullable IFluidState state) {
        if (state != null) {
            if (state.getFluid() == net.minecraft.fluid.Fluids.WATER || state.getFluid() == net.minecraft.fluid.Fluids.FLOWING_WATER)
                return Fluids.WATER;
            else if (state.getFluid() == net.minecraft.fluid.Fluids.LAVA || state.getFluid() == net.minecraft.fluid.Fluids.FLOWING_LAVA)
                return Fluids.LAVA;
        }
        return Fluids.NONE;
    }

    private BlockState translateToBlockstate(@Nullable Fluids fluid) {
        if (fluid != null) {
            if (fluid == Fluids.WATER)
                return Blocks.WATER.getDefaultState();
            if (fluid == Fluids.LAVA)
                return Blocks.LAVA.getDefaultState();
        }
        return null;
    }

    // Please change this code in the future bro
    private boolean isFloating(BlockPos pos, World worldIn) {
        ArrayList<BlockState> surroundingBlocks = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            surroundingBlocks.add(worldIn.getBlockState(pos.add(x, 0, 0)));
        }
        for (int y = -1; y < 2; y++) {
            surroundingBlocks.add(worldIn.getBlockState(pos.add(0, y, 0)));
        }
        for (int z = -1; z < 2; z++) {
            surroundingBlocks.add(worldIn.getBlockState(pos.add(0, 0, z)));
        }
        ExampleMod.LOGGER.info(surroundingBlocks.toString());
        int counter = 0;

        for (BlockState bs : surroundingBlocks) {
            if (bs.isAir())
                counter++;
        }

        ExampleMod.LOGGER.info(counter);

        return counter == 9;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) { return fluidContaining != Fluids.NONE; }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) { return (maxCap() - amountContaining) / maxCap(); }

    @Override
    public Fluids fluidContaining() { return fluidContaining; }

    @Override
    public void setFluidContaining(Fluids fluid) { fluidContaining = fluid; }

    @Override
    public void fill() { amountContaining += fillRate(); }

    @Override
    public void drain() { amountContaining -= fillRate(); }

    @Override
    public float maxCap() { return this.maxCap; }

    @Override
    public float currentFill() { return this.amountContaining; }

    @Override
    public boolean canReceive() { return this.canReceive; }

    @Override
    public boolean canOutput() { return this.canOutput; }

    @Override
    public float fillRate() { return this.fillRate; }

    @Override
    public float flowRate() { return this.flowRate; }

    @Override
    public void setFillRate(float amount) { this.fillRate = amount; }

    @Override
    public void setMaxCap(float amount) { this.maxCap = amount; }

    @Override
    public void setFlowRate(float amount) { this.flowRate = amount; }

    @Override
    public void setCanOutput(boolean bool) { this.canOutput = bool; }

    @Override
    public void setCanReceive(boolean bool) { this.canReceive = bool; }

    @Override
    public void setCurrentFill(float amount) {
        this.amountContaining = amount;
    }
}
