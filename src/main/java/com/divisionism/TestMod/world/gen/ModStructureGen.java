package com.divisionism.TestMod.world.gen;

import com.divisionism.TestMod.ExampleMod;
import com.divisionism.TestMod.util.Registries;
import com.divisionism.TestMod.util.TestFeatureConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModStructureGen {

    public static void generateStructure() {

        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Registries.TEST_FEATURE.get().withConfiguration(new TestFeatureConfig(1.0F))
                    .withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
        }
    }
}
