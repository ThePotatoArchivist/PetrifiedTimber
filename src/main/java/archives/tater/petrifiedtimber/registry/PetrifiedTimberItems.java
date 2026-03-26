package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.item.StandingAndHangingBlockItem;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;

import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PetrifiedTimberItems {

    private static Item register(Identifier id, Function<Item.Properties, Item> block, Item.Properties properties) {
        var key = ResourceKey.create(Registries.ITEM, id);
        return Registry.register(BuiltInRegistries.ITEM, key, block.apply(properties.setId(key)));
    }

    private static Item register(String path, Function<Item.Properties, Item> block, Item.Properties properties) {
        return register(PetrifiedTimber.id(path), block, properties);
    }

    private static Item register(String path, Function<Item.Properties, Item> block) {
        return register(PetrifiedTimber.id(path), block, new Item.Properties());
    }

    private static Item register(String path, Item.Properties properties) {
        return register(path, Item::new, properties);
    }

    private static Item register(Block block, BiFunction<Block, Item.Properties, Item> item, Item.Properties properties) {
        return register(BuiltInRegistries.BLOCK.getKey(block), properties1 -> item.apply(block, properties1), properties.useBlockDescriptionPrefix());
    }

    private static Item register(Block primary, Block secondary, TriFunction<Block, Block, Item.Properties, Item> item, Item.Properties properties) {
        return register(primary, (block, properties1) -> item.apply(block, secondary, properties1), properties);
    }

    private static Item register(Block block, Item.Properties properties) {
        return register(block, BlockItem::new, properties);
    }

    private static Item register(Block block, BiFunction<Block, Item.Properties, Item> item) {
        return register(block, item, new Item.Properties());
    }

    private static Item register(Block block) {
        return register(block, new Item.Properties());
    }

    public static final Item PETRIFIED_OAK_LOG = register(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG);
    public static final Item PETRIFIED_OAK_WOOD = register(PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD);
    public static final Item PETRIFIED_STRIPPED_OAK_LOG = register(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG);
    public static final Item PETRIFIED_STRIPPED_OAK_WOOD = register(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_WOOD);
    public static final Item SHADOW_PETRIFIED_OAK_LOG = register(PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG);
    public static final Item SHADOW_PETRIFIED_OAK_WOOD = register(PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_WOOD);
    public static final Item WARM_PETRIFIED_OAK_LOG = register(PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG);
    public static final Item WARM_PETRIFIED_OAK_WOOD = register(PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_WOOD);
    public static final Item WATCHING_PETRIFIED_OAK_LOG = register(PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LOG);
    public static final Item WATCHING_PETRIFIED_OAK_WOOD = register(PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_WOOD);
    public static final Item CHERRY_PETRIFIED_OAK_LOG = register(PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LOG);
    public static final Item CHERRY_PETRIFIED_OAK_WOOD = register(PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_WOOD);
    public static final Item PETRIFIED_OAK_PLANKS = register(PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS);
    public static final Item PETRIFIED_OAK_LEAVES = register(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES);
    public static final Item SHADOW_PETRIFIED_OAK_LEAVES = register(PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LEAVES);
    public static final Item WARM_PETRIFIED_OAK_LEAVES = register(PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LEAVES);
    public static final Item WATCHING_PETRIFIED_OAK_LEAVES = register(PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LEAVES);
    public static final Item PALE_WATCHING_PETRIFIED_OAK_LEAVES = register(PetrifiedTimberBlocks.PALE_WATCHING_PETRIFIED_OAK_LEAVES);
    public static final Item CHERRY_PETRIFIED_OAK_LEAVES = register(PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LEAVES);
    public static final Item PETRIFIED_OAK_SAPLING = register(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING);
    public static final Item PETRIFIED_OAK_SEEDS = register("petrified_oak_seeds", properties -> new BlockItem(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING_CROP, properties));
    public static final Item PETRIFIED_RED_FLOWER = register(PetrifiedTimberBlocks.PETRIFIED_RED_FLOWER);
    public static final Item PETRIFIED_YELLOW_FLOWER = register(PetrifiedTimberBlocks.PETRIFIED_YELLOW_FLOWER);
    public static final Item PETRIFIED_CYAN_FLOWER = register(PetrifiedTimberBlocks.PETRIFIED_CYAN_FLOWER);
    public static final Item STACKED_ROCKS = register(PetrifiedTimberBlocks.STACKED_ROCKS);
    public static final Item MOSSY_STACKED_ROCKS = register(PetrifiedTimberBlocks.MOSSY_STACKED_ROCKS);
    public static final Item PETRIFIED_OAK_PRESSURE_PLATE = register(PetrifiedTimberBlocks.PETRIFIED_OAK_PRESSURE_PLATE);
    public static final Item PETRIFIED_OAK_TRAPDOOR = register(PetrifiedTimberBlocks.PETRIFIED_OAK_TRAPDOOR);
    public static final Item PETRIFIED_OAK_BUTTON = register(PetrifiedTimberBlocks.PETRIFIED_OAK_BUTTON);
    public static final Item PETRIFIED_OAK_STAIRS = register(PetrifiedTimberBlocks.PETRIFIED_OAK_STAIRS);
    public static final Item PETRIFIED_OAK_SLAB = register(PetrifiedTimberBlocks.PETRIFIED_OAK_SLAB);
    public static final Item PETRIFIED_OAK_FENCE_GATE = register(PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE_GATE);
    public static final Item PETRIFIED_OAK_FENCE = register(PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE);
    public static final Item RESIN_COVERED_OAK_LOG = register(PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG);
    public static final Item RESIN_COVERED_OAK_WOOD = register(PetrifiedTimberBlocks.RESIN_COVERED_OAK_WOOD);
    public static final Item RESIN_COVERED_STRIPPED_OAK_LOG = register(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG);
    public static final Item RESIN_COVERED_STRIPPED_OAK_WOOD = register(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_WOOD);
    public static final Item RESIN_COVERED_OAK_PLANKS = register(PetrifiedTimberBlocks.RESIN_COVERED_OAK_PLANKS);

    public static final Item WHITE_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.WHITE_PETRIFIED_APPLE, PetrifiedTimberBlocks.WHITE_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item ORANGE_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.ORANGE_PETRIFIED_APPLE, PetrifiedTimberBlocks.ORANGE_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item MAGENTA_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.MAGENTA_PETRIFIED_APPLE, PetrifiedTimberBlocks.MAGENTA_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item LIGHT_BLUE_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.LIGHT_BLUE_PETRIFIED_APPLE, PetrifiedTimberBlocks.LIGHT_BLUE_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item YELLOW_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.YELLOW_PETRIFIED_APPLE, PetrifiedTimberBlocks.YELLOW_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item LIME_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.LIME_PETRIFIED_APPLE, PetrifiedTimberBlocks.LIME_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item PINK_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.PINK_PETRIFIED_APPLE, PetrifiedTimberBlocks.PINK_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item GRAY_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.GRAY_PETRIFIED_APPLE, PetrifiedTimberBlocks.GRAY_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item LIGHT_GRAY_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.LIGHT_GRAY_PETRIFIED_APPLE, PetrifiedTimberBlocks.LIGHT_GRAY_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item CYAN_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.CYAN_PETRIFIED_APPLE, PetrifiedTimberBlocks.CYAN_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item PURPLE_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.PURPLE_PETRIFIED_APPLE, PetrifiedTimberBlocks.PURPLE_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item BLUE_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.BLUE_PETRIFIED_APPLE, PetrifiedTimberBlocks.BLUE_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item BROWN_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.BROWN_PETRIFIED_APPLE, PetrifiedTimberBlocks.BROWN_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item GREEN_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.GREEN_PETRIFIED_APPLE, PetrifiedTimberBlocks.GREEN_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item RED_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.RED_PETRIFIED_APPLE, PetrifiedTimberBlocks.RED_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());
    public static final Item BLACK_PETRIFIED_APPLE = register(PetrifiedTimberBlocks.BLACK_PETRIFIED_APPLE, PetrifiedTimberBlocks.BLACK_HANGING_PETRIFIED_APPLE, StandingAndHangingBlockItem::new, new Item.Properties());

    public static final Item PETRIFIED_OAK_SHELF = register(PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF, new Item.Properties().component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));
    public static final Item PETRIFIED_OAK_SIGN = register(PetrifiedTimberBlocks.PETRIFIED_OAK_SIGN, PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_SIGN, SignItem::new, new Item.Properties().stacksTo(16));
    public static final Item PETRIFIED_OAK_HANGING_SIGN = register(PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN, PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_HANGING_SIGN, HangingSignItem::new, new Item.Properties().stacksTo(16));
    public static final Item PETRIFIED_OAK_DOOR = register(PetrifiedTimberBlocks.PETRIFIED_OAK_DOOR, DoubleHighBlockItem::new);

    public static final Item PETRIFIED_OAK_BOAT = register(
            "petrified_oak_boat",
            properties -> new BoatItem(PetrifiedTimberEntities.PETRIFIED_OAK_BOAT, properties),
            new Item.Properties()
                    .stacksTo(1)
    );

    public static final Item PETRIFIED_OAK_CHEST_BOAT = register(
            "petrified_oak_chest_boat",
            properties -> new BoatItem(PetrifiedTimberEntities.PETRIFIED_OAK_CHEST_BOAT, properties),
            new Item.Properties()
                    .stacksTo(1)
    );

    public static final Item MELTED_RESIN_BOTTLE = register(
            "melted_resin_bottle",
            Item::new,
            new Item.Properties()
                    .craftRemainder(Items.GLASS_BOTTLE)
                    .stacksTo(16)
    );

    // Only used for recipe viewer
    public static final Item MELTED_RESIN_CAULDRON = register(
            "melted_resin_cauldron",
            Item::new,
            new Item.Properties()
                    .overrideDescription(PetrifiedTimberBlocks.RESIN_CAULDRON.getDescriptionId())
    );

    public static final Item PETRIFIED_LEAF = register("petrified_leaf", new Item.Properties());

    public static final String PETRIFIED_TIMBER_TAB_TITLE = "itemGroup.petrifiedtimber.petrified_timber";
    public static final CreativeModeTab PETRIFIED_TIMBER_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            PetrifiedTimber.id("petrified_timber"),
            FabricCreativeModeTab.builder()
                .title(Component.translatable(PETRIFIED_TIMBER_TAB_TITLE))
                .icon(PETRIFIED_OAK_LOG::getDefaultInstance)
                .displayItems((_, output) -> {
                    output.accept(PETRIFIED_OAK_LOG);
                    output.accept(PETRIFIED_OAK_WOOD);
                    output.accept(PETRIFIED_STRIPPED_OAK_LOG);
                    output.accept(PETRIFIED_STRIPPED_OAK_WOOD);
                    output.accept(SHADOW_PETRIFIED_OAK_LOG);
                    output.accept(SHADOW_PETRIFIED_OAK_WOOD);
                    output.accept(WARM_PETRIFIED_OAK_LOG);
                    output.accept(WARM_PETRIFIED_OAK_WOOD);
                    output.accept(WATCHING_PETRIFIED_OAK_LOG);
                    output.accept(WATCHING_PETRIFIED_OAK_WOOD);
                    output.accept(CHERRY_PETRIFIED_OAK_LOG);
                    output.accept(CHERRY_PETRIFIED_OAK_WOOD);
                    output.accept(PETRIFIED_OAK_PLANKS);
                    output.accept(PETRIFIED_OAK_STAIRS);
                    output.accept(PETRIFIED_OAK_SLAB);
                    output.accept(PETRIFIED_OAK_FENCE);
                    output.accept(PETRIFIED_OAK_FENCE_GATE);
                    output.accept(PETRIFIED_OAK_DOOR);
                    output.accept(PETRIFIED_OAK_TRAPDOOR);
                    output.accept(PETRIFIED_OAK_PRESSURE_PLATE);
                    output.accept(PETRIFIED_OAK_BUTTON);
                    output.accept(PETRIFIED_OAK_SIGN);
                    output.accept(PETRIFIED_OAK_HANGING_SIGN);
                    output.accept(PETRIFIED_OAK_SHELF);
                    output.accept(PETRIFIED_OAK_BOAT);
                    output.accept(PETRIFIED_OAK_CHEST_BOAT);
                    output.accept(PETRIFIED_OAK_LEAVES);
                    output.accept(SHADOW_PETRIFIED_OAK_LEAVES);
                    output.accept(WARM_PETRIFIED_OAK_LEAVES);
                    output.accept(WATCHING_PETRIFIED_OAK_LEAVES);
                    output.accept(PALE_WATCHING_PETRIFIED_OAK_LEAVES);
                    output.accept(CHERRY_PETRIFIED_OAK_LEAVES);
                    output.accept(PETRIFIED_LEAF);
                    output.accept(PETRIFIED_OAK_SAPLING);
                    output.accept(PETRIFIED_OAK_SEEDS);
                    output.accept(PETRIFIED_RED_FLOWER);
                    output.accept(PETRIFIED_YELLOW_FLOWER);
                    output.accept(PETRIFIED_CYAN_FLOWER);
                    output.accept(STACKED_ROCKS);
                    output.accept(MOSSY_STACKED_ROCKS);
                    output.accept(MELTED_RESIN_BOTTLE);
                    output.accept(RESIN_COVERED_OAK_LOG);
                    output.accept(RESIN_COVERED_OAK_WOOD);
                    output.accept(RESIN_COVERED_STRIPPED_OAK_LOG);
                    output.accept(RESIN_COVERED_STRIPPED_OAK_WOOD);
                    output.accept(RESIN_COVERED_OAK_PLANKS);
                    output.accept(WHITE_PETRIFIED_APPLE);
                    output.accept(LIGHT_GRAY_PETRIFIED_APPLE);
                    output.accept(GRAY_PETRIFIED_APPLE);
                    output.accept(BLACK_PETRIFIED_APPLE);
                    output.accept(BROWN_PETRIFIED_APPLE);
                    output.accept(RED_PETRIFIED_APPLE);
                    output.accept(ORANGE_PETRIFIED_APPLE);
                    output.accept(YELLOW_PETRIFIED_APPLE);
                    output.accept(LIME_PETRIFIED_APPLE);
                    output.accept(GREEN_PETRIFIED_APPLE);
                    output.accept(CYAN_PETRIFIED_APPLE);
                    output.accept(LIGHT_BLUE_PETRIFIED_APPLE);
                    output.accept(BLUE_PETRIFIED_APPLE);
                    output.accept(PURPLE_PETRIFIED_APPLE);
                    output.accept(MAGENTA_PETRIFIED_APPLE);
                    output.accept(PINK_PETRIFIED_APPLE);
                })
                .build()
    );

    public static void init() {

    }
}
