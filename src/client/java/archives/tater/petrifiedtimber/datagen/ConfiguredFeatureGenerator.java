package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;
import archives.tater.petrifiedtimber.worldgen.BiomeDependentFeature;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ConfiguredFeatureGenerator extends FabricDynamicRegistryProvider {

    public ConfiguredFeatureGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static Holder<PlacedFeature> placedFeature(Holder<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        return Holder.direct(new PlacedFeature(feature, Arrays.asList(modifiers)));
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK_FEATURE, new ConfiguredFeature<>(
                PetrifiedTimberWorldgen.BIOME_DEPENDENT,
                new BiomeDependentFeature.Configuration(
                        placedFeature(registries.getOrThrow(TreeFeatures.OAK)),
                        BiomeDependentFeature.entry(
                                placedFeature(registries.getOrThrow(TreeFeatures.SPRUCE)),
                                HolderSet.direct(
                                        registries::getOrThrow,
                                        Biomes.TAIGA,
                                        Biomes.WINDSWEPT_FOREST,
                                        Biomes.WINDSWEPT_HILLS,
                                        Biomes.SNOWY_PLAINS,
                                        Biomes.SNOWY_TAIGA,
                                        Biomes.OLD_GROWTH_PINE_TAIGA,
                                        Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                                        Biomes.GROVE
                                )
                        )
                )
        ));
    }

    @Override
    public String getName() {
        return "Configured Features";
    }
}
