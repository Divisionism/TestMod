package com.divisionism.TestMod.blocks;

import com.divisionism.TestMod.util.Registries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestBlock extends Block {

    public static List<BlockPos> blockPos = new ArrayList<BlockPos>();

    private static final VoxelShape BLOCK = Stream.of(
            Block.makeCuboidShape(4, 0, 1, 12, 3, 14),
            Block.makeCuboidShape(8, 3, 6, 10, 4, 10),
            Block.makeCuboidShape(3.75, 3, 6, 8, 3.25, 6.25),
            Block.makeCuboidShape(3.75, 3, 8, 8, 3.25, 8.25),
            Block.makeCuboidShape(3.5, 2.5, 5.9, 4, 3, 6.4),
            Block.makeCuboidShape(3.5, 2.5, 7.9, 4, 3, 8.4),
            Block.makeCuboidShape(8.5, 3.5999999999999996, 6.300000000000006, 9, 4.1, 6.800000000000006),
            Block.makeCuboidShape(3.9, 0, 2, 4, 3.1, 3),
            Block.makeCuboidShape(3.9, -2.7755575615628914e-17, 12, 4, 3.1000000000000005, 13),
            Block.makeCuboidShape(3.9, -2.7755575615628914e-17, 7, 4, 3.1000000000000005, 8),
            Block.makeCuboidShape(12, -2.7755575615628914e-17, 12, 12.1, 3.1000000000000005, 13),
            Block.makeCuboidShape(12, -2.7755575615628914e-17, 7, 12.1, 3.1000000000000005, 8),
            Block.makeCuboidShape(10, 3, 7, 10.1, 4.1000000000000005, 8),
            Block.makeCuboidShape(7.9000000000000075, 3, 7, 8.000000000000007, 4.1000000000000005, 8),
            Block.makeCuboidShape(12, -2.7755575615628914e-17, 2, 12.1, 3.1000000000000005, 3),
            Block.makeCuboidShape(4, 3, 12, 12, 3.1, 13),
            Block.makeCuboidShape(4, 3, 7, 12, 3.1, 8),
            Block.makeCuboidShape(8, 4, 7, 10, 4.1, 8),
            Block.makeCuboidShape(4, 3, 2, 12, 3.1, 3),
            Block.makeCuboidShape(8.2, 3.6999999999999997, 3, 8.399999999999999, 3.8999999999999995, 6)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public TestBlock() { super(Block.Properties.create(Material.WOOD).hardnessAndResistance(10.0f, 10.0f)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(1)
            .slipperiness(100)
            .jumpFactor(2.0f)
            .lightValue(2)
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, net.minecraft.util.math.BlockPos pos, ISelectionContext context) {
        return BLOCK;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return Registries.TEST_BLOCK_TILE_ENTITY.get().create();
    }
}
