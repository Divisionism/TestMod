package com.divisionism.TestMod.util;

import com.divisionism.TestMod.ExampleMod;
import com.divisionism.TestMod.blocks.IdkBlock;
import com.divisionism.TestMod.blocks.TestBlock;
import com.divisionism.TestMod.items.SuperTool;
import com.divisionism.TestMod.items.TestItem;
import com.divisionism.TestMod.items.TestProjectile;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registries {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", TestItem::new);
    public static final RegistryObject<Item> TEST_PROJECTILE = ITEMS.register("test_projectile", TestProjectile::new);
    public static final RegistryObject<Item> SUPER_TOOL = ITEMS.register("super_tool", () -> new SuperTool(new Item.Properties().group(ItemGroup.MATERIALS)));

    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", TestBlock::new);
    public static final RegistryObject<Item> TEST_BLOCK_ITEM = ITEMS.register("test_block", () -> new BlockItem(TEST_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)
            .addToolType(ToolType.PICKAXE, 2)
            .rarity(Rarity.EPIC)
        )
    );
    public static final RegistryObject<Block> IDK_BLOCK = BLOCKS.register("idk_block", () -> new IdkBlock(Block.Properties.create(Material.ROCK)));
    public static final RegistryObject<Item> IDK_BLOCK_ITEM = ITEMS.register("idk_block", () -> new BlockItem(IDK_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
}
