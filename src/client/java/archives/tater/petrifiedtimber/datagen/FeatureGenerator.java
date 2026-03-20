package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;
import archives.tater.petrifiedtimber.worldgen.*;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;

import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
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
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.material.Fluids;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    private static Holder<PlacedFeature> placedFeature(ConfiguredFeature<?, ?> feature, PlacementModifier... modifiers) {
        return placedFeature(Holder.direct(feature), modifiers);
    }

    @SuppressWarnings("deprecation")
    private static RootPlacer modifyRootPlacer(RootPlacer rootPlacer) {
        if (!(rootPlacer instanceof MangroveRootPlacer)) return rootPlacer;
        return new MangroveRootPlacer(
                UniformInt.of(1, 3),
                simple(PetrifiedTimberBlocks.SHADOW_PETRIFIED_ROOTS),
                Optional.empty(),
                new MangroveRootPlacement(
                        HolderSet.emptyNamed(BuiltInRegistries.BLOCK, BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                        HolderSet.empty(),
                        simple(PetrifiedTimberBlocks.SHADOW_PETRIFIED_ROOTS),
                        8,
                        15,
                        0.2f
                )
        );
    }

    private static ConfiguredFeature<TreeConfiguration, TreeFeature> modifyTree(Holder<ConfiguredFeature<?, ?>> original, Block log, Block leaves) {
        var config = (TreeConfiguration) original.value().config();
        return new ConfiguredFeature<>((TreeFeature) original.value().feature(), new TreeConfigurationBuilder(
                simple(log),
                config.trunkPlacer,
                simple(leaves),
                config.foliagePlacer,
                config.rootPlacer.map(FeatureGenerator::modifyRootPlacer),
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

        var petrifiedOak = entries.add(PetrifiedTimberWorldgen.PETRIFIED_OAK, modifyTree(
                registries.getOrThrow(TreeFeatures.OAK),
                PetrifiedTimberBlocks.PETRIFIED_OAK_LOG,
                PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES
        ));

        entries.add(PetrifiedTimberWorldgen.PETRIFIED_BIOME_TREE_FEATURE, new ConfiguredFeature<>(
                PetrifiedTimberWorldgen.BIOME_DEPENDENT,
                BiomeDependentFeature.builder(placedFeature(petrifiedOak))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_SPRUCE, modifyTree(
                                registries.getOrThrow(TreeFeatures.SPRUCE),
                                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_SPRUCE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_BIRCH, modifyTree(
                                registries.getOrThrow(TreeFeatures.BIRCH),
                                PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_BIRCH))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_ACACIA, modifyTree(
                                registries.getOrThrow(TreeFeatures.ACACIA),
                                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_ACACIA))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_JUNGLE, modifyTree(
                                registries.getOrThrow(TreeFeatures.JUNGLE_TREE),
                                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_JUNGLE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_CHERRY, modifyTree(
                                registries.getOrThrow(TreeFeatures.CHERRY),
                                PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_CHERRY))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_MANGROVE, modifyTree(
                                registries.getOrThrow(TreeFeatures.MANGROVE),
                                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_MANGROVE))
                        .build()
        ));

        entries.add(PetrifiedTimberWorldgen.MEGA_PETRIFIED_BIOME_TREE_FEATURE, new ConfiguredFeature<>(
                PetrifiedTimberWorldgen.BIOME_DEPENDENT,
                BiomeDependentFeature.builder(placedFeature(Holder.direct(new ConfiguredFeature<>(PetrifiedTimberWorldgen.FAIL, NoneFeatureConfiguration.INSTANCE))))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.MEGA_PETRIFIED_SPRUCE, modifyTree(
                                registries.getOrThrow(TreeFeatures.MEGA_SPRUCE),
                                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_SPRUCE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.MEGA_PETRIFIED_JUNGLE, modifyTree(
                                registries.getOrThrow(TreeFeatures.MEGA_JUNGLE_TREE),
                                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_JUNGLE))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_DARK_OAK, modifyTree(
                                registries.getOrThrow(TreeFeatures.DARK_OAK),
                                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_DARK_OAK))
                        .entry(placedFeature(entries.add(PetrifiedTimberWorldgen.PETRIFIED_PALE_OAK, modifyTree(
                                registries.getOrThrow(TreeFeatures.PALE_OAK),
                                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG,
                                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LEAVES
                        ))), biomes.getOrThrow(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_PALE_OAK))
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
                        simple(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)),
                        new SmallBushFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).decorators(List.of(
                        new SubmergedAttachedToLeavesDecorator(
                                1f / 16,
                                4,
                                4,
                                simple(PetrifiedTimberBlocks.RED_PETRIFIED_APPLE.defaultBlockState()),
                                1,
                                List.of(Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
                        )
                )).build())),
                List.of(
                        CountPlacement.of(4),
                        spread(),
                        onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                        BlockPredicateFilter.forPredicate(allOf(
                                matchesFluids(new Vec3i(0, 1, 0), Fluids.WATER),
                                wouldSurvive(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING.defaultBlockState(), Vec3i.ZERO)
                        )),
                        onAverageOnceEvery(16)
                )
        ));

        var saplingSurvives = BlockPredicateFilter.forPredicate(wouldSurvive(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING.defaultBlockState(), Vec3i.ZERO));

        var treesPetrifiedOak = entries.add(PetrifiedTimberWorldgen.TREES_PETRIFIED_OAK, new ConfiguredFeature<>(
                Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                        List.of(
                                new WeightedPlacedFeature(placedFeature(classicPetrifiedOak, saplingSurvives), 0.05f)
                        ),
                        placedFeature(petrifiedOak, saplingSurvives)
                )
        ));

        entries.add(PetrifiedTimberWorldgen.PLACED_TREES_PETRIFIED_OAK, new PlacedFeature(
                treesPetrifiedOak,
                List.of(
                        CountPlacement.of(ConstantInt.of(6)),
                        spread(),
                        forMaxDepth(0),
                        onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                        biome()
                )
        ));

        entries.add(PetrifiedTimberWorldgen.PLACED_TREES_PETRIFIED_OAK_SPARSE, new PlacedFeature(
                treesPetrifiedOak,
                List.of(
                        CountPlacement.of(ConstantInt.of(4)),
                        spread(),
                        forMaxDepth(0),
                        onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                        biome(),
                        onAverageOnceEvery(2)
                )
        ));

        entries.add(PetrifiedTimberWorldgen.PLACED_ROCK, new PlacedFeature(
                entries.add(PetrifiedTimberWorldgen.ROCK, new ConfiguredFeature<>(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(
                        placedFeature(new ConfiguredFeature<>(Feature.FOREST_ROCK, new BlockStateConfiguration(
                                PetrifiedTimberBlocks.STACKED_ROCKS.defaultBlockState()
                        ))),
                        placedFeature(new ConfiguredFeature<>(Feature.BLOCK_PILE, new BlockPileConfiguration(
                                simple(PetrifiedTimberBlocks.STACKED_ROCKS)
                        )))
                )))),
                List.of(
                    CountPlacement.of(ConstantInt.of(12)),
                    spread(),
                    onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                    biome()
                )
        ));

        var petrifiedFlower = entries.add(PetrifiedTimberWorldgen.PETRIFIED_FLOWER, new ConfiguredFeature<>(
                Feature.FLOWER, new RandomPatchConfiguration(
                        64, 7, 3,
                        placedFeature(
                                new ConfiguredFeature<>(
                                        Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                        new WeightedStateProvider(new WeightedList.Builder<BlockState>()
                                                .add(PetrifiedTimberBlocks.PETRIFIED_RED_FLOWER.defaultBlockState(), 2)
                                                .add(PetrifiedTimberBlocks.PETRIFIED_YELLOW_FLOWER.defaultBlockState())
                                        )
                                )
                                ),
                                BlockPredicateFilter.forPredicate(ONLY_IN_AIR_PREDICATE)
                        )
                )
        ));

        entries.add(PetrifiedTimberWorldgen.PLACED_PETRIFIED_FLOWER, new PlacedFeature(
                petrifiedFlower,
                List.of(
                        onAverageOnceEvery(16),
                        spread(),
                        onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        biome()
                )
        ));

        entries.add(PetrifiedTimberWorldgen.PLACED_PETRIFIED_FLOWER_SPARSE, new PlacedFeature(
                petrifiedFlower,
                List.of(
                        onAverageOnceEvery(32),
                        spread(),
                        onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                        biome()
                )
        ));
    }

    private static PlacedFeature emptyFeature() {
        return new PlacedFeature(Holder.direct(new ConfiguredFeature<>(Feature.NO_OP, NoneFeatureConfiguration.INSTANCE)), List.of(biome()));
    }

    // Features are bootstrapped as empty because I need access to vanilla features
    public static void bootstrapFeatures(BootstrapContext<PlacedFeature> context) {
        context.register(PetrifiedTimberWorldgen.PLACED_TREES_PETRIFIED_OAK, emptyFeature());
        context.register(PetrifiedTimberWorldgen.PLACED_TREES_PETRIFIED_OAK_SPARSE, emptyFeature());
        context.register(PetrifiedTimberWorldgen.PLACED_ROCK, emptyFeature());
        context.register(PetrifiedTimberWorldgen.PLACED_PETRIFIED_FLOWER, emptyFeature());
        context.register(PetrifiedTimberWorldgen.PLACED_PETRIFIED_FLOWER_SPARSE, emptyFeature());
    }

    @Override
    public String getName() {
        return "Features";
    }
}
