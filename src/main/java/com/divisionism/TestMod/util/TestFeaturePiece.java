package com.divisionism.TestMod.util;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.ScatteredStructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;

import java.util.Random;

public class TestFeaturePiece extends ScatteredStructurePiece {

    private boolean placedChest;
    public static IStructurePieceType TFP = IStructurePieceType.register(TestFeaturePiece::new, "TFP");

    public TestFeaturePiece(Random random, int x, int z) {
        super(TestFeaturePiece.TFP, random, x, 64, z, 12, 10, 15);
    }

    public TestFeaturePiece(TemplateManager templateManager, CompoundNBT compoundNBT) {
        super(TestFeaturePiece.TFP, compoundNBT);
        this.placedChest = compoundNBT.getBoolean("placedChest");
    }

    @Override
    protected void readAdditional(CompoundNBT tagCompound) {
        super.readAdditional(tagCompound);
        tagCompound.putBoolean("placedChest", this.placedChest);
    }

    @Override
    public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
        if (!this.isInsideBounds(worldIn, mutableBoundingBoxIn, 0)) {
            return false;
        } else {
            for (int i = chunkPosIn.x; i < chunkPosIn.x + 11; i++) {
                this.setBlockState(worldIn, Blocks.END_STONE.getDefaultState(), i, 0, chunkPosIn.z, mutableBoundingBoxIn);
            }
            if (!this.placedChest) {
                this.placedChest = this.generateChest(worldIn, mutableBoundingBoxIn, randomIn, 0, 0, 0, LootTables.CHESTS_IGLOO_CHEST);
            }
            return true;
        }
    }
}
