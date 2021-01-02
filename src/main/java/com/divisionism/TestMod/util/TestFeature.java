package com.divisionism.TestMod.util;

import com.divisionism.TestMod.ExampleMod;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;


import java.util.function.Function;

public class TestFeature extends ScatteredStructure<TestFeatureConfig> {
    public TestFeature(Function<Dynamic<?>, ? extends TestFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    protected int getSeedModifier() {
        return 12345678;
    }

    @Override
    public IStartFactory getStartFactory() {
        return TestFeature.Start::new;
    }

    @Override
    public String getStructureName() {
        return ExampleMod.MOD_ID +":test_structure";
    }

    @Override
    public int getSize() {
        return 1;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> p_i225807_1_, int p_i225807_2_, int p_i225807_3_, MutableBoundingBox p_i225807_4_, int p_i225807_5_, long p_i225807_6_) {
            super(p_i225807_1_, p_i225807_2_, p_i225807_3_, p_i225807_4_, p_i225807_5_, p_i225807_6_);
        }

        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            TestFeaturePiece testFeaturePiece = new TestFeaturePiece(this.rand, chunkX * 16, chunkZ * 16);
            this.components.add(testFeaturePiece);
            this.recalculateStructureSize();
        }
    }
}
