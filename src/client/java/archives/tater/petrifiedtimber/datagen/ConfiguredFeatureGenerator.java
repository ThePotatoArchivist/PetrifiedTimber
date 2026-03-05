package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;
import archives.tater.petrifiedtimber.worldgen.BiomeDependentFeature;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import static archives.tater.petrifiedtimber.worldgen.BiomeDependentFeature.entry;

public class ConfiguredFeatureGenerator extends FabricDynamicRegistryProvider {

    public ConfiguredFeatureGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static Holder<PlacedFeature> placedFeature(Holder<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        return Holder.direct(new PlacedFeature(feature, Arrays.asList(modifiers)));
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        var biomes = registries.lookupOrThrow(Registries.BIOME);

        entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK_FEATURE, new ConfiguredFeature<>(
                PetrifiedTimberWorldgen.BIOME_DEPENDENT,
                new BiomeDependentFeature.Configuration(
                        placedFeature(registries.getOrThrow(TreeFeatures.OAK)),
                        entry(
                                placedFeature(registries.getOrThrow(TreeFeatures.SPRUCE)),
                                biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_SPRUCE)
                        ),
                        entry(
                                placedFeature(registries.getOrThrow(TreeFeatures.BIRCH)),
                                biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_BIRCH)
                        ),
                        entry(
                                placedFeature(registries.getOrThrow(TreeFeatures.ACACIA)),
                                biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_ACACIA)
                        ),
                        entry(
                                placedFeature(registries.getOrThrow(TreeFeatures.JUNGLE_TREE)),
                                biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_JUNGLE)
                        ),
                        entry(
                                placedFeature(registries.getOrThrow(TreeFeatures.DARK_OAK)),
                                biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_DARK_OAK)
                        )
                )
        ));
    }

    @Override
    public String getName() {
        return "Configured Features";
    }
}
