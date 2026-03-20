package archives.tater.petrifiedtimber.rrv;

import cc.cassian.rrv.api.ReliableRecipeViewerPlugin;
import cc.cassian.rrv.api.recipe.ItemView;

public class PetrifiedTimberRRV implements ReliableRecipeViewerPlugin {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addServerRecipeProvider(recipeList ->
                recipeList.add(new PetrificationServerRecipe()));
    }
}
