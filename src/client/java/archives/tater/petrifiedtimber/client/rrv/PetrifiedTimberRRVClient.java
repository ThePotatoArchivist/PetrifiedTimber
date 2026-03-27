package archives.tater.petrifiedtimber.client.rrv;

import archives.tater.petrifiedtimber.block.Petrifying;
import archives.tater.petrifiedtimber.block.ResinCauldronBlock;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;
import archives.tater.petrifiedtimber.rrv.PetrificationServerRecipe;

import net.minecraft.core.registries.BuiltInRegistries;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.common.builtin.interaction.WorldInteractionClientRecipe;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;

public class PetrifiedTimberRRVClient implements ReliableRecipeViewerClientPlugin {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addClientRecipeWrapper(PetrificationServerRecipe.TYPE, _recipe -> BuiltInRegistries.BLOCK.stream()
                .filter(Petrifying.class::isInstance)
                .map(block -> new PetrificationClientRecipe(block, ((Petrifying) block).getPetrifiedBlock()))
                .toList()
        );

        ItemView.addClientReloadCallback(() -> {
            var cauldron = SlotContent.of(PetrifiedTimberItems.MELTED_RESIN_CAULDRON);

            ResinCauldronBlock.RESIN_COATING.forEach((input, result) ->
                    ItemView.addWorldInteractionRecipe(new WorldInteractionClientRecipe(
                            SlotContent.of(input),
                            SlotContent.of(cauldron),
                            SlotContent.of(result)
                    ))
            );
        });
    }
}
