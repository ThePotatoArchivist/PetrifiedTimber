package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberEntities;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import static archives.tater.petrifiedtimber.PetrifiedTimberUtil.snakeToTitle;

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

    private static void add(TranslationBuilder translationBuilder, EntityType<?> item) {
        translationBuilder.add(item, snakeToTitle(BuiltInRegistries.ENTITY_TYPE.getKey(item).getPath()));
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translateFamily(translationBuilder, "Petrified Oak", PetrifiedTimberBlocks.PETRIFIED_OAK_FAMILY);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING);
        add(translationBuilder, PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.RESIN_COVERED_OAK_PLANKS);

        add(translationBuilder, PetrifiedTimberItems.PETRIFIED_OAK_BOAT);
        translationBuilder.add(PetrifiedTimberItems.PETRIFIED_OAK_CHEST_BOAT, "Petrified Oak Boat with Chest");
        add(translationBuilder, PetrifiedTimberEntities.PETRIFIED_OAK_BOAT);
        translationBuilder.add(PetrifiedTimberEntities.PETRIFIED_OAK_CHEST_BOAT, "Petrified Oak Boat with Chest");
        translationBuilder.add(PetrifiedTimberItems.PETRIFIED_TIMBER_TAB_TITLE, "Petrified Timber");
    }
}
