package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;
import archives.tater.petrifiedtimber.worldgen.BiomeDependentFeature;
import archives.tater.petrifiedtimber.worldgen.CornerCutFoliagePlacer;
import archives.tater.petrifiedtimber.worldgen.CuboidFoliagePlacer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;

public class ConfiguredFeatureGenerator extends FabricDynamicRegistryProvider {

    public ConfiguredFeatureGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static Holder<PlacedFeature> placedFeature(Holder<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        return Holder.direct(new PlacedFeature(feature, Arrays.asList(modifiers)));
    }

    private static ConfiguredFeature<TreeConfiguration, TreeFeature> modifyTree(Holder<ConfiguredFeature<?, ?>> original) {
        var config = (TreeConfiguration) original.value().config();
        return new ConfiguredFeature<>((TreeFeature) original.value().feature(), new TreeConfigurationBuilder(
                simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG),
                config.trunkPlacer,
                simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES),
                config.foliagePlacer,
                config.rootPlacer,
                config.minimumSize
        )
                .decorators(List.of(
                        new AttachedToLeavesDecorator(
                                0.05f,
                                2,
                                1,
                                simple(PetrifiedTimberBlocks.RED_HANGING_PETRIFIED_APPLE),
                                1,
                                List.of(Direction.UP)
                        )
                ))
                .build());
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        var biomes = registries.lookupOrThrow(Registries.BIOME);

        entries.add(PetrifiedTimberWorldgen.PETRIFIED_BIOME_TREE_FEATURE, new ConfiguredFeature<>(
                PetrifiedTimberWorldgen.BIOME_DEPENDENT,
                BiomeDependentFeature.builder(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK, modifyTree(registries.getOrThrow(TreeFeatures.OAK)))))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_SPRUCE, modifyTree(registries.getOrThrow(TreeFeatures.SPRUCE)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_SPRUCE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_BIRCH, modifyTree(registries.getOrThrow(TreeFeatures.BIRCH)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_BIRCH))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_ACACIA, modifyTree(registries.getOrThrow(TreeFeatures.ACACIA)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_ACACIA))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_JUNGLE, modifyTree(registries.getOrThrow(TreeFeatures.JUNGLE_TREE)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_JUNGLE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_CHERRY, modifyTree(registries.getOrThrow(TreeFeatures.CHERRY)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_CHERRY))
                        .build()
        ));

        entries.add(PetrifiedTimberWorldgen.MEGA_PETRIFIED_BIOME_TREE_FEATURE, new ConfiguredFeature<>(
                PetrifiedTimberWorldgen.BIOME_DEPENDENT,
                BiomeDependentFeature.builder(placedFeature(Holder.direct(new ConfiguredFeature<>(Feature.NO_OP, NoneFeatureConfiguration.INSTANCE))))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.MEGA_PETRIFIED_SPRUCE, modifyTree(registries.getOrThrow(TreeFeatures.MEGA_SPRUCE)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_SPRUCE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.MEGA_PETRIFIED_JUNGLE, modifyTree(registries.getOrThrow(TreeFeatures.MEGA_JUNGLE_TREE)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_JUNGLE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_DARK_OAK, modifyTree(registries.getOrThrow(TreeFeatures.DARK_OAK)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_DARK_OAK))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_PALE_OAK, modifyTree(registries.getOrThrow(TreeFeatures.PALE_OAK)))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_PALE_OAK))
                        .build()
        ));

        entries.add(PetrifiedTimberWorldgen.CLASSIC_PETRIFIED_OAK_FEATURE, new ConfiguredFeature<>(
                TreeFeature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(HolderSet.direct(
                        placedFeature(Holder.direct(new ConfiguredFeature<>(Feature.TREE, new TreeConfigurationBuilder(
                                simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG),
                                new StraightTrunkPlacer(5, 0, 0),
                                simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES),
                                new CuboidFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 3),
                                new TwoLayersFeatureSize(1, 0, 1)
                        ).build()))),
                        placedFeature(Holder.direct(new ConfiguredFeature<>(Feature.TREE, new TreeConfigurationBuilder(
                                simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG),
                                new StraightTrunkPlacer(5, 0, 0),
                                simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES),
                                new CornerCutFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 3, 1),
                                new TwoLayersFeatureSize(1, 0, 1)
                        ).build())))
                ))
        ));
    }

    @Override
    public String getName() {
        return "Configured Features";
    }
}
