package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.client.rrv.PetrificationClientRecipe;
import archives.tater.petrifiedtimber.client.rrv.PetrificationClientRecipeType;
import archives.tater.petrifiedtimber.registry.*;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import static archives.tater.petrifiedtimber.PetrifiedTimberUtil.snakeToTitle;
import static net.minecraft.util.Util.makeDescriptionId;

public class LangGenerator extends FabricLanguageProvider {
    public LangGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    private static void translateFamily(TranslationBuilder builder, String name, BlockFamily family) {
        family.getVariants().forEach((variant, block) -> {
            if (variant == BlockFamily.Variant.WALL_SIGN) return;
            builder.add(block, name + " " + snakeToTitle(variant.name()));
        });
    }

    private static void add(TranslationBuilder builder, Block block) {
        builder.add(block, snakeToTitle(BuiltInRegistries.BLOCK.getKey(block).getPath()));
    }

    private static void add(TranslationBuilder translationBuilder, Item item) {
        translationBuilder.add(item, snakeToTitle(BuiltInRegistries.ITEM.getKey(item).getPath()));
    }

    private static void add(TranslationBuilder translationBuilder, EntityType<?> entity) {
        translationBuilder.add(entity, snakeToTitle(BuiltInRegistries.ENTITY_TYPE.getKey(entity).getPath()));
    }

    private static void add(TranslationBuilder translationBuilder, TagKey<?> tag) {
        translationBuilder.add(tag, snakeToTitle(tag.location().getPath()));
    }

    private static void add(TranslationBuilder translationBuilder, AdvancementGenerator.AdvancementTranslations advancementTranslations, String title, String description) {
        translationBuilder.add(advancementTranslations.title(), title);
        translationBuilder.add(advancementTranslations.description(), description);
    }

    private static void addBiome(TranslationBuilder translationBuilder, ResourceKey<Biome> biome, String value) {
        translationBuilder.add(biome.identifier().toLanguageKey("biome"), value);
    }

