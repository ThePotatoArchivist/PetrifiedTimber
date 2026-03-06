package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.block.*;
import archives.tater.petrifiedtimber.mixin.BlockSetTypeInvoker;
import archives.tater.petrifiedtimber.mixin.WoodTypeInvoker;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PetrifiedTimberBlocks {

    private static Block register(String path, Function<BlockBehaviour.Properties, Block> block, BlockBehaviour.Properties properties) {
        var key = ResourceKey.create(Registries.BLOCK, PetrifiedTimber.id(path));
        return Registry.register(BuiltInRegistries.BLOCK, key, block.apply(properties.setId(key)));
    }

    private static Block register(String path, BlockBehaviour.Properties properties) {
        return register(path, Block::new, properties);
    }

    private static <T> Block register(String path, BiFunction<T, BlockBehaviour.Properties, Block> block, T type, BlockBehaviour.Properties properties) {
        return register(path, properties1 -> block.apply(type, properties1), properties);
    }

    private static BlockBehaviour.Properties petrifiedWoodProperties() {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .strength(2f, 6f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops();
    }

    private static BlockBehaviour.Properties appleProperties() {
        return BlockBehaviour.Properties.of()
                .strength(1f)
                .sound(SoundType.STONE);
    }

    private static BlockBehaviour.Properties hangingAppleProperties() {
        return appleProperties()
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .noCollision();
    }

    private static BlockBehaviour.Properties variant(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block)
                .overrideLootTable(block.getLootTable())
                .overrideDescription(block.getDescriptionId());
    }

    public static final BlockSetType PETRIFIED_OAK_BLOCK_SET = BlockSetTypeInvoker.invokeRegister(new BlockSetType(
            PetrifiedTimber.uId("petrified_oak"),
            true,
            true,
            false,
            BlockSetType.PressurePlateSensitivity.MOBS,
            SoundType.STONE,
            SoundEvents.NETHER_WOOD_DOOR_CLOSE,
            SoundEvents.NETHER_WOOD_DOOR_OPEN,
            SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE,
            SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON
    ));

    public static final WoodType PETRIFIED_OAK_WOOD_TYPE = WoodTypeInvoker.invokeRegister(new WoodType(
            PetrifiedTimber.uId("petrified_oak"),
            PETRIFIED_OAK_BLOCK_SET,
            SoundType.STONE,
            SoundType.NETHER_WOOD_HANGING_SIGN,
            SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE,
            SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN
    ));

    public static final Block PETRIFIED_OAK_WOOD = register("petrified_oak_wood", RotatedPillarBlock::new, petrifiedWoodProperties());

    public static final Block PETRIFIED_OAK_PLANKS = register("petrified_oak_planks", petrifiedWoodProperties());

    public static final Block PETRIFIED_OAK_SAPLING = register(
            "petrified_oak_sapling",
            SaplingBlock::new,
            PetrifiedTimberWorldgen.PETRIFIED_OAK_GROWER,
            petrifiedWoodProperties()
                    .noCollision()
                    .strength(1f, 3f)
                    .pushReaction(PushReaction.DESTROY)
    );

    public static final Block PETRIFIED_OAK_LOG = register("petrified_oak_log", RotatedPillarBlock::new, petrifiedWoodProperties());

    public static final Block PETRIFIED_STRIPPED_OAK_LOG = register("petrified_stripped_oak_log", RotatedPillarBlock::new, petrifiedWoodProperties());

    public static final Block PETRIFIED_STRIPPED_OAK_WOOD = register("petrified_stripped_oak_wood", RotatedPillarBlock::new, petrifiedWoodProperties());

    public static final Block PETRIFIED_OAK_LEAVES = register(
            "petrified_oak_leaves",
            PetrifiedLeavesBlock::new,
            petrifiedWoodProperties()
                    .sound(SoundType.TUFF)
                    .noOcclusion()
                    .isSuffocating(Blocks::never)
                    .isViewBlocking(Blocks::never)
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(Blocks::never)
    );

    public static final Block PETRIFIED_OAK_SHELF = register("petrified_oak_shelf", ShelfBlock::new, petrifiedWoodProperties());

    public static final Block PETRIFIED_OAK_SIGN = register(
            "petrified_oak_sign",
            StandingSignBlock::new,
            PETRIFIED_OAK_WOOD_TYPE,
            petrifiedWoodProperties()
                    .forceSolidOn()
                    .noCollision()
    );

    public static final Block PETRIFIED_OAK_WALL_SIGN = register(
            "petrified_oak_wall_sign",
            WallSignBlock::new,
            PETRIFIED_OAK_WOOD_TYPE,
            variant(PETRIFIED_OAK_SIGN)
    );

    public static final Block PETRIFIED_OAK_HANGING_SIGN = register(
            "petrified_oak_hanging_sign",
            CeilingHangingSignBlock::new,
            PETRIFIED_OAK_WOOD_TYPE,
            petrifiedWoodProperties()
                    .forceSolidOn()
                    .noCollision()
    );

    public static final Block PETRIFIED_OAK_WALL_HANGING_SIGN = register(
            "petrified_oak_wall_hanging_sign",
            WallHangingSignBlock::new,
            PETRIFIED_OAK_WOOD_TYPE,
            variant(PETRIFIED_OAK_HANGING_SIGN)
    );

    public static final Block PETRIFIED_OAK_PRESSURE_PLATE = register(
            "petrified_oak_pressure_plate",
            PressurePlateBlock::new,
            PETRIFIED_OAK_BLOCK_SET,
            petrifiedWoodProperties()
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
    );

    public static final Block PETRIFIED_OAK_TRAPDOOR = register(
            "petrified_oak_trapdoor",
            TrapDoorBlock::new,
            PETRIFIED_OAK_BLOCK_SET,
            petrifiedWoodProperties()
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
    );

    public static final Block POTTED_PETRIFIED_OAK_SAPLING = register(
            "potted_petrified_oak_sapling",
            FlowerPotBlock::new,
            PETRIFIED_OAK_SAPLING,
            Blocks.flowerPotProperties()
    );

    public static final Block PETRIFIED_OAK_BUTTON = register(
            "petrified_oak_button",
            properties -> new ButtonBlock(PETRIFIED_OAK_BLOCK_SET, 20, properties),
            Blocks.buttonProperties()
    );

    public static final Block PETRIFIED_OAK_STAIRS = register(
            "petrified_oak_stairs",
            StairBlock::new,
            PETRIFIED_OAK_PLANKS.defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(PETRIFIED_OAK_PLANKS)
    );

    public static final Block PETRIFIED_OAK_SLAB = register(
            "petrified_oak_slab",
            SlabBlock::new,
            petrifiedWoodProperties()
    );

    public static final Block PETRIFIED_OAK_FENCE_GATE = register(
            "petrified_oak_fence_gate",
            FenceGateBlock::new,
            PETRIFIED_OAK_WOOD_TYPE,
            petrifiedWoodProperties()
                    .forceSolidOn()
    );

    public static final Block PETRIFIED_OAK_FENCE = register(
            "petrified_oak_fence",
            FenceBlock::new,
            petrifiedWoodProperties()
    );

    public static final Block PETRIFIED_OAK_DOOR = register(
            "petrified_oak_door",
            DoorBlock::new,
            PETRIFIED_OAK_BLOCK_SET,
            petrifiedWoodProperties()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
    );

    public static final BlockFamily PETRIFIED_OAK_FAMILY = BlockFamilies.familyBuilder(PETRIFIED_OAK_PLANKS)
            .button(PETRIFIED_OAK_BUTTON)
            .fence(PETRIFIED_OAK_FENCE)
            .fenceGate(PETRIFIED_OAK_FENCE_GATE)
            .pressurePlate(PETRIFIED_OAK_PRESSURE_PLATE)
            .sign(PETRIFIED_OAK_SIGN, PETRIFIED_OAK_WALL_SIGN)
            .slab(PETRIFIED_OAK_SLAB)
            .stairs(PETRIFIED_OAK_STAIRS)
            .door(PETRIFIED_OAK_DOOR)
            .trapdoor(PETRIFIED_OAK_TRAPDOOR)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static final Block RESIN_COVERED_OAK_LOG = register(
            "resin_covered_oak_log",
            PetrifyingRotatedPillarBlock::new,
            PETRIFIED_OAK_LOG,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)
                    .randomTicks()
    );

    public static final Block RESIN_COVERED_OAK_WOOD = register(
            "resin_covered_oak_wood",
            PetrifyingRotatedPillarBlock::new,
            PETRIFIED_OAK_WOOD,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)
                    .randomTicks()
    );

    public static final Block RESIN_COVERED_STRIPPED_OAK_LOG = register(
            "resin_covered_stripped_oak_log",
            PetrifyingRotatedPillarBlock::new,
            PETRIFIED_STRIPPED_OAK_LOG,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                    .randomTicks()
    );

    public static final Block RESIN_COVERED_STRIPPED_OAK_WOOD = register(
            "resin_covered_stripped_oak_wood",
            PetrifyingRotatedPillarBlock::new,
            PETRIFIED_STRIPPED_OAK_WOOD,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                    .randomTicks()
    );

    public static final Block RESIN_COVERED_OAK_PLANKS = register(
            "resin_covered_oak_planks",
            PetrifyingBlock::new,
            PETRIFIED_OAK_PLANKS,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                    .randomTicks()
    );

    public static final Block RESIN_CAULDRON = register(
            "resin_cauldron",
            ResinCauldronBlock::new,
            ResinCauldronBlock.INTERACTION,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WATER_CAULDRON)
    );

    public static final Block WHITE_PETRIFIED_APPLE = register("white_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block ORANGE_PETRIFIED_APPLE = register("orange_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block MAGENTA_PETRIFIED_APPLE = register("magenta_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block LIGHT_BLUE_PETRIFIED_APPLE = register("light_blue_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block YELLOW_PETRIFIED_APPLE = register("yellow_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block LIME_PETRIFIED_APPLE = register("lime_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block PINK_PETRIFIED_APPLE = register("pink_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block GRAY_PETRIFIED_APPLE = register("gray_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block LIGHT_GRAY_PETRIFIED_APPLE = register("light_gray_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block CYAN_PETRIFIED_APPLE = register("cyan_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block PURPLE_PETRIFIED_APPLE = register("purple_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block BLUE_PETRIFIED_APPLE = register("blue_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block BROWN_PETRIFIED_APPLE = register("brown_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block GREEN_PETRIFIED_APPLE = register("green_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block RED_PETRIFIED_APPLE = register("red_petrified_apple", AppleBlock::new, appleProperties());
    public static final Block BLACK_PETRIFIED_APPLE = register("black_petrified_apple", AppleBlock::new, appleProperties());

    public static final Block WHITE_HANGING_PETRIFIED_APPLE = register("white_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block ORANGE_HANGING_PETRIFIED_APPLE = register("orange_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block MAGENTA_HANGING_PETRIFIED_APPLE = register("magenta_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block LIGHT_BLUE_HANGING_PETRIFIED_APPLE = register("light_blue_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block YELLOW_HANGING_PETRIFIED_APPLE = register("yellow_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block LIME_HANGING_PETRIFIED_APPLE = register("lime_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block PINK_HANGING_PETRIFIED_APPLE = register("pink_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block GRAY_HANGING_PETRIFIED_APPLE = register("gray_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block LIGHT_GRAY_HANGING_PETRIFIED_APPLE = register("light_gray_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block CYAN_HANGING_PETRIFIED_APPLE = register("cyan_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block PURPLE_HANGING_PETRIFIED_APPLE = register("purple_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block BLUE_HANGING_PETRIFIED_APPLE = register("blue_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block BROWN_HANGING_PETRIFIED_APPLE = register("brown_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block GREEN_HANGING_PETRIFIED_APPLE = register("green_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block RED_HANGING_PETRIFIED_APPLE = register("red_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());
    public static final Block BLACK_HANGING_PETRIFIED_APPLE = register("black_hanging_petrified_apple", HangingAppleBlock::new, hangingAppleProperties());

    static {
        BlockEntityType.SIGN.addSupportedBlock(PETRIFIED_OAK_SIGN);
        BlockEntityType.SIGN.addSupportedBlock(PETRIFIED_OAK_WALL_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(PETRIFIED_OAK_HANGING_SIGN);
        BlockEntityType.HANGING_SIGN.addSupportedBlock(PETRIFIED_OAK_WALL_HANGING_SIGN);
        BlockEntityType.SHELF.addSupportedBlock(PETRIFIED_OAK_SHELF);

        FlammableBlockRegistry.getDefaultInstance().add(RESIN_COVERED_OAK_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(RESIN_COVERED_OAK_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(RESIN_COVERED_OAK_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(RESIN_COVERED_STRIPPED_OAK_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(RESIN_COVERED_STRIPPED_OAK_WOOD, 5, 5);

        ResinCauldronBlock.bootstrap();

        Item.BY_BLOCK.put(RESIN_CAULDRON, Items.CAULDRON);
    }

    public static void init() {

    }
}
