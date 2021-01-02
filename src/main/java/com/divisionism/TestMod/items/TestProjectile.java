package com.divisionism.TestMod.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class TestProjectile extends Item {

    public TestProjectile() { super(new Item.Properties().group(ItemGroup.MATERIALS)); }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        SnowballEntity entity = new SnowballEntity(worldIn, playerIn);

        if (!worldIn.isRemote) {
            worldIn.addEntity(entity);
            if (!entity.isAirBorne) {
                worldIn.createExplosion(playerIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), 5, Explosion.Mode.DESTROY);
                return new ActionResult<>(ActionResultType.SUCCESS, new ItemStack(this));
            }
        }
        return new ActionResult<>(ActionResultType.FAIL, new ItemStack(this));
    }
}
