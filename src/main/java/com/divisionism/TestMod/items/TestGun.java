package com.divisionism.TestMod.items;

import com.divisionism.TestMod.ExampleMod;
import com.divisionism.TestMod.entities.BulletEntity;
import com.divisionism.TestMod.util.Registries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TestGun extends Item {

    public TestGun(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        Vec3d speed = new Vec3d(5.0, 5.0, 5.0);

        BulletEntity bulletEntity = new BulletEntity(worldIn, playerIn);
        if (!worldIn.isRemote) {
            bulletEntity.setVelocity(speed.getX(), speed.getY(), speed.getZ());
            worldIn.addEntity(bulletEntity);
            bulletEntity.playSound(Registries.BULLET_SHOT.get(), 1.0f, bulletEntity.getEntityWorld().rand.nextFloat());
            ExampleMod.LOGGER.info("Shots fired");
        }

        return new ActionResult<>(ActionResultType.SUCCESS, new ItemStack(this.getItem()));
    }
}
