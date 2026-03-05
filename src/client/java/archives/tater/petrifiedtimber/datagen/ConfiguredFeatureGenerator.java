package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
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
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
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

    private static ConfiguredFeature<TreeConfiguration, TreeFeature> modifyTree(Holder<ConfiguredFeature<?, ?>> original) {
        var config = (TreeConfiguration) original.value().config();
        return new ConfiguredFeature<>((TreeFeature) original.value().feature(), new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG),
                config.trunkPlacer,
                BlockStateProvider.simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES),
                config.foliagePlacer,
                config.rootPlacer,
                config.minimumSize
        ).build());
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        var biomes = registries.lookupOrThrow(Registries.BIOME);

        entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK_FEATURE, new ConfiguredFeature<>(
                PetrifiedTimberWorldgen.BIOME_DEPENDENT,
                BiomeDependentFeature.builder(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK_OAK, modifyTree(registries.getOrThrow(TreeFeatures.OAK)))))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK_SPRUCE, modifyTree(registries.getOrThrow(TreeFeatures.SPRUCE)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_SPRUCE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK_BIRCH, modifyTree(registries.getOrThrow(TreeFeatures.BIRCH)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_BIRCH))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK_ACACIA, modifyTree(registries.getOrThrow(TreeFeatures.ACACIA)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_ACACIA))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK_JUNGLE, modifyTree(registries.getOrThrow(TreeFeatures.JUNGLE_TREE)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_JUNGLE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK_DARK_OAK, modifyTree(registries.getOrThrow(TreeFeatures.DARK_OAK)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_DARK_OAK))
                        .build()
        ));
    }

    @Override
    public String getName() {
        return "Configured Features";
    }
}
