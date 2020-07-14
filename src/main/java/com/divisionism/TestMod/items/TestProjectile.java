package com.divisionism.TestMod.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class TestProjectile extends Item {

    public TestProjectile() { super(new Item.Properties().group(ItemGroup.MATERIALS)); }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        ItemStack itemstack = playerIn.getHeldItem(handIn);
        SnowballEntity projectileEntity = new SnowballEntity(worldIn, playerIn);
        TNTEntity tnt = new TNTEntity(EntityType.TNT, worldIn);
        worldIn.playSound((PlayerEntity) null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote) {
            projectileEntity.setItem(itemstack);
            projectileEntity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(projectileEntity);
        }

        if (!projectileEntity.isAirBorne) {
            tnt.setPosition(projectileEntity.getPosition().getX(), projectileEntity.getPosition().getY(), projectileEntity.getPosition().getZ());
            tnt.setFuse(1);
            worldIn.addEntity(tnt);
            System.out.println("Summoned a new tnt entity at " + tnt.getPosition());
            projectileEntity.remove();
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }
        return ActionResult.resultSuccess(itemstack);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
        item = player.getHeldItem(player.getActiveHand());
        TNTEntity tnt = new TNTEntity(EntityType.TNT, player.world);
        ItemEntity itemE = new ItemEntity(EntityType.ITEM, player.world);

        System.out.println(item.toString());
        itemE.setItem(item);
        System.out.println(itemE.getPosition());

        tnt.setPosition(itemE.getPosition().getX(), itemE.getPosition().getY(), itemE.getPosition().getZ());
        tnt.setFuse(1);
        player.world.addEntity(tnt);
        System.out.println("Summoned a new tnt entity at " + tnt.getPosition());

        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TextComponent() {
            @Override
            public String getUnformattedComponentText() {
                return "\u00A7e\u00A7o[Don't use if you won't your face intact]";
            }

            @Override
            public ITextComponent shallowCopy() {
                return null;
            }
        });
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
