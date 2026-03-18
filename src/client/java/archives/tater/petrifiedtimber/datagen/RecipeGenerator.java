package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItemTags;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new PTRecipeProvider(provider, recipeOutput);
    }

    @Override
    public String getName() {
        return "Recipes";
    }

    private static class PTRecipeProvider extends RecipeProvider {
        private void stonecutterFamily(BlockFamily family) {
            family.getVariants().forEach((variant, block) -> {
                if (variant != Variant.WALL_SIGN)
                    stonecutterResultFromBase(RecipeCategory.DECORATIONS, block, family.getBaseBlock(), variant == Variant.SLAB ? 2 : 1);
            });
        }

        public PTRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            super(provider, recipeOutput);
        }

        @Override
        public void buildRecipes() {
            generateRecipes(PetrifiedTimberBlocks.PETRIFIED_OAK_FAMILY, FeatureFlags.VANILLA_SET);
            stonecutterFamily(PetrifiedTimberBlocks.PETRIFIED_OAK_FAMILY);

            shelf(PetrifiedTimberItems.PETRIFIED_OAK_SHELF, PetrifiedTimberItems.PETRIFIED_STRIPPED_OAK_LOG);
            hangingSign(PetrifiedTimberItems.PETRIFIED_OAK_HANGING_SIGN, PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG);
            woodenBoat(PetrifiedTimberItems.PETRIFIED_OAK_BOAT, PetrifiedTimberItems.PETRIFIED_OAK_PLANKS);
            chestBoat(PetrifiedTimberItems.PETRIFIED_OAK_CHEST_BOAT, PetrifiedTimberItems.PETRIFIED_OAK_BOAT);

            stonecutterResultFromBase(RecipeCategory.DECORATIONS, PetrifiedTimberItems.PETRIFIED_OAK_SHELF, PetrifiedTimberItems.PETRIFIED_STRIPPED_OAK_LOG);
            stonecutterResultFromBase(RecipeCategory.DECORATIONS, PetrifiedTimberItems.PETRIFIED_OAK_HANGING_SIGN, PetrifiedTimberItems.PETRIFIED_STRIPPED_OAK_LOG);

            shapeless(RecipeCategory.DECORATIONS, PetrifiedTimberItems.PETRIFIED_OAK_SEEDS, 4)
                    .requires(PetrifiedTimberItemTags.PETRIFIED_APPLES)
                    .unlockedBy("has_petrified_aple", has(PetrifiedTimberItemTags.PETRIFIED_APPLES))
                    .save(output);

            threeByThreePacker(RecipeCategory.DECORATIONS, PetrifiedTimberItems.PETRIFIED_OAK_LEAVES, PetrifiedTimberItems.PETRIFIED_LEAF);

            colorItemWithDye(
                    List.of(
                            Items.BLACK_DYE,
                            Items.BLUE_DYE,
                            Items.BROWN_DYE,
                            Items.CYAN_DYE,
                            Items.GRAY_DYE,
                            Items.GREEN_DYE,
                            Items.LIGHT_BLUE_DYE,
                            Items.LIGHT_GRAY_DYE,
                            Items.LIME_DYE,
                            Items.MAGENTA_DYE,
                            Items.ORANGE_DYE,
                            Items.PINK_DYE,
                            Items.PURPLE_DYE,
                            Items.RED_DYE,
                            Items.YELLOW_DYE,
                            Items.WHITE_DYE
                    ),
                    List.of(
                            PetrifiedTimberItems.BLACK_PETRIFIED_APPLE,
                            PetrifiedTimberItems.BLUE_PETRIFIED_APPLE,
                            PetrifiedTimberItems.BROWN_PETRIFIED_APPLE,
                            PetrifiedTimberItems.CYAN_PETRIFIED_APPLE,
                            PetrifiedTimberItems.GRAY_PETRIFIED_APPLE,
                            PetrifiedTimberItems.GREEN_PETRIFIED_APPLE,
                            PetrifiedTimberItems.LIGHT_BLUE_PETRIFIED_APPLE,
                            PetrifiedTimberItems.LIGHT_GRAY_PETRIFIED_APPLE,
                            PetrifiedTimberItems.LIME_PETRIFIED_APPLE,
                            PetrifiedTimberItems.MAGENTA_PETRIFIED_APPLE,
                            PetrifiedTimberItems.ORANGE_PETRIFIED_APPLE,
                            PetrifiedTimberItems.PINK_PETRIFIED_APPLE,
                            PetrifiedTimberItems.PURPLE_PETRIFIED_APPLE,
                            PetrifiedTimberItems.RED_PETRIFIED_APPLE,
                            PetrifiedTimberItems.YELLOW_PETRIFIED_APPLE,
                            PetrifiedTimberItems.WHITE_PETRIFIED_APPLE
                    ),
                    PetrifiedTimber.sId("petrified_apple"),
                    RecipeCategory.DECORATIONS
            );
        }
    }
}
