package com.divisionism.TestMod.util;

import com.divisionism.TestMod.ExampleMod;
import com.divisionism.TestMod.blocks.IdkBlock;
import com.divisionism.TestMod.blocks.TestBlock;
import com.divisionism.TestMod.blocks.TestChest;
import com.divisionism.TestMod.blocks.TestMachine;
import com.divisionism.TestMod.blocks.containers.TestChestContainer;
import com.divisionism.TestMod.entities.BulletEntity;
import com.divisionism.TestMod.items.*;
import com.divisionism.TestMod.tileentity.C4TE;
import com.divisionism.TestMod.tileentity.TestChestTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registries {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, ExampleMod.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, ExampleMod.MOD_ID);
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, ExampleMod.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, ExampleMod.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, ExampleMod.MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //Items
    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", TestItem::new);
    public static final RegistryObject<Item> TEST_PROJECTILE = ITEMS.register("test_projectile", TestProjectile::new);
    public static final RegistryObject<Item> SUPER_TOOL = ITEMS.register("super_tool", () -> new SuperTool(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> FLUID_CONTAINER = ITEMS.register("fluid_container", () -> new TestFluidContainer(new Item.Properties().group(ItemGroup.TOOLS), 2000, 100, 400, true, true));
    public static final RegistryObject<Item> CUM_BUCKET = ITEMS.register("cum_bucket", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> META_DATA_ITEM = ITEMS.register("meta_data_item", () -> new MetaDataItem(new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Item> TEST_GUN = ITEMS.register("test_gun", () -> new TestGun(new Item.Properties().group(ItemGroup.MATERIALS).maxStackSize(1)));

    //Blocks
    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", TestBlock::new);
    public static final RegistryObject<Item> TEST_BLOCK_ITEM = ITEMS.register("test_block", () -> new BlockItem(TEST_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)
            .addToolType(ToolType.PICKAXE, 2)
            .rarity(Rarity.EPIC)
        )
    );
    public static final RegistryObject<Block> IDK_BLOCK = BLOCKS.register("idk_block", () -> new IdkBlock(Block.Properties.create(Material.ROCK)));
    public static final RegistryObject<Item> IDK_BLOCK_ITEM = ITEMS.register("idk_block", () -> new BlockItem(IDK_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
    public static final RegistryObject<Block> TEST_CHEST = BLOCKS.register("test_chest", () -> new TestChest(Block.Properties.create(Material.WOOD)));
    public static final RegistryObject<Item> TEST_CHEST_ITEM = ITEMS.register("test_chest", () -> new BlockItem(TEST_CHEST.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    //Tile Entities
    public static final RegistryObject<TileEntityType<TestChestTileEntity>> TEST_CHEST_TILE = TILE_ENTITIES
            .register("test_chest", () -> TileEntityType.Builder
                    .create(TestChestTileEntity::new, TEST_CHEST.get()).build(null));
    public static final RegistryObject<TileEntityType<C4TE>> TEST_BLOCK_TILE_ENTITY = TILE_ENTITIES
            .register("c4te", () -> TileEntityType.Builder
                    .create(C4TE::new, TEST_BLOCK.get()).build(null));

    //Containers
    public static final RegistryObject<ContainerType<TestChestContainer>> TEST_CHEST_CONTAINER = CONTAINERS
            .register("test_chest", () -> IForgeContainerType.create(TestChestContainer::new));

    //Features
    public static final RegistryObject<Structure<TestFeatureConfig>> TEST_FEATURE = FEATURES.register("test_feature", () -> new TestFeature(TestFeatureConfig::deserialize));

    //Misc
    public static final RegistryObject<Block> TEST_MACHINE = BLOCKS.register("test_machine", () -> new TestMachine(Block.Properties.create(Material.ROCK)));
    public static final RegistryObject<Item> TEST_MACHINE_ITEM = ITEMS.register("test_machine", () -> new BlockItem(TEST_MACHINE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));

    //Entities
    public static final RegistryObject<EntityType<BulletEntity>> BULLET = ENTITIES.register("bullet",
            () -> EntityType.Builder.<BulletEntity>create(BulletEntity::new, EntityClassification.MISC)
                    .size(0.5f, 0.5f)
                    .build(new ResourceLocation(ExampleMod.MOD_ID, "bullet_entity").toString()));

    //Sounds
    public static final RegistryObject<SoundEvent> BULLET_SHOT = SOUNDS.register("entity.bullet_shot",
            () -> new SoundEvent(new ResourceLocation("entity.bullet_shot")));
}
