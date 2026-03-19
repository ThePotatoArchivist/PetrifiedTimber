package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.worldgen.*;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Optional;

public class PetrifiedTimberWorldgen {
    private static <F extends Feature<FC>, FC extends FeatureConfiguration> F featureType(String path, F type) {
        return Registry.register(BuiltInRegistries.FEATURE, PetrifiedTimber.id(path), type);
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> configuredFeature(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, PetrifiedTimber.id(path));
    }

    private static ResourceKey<PlacedFeature> placedFeature(ResourceKey<ConfiguredFeature<?, ?>> key) {
        return ResourceKey.create(Registries.PLACED_FEATURE, key.identifier());
    }

    private static <T extends FoliagePlacer> FoliagePlacerType<T> foliagePlacer(String path, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, PetrifiedTimber.id(path), new FoliagePlacerType<>(codec));
    }

    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_BIOME_TREE_FEATURE = configuredFeature("petrified_biome_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_PETRIFIED_BIOME_TREE_FEATURE = configuredFeature("mega_petrified_biome_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLASSIC_PETRIFIED_OAK_FEATURE = configuredFeature("classic_petrified_oak");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_OAK = configuredFeature("petrified_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_SPRUCE = configuredFeature("petrified_spruce");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_BIRCH = configuredFeature("petrified_birch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_ACACIA = configuredFeature("petrified_acacia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_JUNGLE = configuredFeature("petrified_jungle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_CHERRY = configuredFeature("petrified_cherry");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_PETRIFIED_SPRUCE = configuredFeature("mega_petrified_spruce");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_PETRIFIED_JUNGLE = configuredFeature("mega_petrified_jungle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_DARK_OAK = configuredFeature("petrified_dark_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_PALE_OAK = configuredFeature("petrified_pale_oak");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SWAMP_BUSH = configuredFeature("swamp_bush");
    public static final ResourceKey<PlacedFeature> PLACED_SWAMP_BUSH = placedFeature(SWAMP_BUSH);

    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_PETRIFIED_OAK = configuredFeature("trees_petrified_oak");
    public static final ResourceKey<PlacedFeature> PLACED_TREES_PETRIFIED_OAK = placedFeature(TREES_PETRIFIED_OAK);
    public static final ResourceKey<PlacedFeature> PLACED_TREES_PETRIFIED_OAK_SPARSE = ResourceKey.create(Registries.PLACED_FEATURE, PetrifiedTimber.id("trees_petrified_oak_sparse"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK = configuredFeature("rock");
    public static final ResourceKey<PlacedFeature> PLACED_ROCK = placedFeature(ROCK);

    public static final ResourceKey<Biome> PETRIFIED_FOREST = ResourceKey.create(Registries.BIOME, PetrifiedTimber.id("petrified_forest"));
    public static final ResourceKey<Biome> PETRIFIED_FOREST_EDGE = ResourceKey.create(Registries.BIOME, PetrifiedTimber.id("petrified_forest_edge"));

    public static final Feature<BiomeDependentFeature.Configuration> BIOME_DEPENDENT = featureType(
            "biome_dependent",
            new BiomeDependentFeature(BiomeDependentFeature.Configuration.CODEC)
    );
    
    public static final Feature<NoneFeatureConfiguration> FAIL = featureType(
            "fail",
            new FailFeature(NoneFeatureConfiguration.CODEC)
    );

    public static final TreeGrower PETRIFIED_OAK_GROWER = new TreeGrower(
            PetrifiedTimber.sId("petrified_oak"),
            0.05f,
            Optional.of(MEGA_PETRIFIED_BIOME_TREE_FEATURE),
            Optional.empty(),
            Optional.of(PETRIFIED_BIOME_TREE_FEATURE),
            Optional.of(CLASSIC_PETRIFIED_OAK_FEATURE),
            Optional.empty(),
            Optional.empty()
    );

    public static final TagKey<Biome> HAS_MEGA_PETRIFIED_OAK_TREE = TagKey.create(Registries.BIOME, PetrifiedTimber.id("has_mega_petrified_oak_tree"));
    public static final TagKey<Biome> HAS_SWAMP_BUSH = TagKey.create(Registries.BIOME, PetrifiedTimber.id("has_swamp_bush"));

    public static final FoliagePlacerType<CuboidFoliagePlacer> CUBOID_FOLIAGE_PLACER = foliagePlacer("cuboid", CuboidFoliagePlacer.CODEC);
    public static final FoliagePlacerType<CornerCutFoliagePlacer> CORNER_CUT_FOLIAGE_PLACER = foliagePlacer("corner_cut", CornerCutFoliagePlacer.CODEC);
    public static final FoliagePlacerType<SmallBushFoliagePlacer> SMALL_BUSH_FOLIAGE_PLACER = foliagePlacer("small_bush", SmallBushFoliagePlacer.CODEC);
    public static final TreeDecoratorType<SubmergedAttachedToLeavesDecorator> SUBMERGED_ATTACHED_TO_LEAVES_DECORATOR = Registry.register(
            BuiltInRegistries.TREE_DECORATOR_TYPE,
            PetrifiedTimber.id("submerged_attached_to_leaves"),
            new TreeDecoratorType<>(SubmergedAttachedToLeavesDecorator.CODEC)
    );

    public static void init() {
        BiomeModifications.addFeature(
                context -> context.hasTag(HAS_SWAMP_BUSH),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                PLACED_SWAMP_BUSH
        );
    }
}
