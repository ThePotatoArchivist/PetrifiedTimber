package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.worldgen.BiomeDependentFeature;
import archives.tater.petrifiedtimber.worldgen.CuboidFoliagePlacer;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Optional;

public class PetrifiedTimberWorldgen {
    private static ResourceKey<ConfiguredFeature<?, ?>> configuredFeature(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, PetrifiedTimber.id(path));
    }

    private static <T extends FoliagePlacer> FoliagePlacerType<T> foliagePlacer(String path, MapCodec<T> codec) {
        return Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, PetrifiedTimber.id(path), new FoliagePlacerType<>(codec));
    }

    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_BIOME_TREE_FEATURE = configuredFeature("petrified_biome_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLASSIC_PETRIFIED_OAK_FEATURE = configuredFeature("classic_petrified_oak");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_OAK = configuredFeature("petrified_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_SPRUCE = configuredFeature("petrified_spruce");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_BIRCH = configuredFeature("petrified_birch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_ACACIA = configuredFeature("petrified_acacia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_JUNGLE = configuredFeature("petrified_jungle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_DARK_OAK = configuredFeature("petrified_dark_oak");

    public static final Feature<BiomeDependentFeature.Configuration> BIOME_DEPENDENT = Registry.register(
            BuiltInRegistries.FEATURE,
            PetrifiedTimber.id("biome_dependent"),
            new BiomeDependentFeature(BiomeDependentFeature.Configuration.CODEC)
    );

    public static final TreeGrower PETRIFIED_OAK_GROWER = new TreeGrower(
            PetrifiedTimber.sId("petrified_oak"),
//            0.05f,
            1f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(PETRIFIED_BIOME_TREE_FEATURE),
            Optional.of(CLASSIC_PETRIFIED_OAK_FEATURE),
            Optional.empty(),
            Optional.empty()
    );

    public static final FoliagePlacerType<CuboidFoliagePlacer> CUBOID_FOLIAGE_PLACER = foliagePlacer("cuboid", CuboidFoliagePlacer.CODEC);

    public static void init() {

    }
}
