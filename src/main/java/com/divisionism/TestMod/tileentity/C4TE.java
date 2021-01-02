package com.divisionism.TestMod.tileentity;

import com.divisionism.TestMod.items.TestItem;
import com.divisionism.TestMod.util.IRemoteController;
import com.divisionism.TestMod.util.IRemoteExplosive;
import com.divisionism.TestMod.util.Registries;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Objects;

public class C4TE extends TileEntity implements ITickableTileEntity, IRemoteExplosive {

    public C4TE(TileEntityType<?> tileEntityTypeIn) { super(tileEntityTypeIn); }

    public C4TE() { this(Registries.TEST_BLOCK_TILE_ENTITY.get()); }

    @Override
    public void tick() {
        if (detonator().sentSignal()) { explode(Objects.requireNonNull(this.getWorld())); }
    }

    @Override
    public float radius() { return 5.0f; }

    @Override
    public IRemoteController detonator() { return new TestItem(); }

    @Override
    public void explode(World worldIn) {
        worldIn.createExplosion(worldIn.getClosestPlayer(this.pos.getX(), this.pos.getY(), this.pos.getZ()), this.pos.getX(), this.pos.getY(), this.pos.getZ(), this.radius(), Explosion.Mode.DESTROY);
    }
}
