package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.flag.FeatureFlags;

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
        public PTRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            super(provider, recipeOutput);
        }

        @Override
        public void buildRecipes() {
            generateRecipes(PetrifiedTimberBlocks.PETRIFIED_OAK_FAMILY, FeatureFlags.VANILLA_SET);
        }
    }
}
