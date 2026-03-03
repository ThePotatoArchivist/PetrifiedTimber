package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

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

    public static final Item PETRIFIED_OAK_WOOD = register(PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD);
    public static final Item PETRIFIED_OAK_PLANKS = register(PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS);
    public static final Item PETRIFIED_OAK_SAPLING = register(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING);
    public static final Item PETRIFIED_OAK_LOG = register(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG);
    public static final Item STRIPPED_PETRIFIED_OAK_LOG = register(PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_LOG);
    public static final Item STRIPPED_PETRIFIED_OAK_WOOD = register(PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_WOOD);
    public static final Item PETRIFIED_OAK_LEAVES = register(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES);
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

    public static final String PETRIFIED_TIMBER_TAB_TITLE = "itemGroup.petrifiedtimber.petrified_timber";
    public static final CreativeModeTab PETRIFIED_TIMBER_TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            PetrifiedTimber.id("petrified_timber"),
            FabricItemGroup.builder()
                .title(Component.translatable(PETRIFIED_TIMBER_TAB_TITLE))
                .icon(PETRIFIED_OAK_LOG::getDefaultInstance)
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(PETRIFIED_OAK_LOG);
                    output.accept(PETRIFIED_OAK_WOOD);
                    output.accept(STRIPPED_PETRIFIED_OAK_LOG);
                    output.accept(STRIPPED_PETRIFIED_OAK_WOOD);
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
                    output.accept(PETRIFIED_OAK_SAPLING);
                    output.accept(PETRIFIED_OAK_LEAVES);
                    output.accept(RESIN_COVERED_OAK_LOG);
                    output.accept(RESIN_COVERED_OAK_WOOD);
                    output.accept(RESIN_COVERED_STRIPPED_OAK_LOG);
                    output.accept(RESIN_COVERED_STRIPPED_OAK_WOOD);
                    output.accept(RESIN_COVERED_OAK_PLANKS);
                })
                .build()
    );

    public static void init() {

    }
}
