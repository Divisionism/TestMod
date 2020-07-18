package com.divisionism.TestMod.blocks;

import com.divisionism.TestMod.util.Registries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
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
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        worldIn.createExplosion(player, pos.getX(), pos.getY(), pos.getZ(), 10.0f, Explosion.Mode.DESTROY);

        try {
            blockPos.remove(blockPos.indexOf(pos));
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            return;
        }
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        worldIn.createExplosion(worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ()), pos.getX(), pos.getY(), pos.getZ(), 10.0f, Explosion.Mode.DESTROY);

        try {
            blockPos.remove(blockPos.indexOf(pos));
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            return;
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        if (placer instanceof PlayerEntity) {

            if (!blockPos.contains(pos)) {
                blockPos.add(pos);
                System.out.println("Added new pos at " + pos);
            }
        }
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {

        for (BlockPos Pos : blockPos) {

            if (Pos == pos)
                blockPos.remove(blockPos.indexOf(Pos));
        }
    }

    public static void Detonation(World worldIn, PlayerEntity player) {

        for (BlockPos bp : blockPos) {
            worldIn.createExplosion(player, bp.getX(), bp.getY(), bp.getZ(), 10.0f, Explosion.Mode.DESTROY);
            worldIn.destroyBlock(bp, false);
        }
        blockPos.clear();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        worldIn.destroyBlock(pos, true);
        player.inventory.addItemStackToInventory(Registries.TEST_BLOCK_ITEM.get().getDefaultInstance());
        System.out.println("Destroyed block at " + pos);
        System.out.println(player.getHeldItemMainhand());

        return ActionResultType.SUCCESS;
    }
}
