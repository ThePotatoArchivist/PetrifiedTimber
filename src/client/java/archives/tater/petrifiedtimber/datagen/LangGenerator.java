package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
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

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translateFamily(translationBuilder, "Petrified Oak", PetrifiedTimberBlocks.PETRIFIED_OAK_FAMILY);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_LOG);
        add(translationBuilder, PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_WOOD);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING);
        add(translationBuilder, PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF);
        add(translationBuilder, PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN);
        translationBuilder.add(PetrifiedTimberItems.PETRIFIED_TIMBER_TAB_TITLE, "Petrified Timber");
    }
}
