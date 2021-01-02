package com.divisionism.TestMod.util;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.BuriedTreasureConfig;

public class TestFeatureConfig implements IFeatureConfig {
    public final float probability;

    public TestFeatureConfig(float probability) {
        this.probability = probability;
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("probability"), ops.createFloat(this.probability))));
    }

    public static <T> TestFeatureConfig deserialize(Dynamic<T> p_214684_0_) {
        float f = p_214684_0_.get("probability").asFloat(0.0F);
        return new TestFeatureConfig(f);
    }
}
