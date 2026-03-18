package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;
import archives.tater.petrifiedtimber.worldgen.BiomeDependentFeature;
import archives.tater.petrifiedtimber.worldgen.CornerCutFoliagePlacer;
import archives.tater.petrifiedtimber.worldgen.CuboidFoliagePlacer;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;

import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration.TreeConfigurationBuilder;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.*;
import static net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider.simple;
import static net.minecraft.world.level.levelgen.placement.BiomeFilter.biome;
import static net.minecraft.world.level.levelgen.placement.HeightmapPlacement.onHeightmap;
import static net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread;
import static net.minecraft.world.level.levelgen.placement.RarityFilter.onAverageOnceEvery;
import static net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter.forMaxDepth;

public class FeatureGenerator extends FabricDynamicRegistryProvider {

    public FeatureGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static Holder<PlacedFeature> placedFeature(Holder<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        return Holder.direct(new PlacedFeature(feature, Arrays.asList(modifiers)));
    }

    private static ConfiguredFeature<TreeConfiguration, TreeFeature> modifyTree(Holder<ConfiguredFeature<?, ?>> original, Block log) {
        var config = (TreeConfiguration) original.value().config();
        return new ConfiguredFeature<>((TreeFeature) original.value().feature(), new TreeConfigurationBuilder(
                simple(log),
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
                                List.of(Direction.DOWN)
                        )
                ))
                .build());
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        var biomes = registries.lookupOrThrow(Registries.BIOME);

        var petrifiedOak = entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK, modifyTree(registries.getOrThrow(TreeFeatures.OAK), PetrifiedTimberBlocks.PETRIFIED_OAK_LOG));

        entries.add(PetrifiedTimberWorldgen.PETRIFIED_BIOME_TREE_FEATURE, new ConfiguredFeature<>(
                PetrifiedTimberWorldgen.BIOME_DEPENDENT,
                BiomeDependentFeature.builder(placedFeature(petrifiedOak))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_SPRUCE, modifyTree(registries.getOrThrow(TreeFeatures.SPRUCE), PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_SPRUCE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_BIRCH, modifyTree(registries.getOrThrow(TreeFeatures.BIRCH), PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_BIRCH))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_ACACIA, modifyTree(registries.getOrThrow(TreeFeatures.ACACIA), PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_ACACIA))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_JUNGLE, modifyTree(registries.getOrThrow(TreeFeatures.JUNGLE_TREE), PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_JUNGLE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_CHERRY, modifyTree(registries.getOrThrow(TreeFeatures.CHERRY), PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LOG))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_CHERRY))
                        .build()
        ));

        entries.add(PetrifiedTimberWorldgen.MEGA_PETRIFIED_BIOME_TREE_FEATURE, new ConfiguredFeature<>(
                PetrifiedTimberWorldgen.BIOME_DEPENDENT,
                BiomeDependentFeature.builder(placedFeature(Holder.direct(new ConfiguredFeature<>(PetrifiedTimberWorldgen.FAIL, NoneFeatureConfiguration.INSTANCE))))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.MEGA_PETRIFIED_SPRUCE, modifyTree(registries.getOrThrow(TreeFeatures.MEGA_SPRUCE), PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_SPRUCE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.MEGA_PETRIFIED_JUNGLE, modifyTree(registries.getOrThrow(TreeFeatures.MEGA_JUNGLE_TREE), PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_JUNGLE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_DARK_OAK, modifyTree(registries.getOrThrow(TreeFeatures.DARK_OAK), PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_DARK_OAK))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_PALE_OAK, modifyTree(registries.getOrThrow(TreeFeatures.PALE_OAK), PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_PALE_OAK))
                        .build()
        ));

        var classicPetrifiedOak = entries.add(PetrifiedTimberWorldgen.CLASSIC_PETRIFIED_OAK_FEATURE, new ConfiguredFeature<>(
                Feature.SIMPLE_RANDOM_SELECTOR,
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

        entries.add(PetrifiedTimberWorldgen.PLACED_SWAMP_BUSH, new PlacedFeature(
                entries.add(PetrifiedTimberWorldgen.SWAMP_BUSH, new ConfiguredFeature<>(Feature.TREE, new TreeConfigurationBuilder(
                        simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG),
                        new StraightTrunkPlacer(1, 0, 0),
                        new WeightedStateProvider(new WeightedList.Builder<BlockState>()
                                .add(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true), 1)
                                .add(Blocks.WATER.defaultBlockState(), 3)
                        ),
                        new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 1),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).build())),
                List.of(
                        spread(),
                        onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                        BlockPredicateFilter.forPredicate(allOf(
                                matchesFluids(new Vec3i(0, 1, 0), Fluids.WATER),
                                wouldSurvive(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING.defaultBlockState(), Vec3i.ZERO)
                        )),
                        onAverageOnceEvery(4)
                )
        ));

        var saplingSurvives = BlockPredicateFilter.forPredicate(wouldSurvive(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING.defaultBlockState(), Vec3i.ZERO));

        entries.add(PetrifiedTimberWorldgen.PLACED_TREES_PETRIFIED_OAK, new PlacedFeature(
                entries.add(PetrifiedTimberWorldgen.TREES_PETRIFIED_OAK, new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                        List.of(
                                new WeightedPlacedFeature(placedFeature(classicPetrifiedOak, saplingSurvives), 0.05f)
                        ),
                        placedFeature(petrifiedOak, saplingSurvives)
                ))),
                List.of(
                        CountPlacement.of(ConstantInt.of(6)),
                        spread(),
                        forMaxDepth(0),
                        onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                        biome()
                )
        ));

        entries.add(PetrifiedTimberWorldgen.PLACED_ROCK, new PlacedFeature(
                entries.add(PetrifiedTimberWorldgen.ROCK, new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(
                        Blocks.STONE.defaultBlockState()
                ))),
                List.of(
                    CountPlacement.of(ConstantInt.of(12)),
                    spread(),
                    onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                    biome()
                )
        ));
    }

    @Override
    public String getName() {
        return "Features";
    }
}
