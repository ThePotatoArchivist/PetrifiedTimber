package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.FeatureTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.concurrent.CompletableFuture;

public class FeatureTagGenerator extends FabricTagsProvider<ConfiguredFeature<?, ?>> {
    public FeatureTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.CONFIGURED_FEATURE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(FeatureTags.CAN_SPAWN_FROM_BONE_MEAL)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FLOWER);
    }
}