    private static void addBiome(TranslationBuilder translationBuilder, ResourceKey<Biome> biome) {
        addBiome(translationBuilder, biome, snakeToTitle(biome.identifier().getPath()));
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translateFamily(translationBuilder, "Petrified Oak", PetrifiedTimberBlocks.PETRIFIED_OAK_FAMILY);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES);
        add(translationBuilder, PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LEAVES);
        add(translationBuilder, PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LEAVES);
        add(translationBuilder, PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LEAVES);
        add(translationBuilder, PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LEAVES);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING);
        add(translationBuilder, PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING_CROP);
        add(translationBuilder, PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING_CROP);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_RED_FLOWER);
        add(translationBuilder, PetrifiedTimberBlocks.POTTED_PETRIFIED_RED_FLOWER);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_YELLOW_FLOWER);
        add(translationBuilder, PetrifiedTimberBlocks.POTTED_PETRIFIED_YELLOW_FLOWER);
        add(translationBuilder, PetrifiedTimberBlocks.SHADOW_PETRIFIED_ROOTS);
        add(translationBuilder, PetrifiedTimberBlocks.STACKED_ROCKS);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_OAK_PLANKS);

        add(translationBuilder, PetrifiedTimberItems.PETRIFIED_OAK_SEEDS);
        add(translationBuilder, PetrifiedTimberBlocks.WHITE_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.WHITE_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.ORANGE_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.ORANGE_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.MAGENTA_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.MAGENTA_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.LIGHT_BLUE_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.LIGHT_BLUE_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.YELLOW_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.YELLOW_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.LIME_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.LIME_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.PINK_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.PINK_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.GRAY_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.GRAY_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.LIGHT_GRAY_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.LIGHT_GRAY_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.CYAN_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.CYAN_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.PURPLE_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.PURPLE_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.BLUE_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.BLUE_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.BROWN_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.BROWN_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.GREEN_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.GREEN_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.RED_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.RED_HANGING_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.BLACK_PETRIFIED_APPLE);
        add(translationBuilder, PetrifiedTimberBlocks.BLACK_HANGING_PETRIFIED_APPLE);

        add(translationBuilder, PetrifiedTimberItems.PETRIFIED_OAK_BOAT);
        translationBuilder.add(PetrifiedTimberItems.PETRIFIED_OAK_CHEST_BOAT, "Petrified Oak Boat with Chest");
        add(translationBuilder, PetrifiedTimberEntities.PETRIFIED_OAK_BOAT);
        translationBuilder.add(PetrifiedTimberEntities.PETRIFIED_OAK_CHEST_BOAT, "Petrified Oak Boat with Chest");
        translationBuilder.add(PetrifiedTimberItems.PETRIFIED_TIMBER_TAB_TITLE, "Petrified Timber");

        translationBuilder.add(PetrifiedTimberBlocks.RESIN_CAULDRON, "Melted Resin Cauldron");
        translationBuilder.add(PetrifiedTimberItems.MELTED_RESIN_BOTTLE, "Bottle of Melted Resin");
        translationBuilder.add(PetrifiedTimberItems.PETRIFIED_LEAF, "Petrified Leaf");

        translationBuilder.add(makeDescriptionId("block", BuiltInRegistries.FLUID.getKey(PetrifiedTimberFluids.MELTED_RESIN)), "Melted Resin");

        add(translationBuilder, PetrifiedTimberItemTags.PETRIFIED_OAK_LOGS);
        add(translationBuilder, PetrifiedTimberItemTags.SHADOW_PETRIFIED_OAK_LOGS);
        add(translationBuilder, PetrifiedTimberItemTags.WARM_PETRIFIED_OAK_LOGS);
        add(translationBuilder, PetrifiedTimberItemTags.WATCHING_PETRIFIED_OAK_LOGS);
        add(translationBuilder, PetrifiedTimberItemTags.CHERRY_PETRIFIED_OAK_LOGS);
        add(translationBuilder, PetrifiedTimberItemTags.ALL_PETRIFIED_OAK_LOGS);
        add(translationBuilder, PetrifiedTimberItemTags.RESIN_COVERED_OAK_LOGS);
        add(translationBuilder, PetrifiedTimberItemTags.PETRIFIED_OAK_LEAVES);
        add(translationBuilder, PetrifiedTimberItemTags.PETRIFIED_APPLES);
        add(translationBuilder, PetrifiedTimberItemTags.CAN_ACQUIRE_FROM_PETRIFYING);

        add(translationBuilder, AdvancementGenerator.ROOT, "Petrified Timber", "TODO");
        add(translationBuilder, AdvancementGenerator.MELT_RESIN, "Dripping Steaming Sap", "Melt resin into a cauldron");
        add(translationBuilder, AdvancementGenerator.COLLECT_PETRIFIED_WOOD, "I might as well be stone too", "Bury resin covered wood underground for several days");
        add(translationBuilder, AdvancementGenerator.COLLECT_PETRIFIED_LEAVES, "Is it fall already?", "Discover a petrified bush in a swamp");
        add(translationBuilder, AdvancementGenerator.COLLECT_PETRIFIED_APPLE, "OW! My teeth", "Find a petrified apple");
        add(translationBuilder, AdvancementGenerator.PLANT_PETRIFIED_SEEDS, "TODO", "Plant petrified oak seeds");
        add(translationBuilder, AdvancementGenerator.COLLECT_ALL_LOGS, "Petrifying Camouflage", "Collect every variant of petrified log");
        add(translationBuilder, AdvancementGenerator.FIND_PETRIFIED_FOREST, "Just as I remember it!", "Discover a forest lost to time");

        addBiome(translationBuilder, PetrifiedTimberWorldgen.PETRIFIED_FOREST);
        addBiome(translationBuilder, PetrifiedTimberWorldgen.PETRIFIED_PLAINS);
        addBiome(translationBuilder, PetrifiedTimberWorldgen.PETRIFIED_RIDGE);

        translationBuilder.add(PetrificationClientRecipeType.DISPLAY_NAME, "Petrification");
        translationBuilder.add(PetrificationClientRecipe.COVER_DESCRIPTION_TRANSLATION, "Must be on all sides");
        translationBuilder.add(PetrificationClientRecipe.CATALYST_DESCRIPTION_TRANSLATION, "Must be on at least one side");
        translationBuilder.add(PetrificationClientRecipe.ACCELERATOR_DESCRIPTION_TRANSLATION, "Speeds up petrification if on at least one side");
    }
}
