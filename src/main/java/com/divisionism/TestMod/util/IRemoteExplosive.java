package com.divisionism.TestMod.util;

import net.minecraft.world.World;

public interface IRemoteExplosive {

    float radius();
    IRemoteController detonator();
    void explode(World worldIn);
}
