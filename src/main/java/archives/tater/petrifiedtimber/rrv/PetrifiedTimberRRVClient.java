package archives.tater.petrifiedtimber.rrv;

import archives.tater.petrifiedtimber.block.Petrifying;

import net.minecraft.core.registries.BuiltInRegistries;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;

public class PetrifiedTimberRRVClient implements ReliableRecipeViewerClientPlugin {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addClientRecipeWrapper(PetrificationServerRecipe.TYPE, _recipe -> BuiltInRegistries.BLOCK.stream()
                .filter(Petrifying.class::isInstance)
                .map(block -> new PetrificationClientRecipe(block, ((Petrifying) block).getPetrifiedBlock()))
                .toList()
        );
    }
}
