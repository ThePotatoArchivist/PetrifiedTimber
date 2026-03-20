package archives.tater.petrifiedtimber.client.rrv;

import archives.tater.petrifiedtimber.block.Petrifying;
import archives.tater.petrifiedtimber.block.ResinCauldronBlock;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberFluids;
import archives.tater.petrifiedtimber.rrv.PetrificationServerRecipe;

import net.minecraft.core.registries.BuiltInRegistries;

import cc.cassian.rrv.api.ReliableRecipeViewerClientPlugin;
import cc.cassian.rrv.api.recipe.ItemView;
import cc.cassian.rrv.common.builtin.interaction.WorldInteractionClientRecipe;
import cc.cassian.rrv.common.builtin.interaction.WorldInteractionServerRecipe;
import cc.cassian.rrv.common.extra.FluidStack;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;

public class PetrifiedTimberRRVClient implements ReliableRecipeViewerClientPlugin {
    @Override
    public void onIntegrationInitialize() {
        ItemView.addClientRecipeWrapper(PetrificationServerRecipe.TYPE, _recipe -> BuiltInRegistries.BLOCK.stream()
                .filter(Petrifying.class::isInstance)
                .map(block -> new PetrificationClientRecipe(block, ((Petrifying) block).getPetrifiedBlock()))
                .toList()
        );

        ItemView.addClientRecipeWrapper(WorldInteractionServerRecipe.TYPE, _recipe -> ResinCauldronBlock.RESIN_COATING.entrySet().stream()
                .map(entry -> new WorldInteractionClientRecipe(
                        SlotContent.of(entry.getKey()),
                        SlotContent.of(new FluidStack(PetrifiedTimberFluids.MELTED_RESIN, (int) ResinCauldronBlock.FLUID_PER_LEVEL / 81)),
                        SlotContent.of(entry.getValue())
                ))
                .toList()
        );
    }
}
