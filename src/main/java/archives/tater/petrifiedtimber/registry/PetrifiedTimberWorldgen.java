package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.worldgen.BiomeDependentFeature;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.Optional;

public class PetrifiedTimberWorldgen {
    private static ResourceKey<ConfiguredFeature<?, ?>> feature(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, PetrifiedTimber.id(path));
    }

    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_OAK_FEATURE = feature("petrified_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLASSIC_PETRIFIED_OAK_FEATURE = feature("classic_petrified_oak");

    public static final Feature<BiomeDependentFeature.Configuration> BIOME_DEPENDENT = Registry.register(
            BuiltInRegistries.FEATURE,
            PetrifiedTimber.id("biome_dependent"),
            new BiomeDependentFeature(BiomeDependentFeature.Configuration.CODEC)
    );

    public static final TreeGrower PETRIFIED_OAK_GROWER = new TreeGrower(
            PetrifiedTimber.sId("petrified_oak"),
            0.05f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(PETRIFIED_OAK_FEATURE),
            Optional.of(CLASSIC_PETRIFIED_OAK_FEATURE),
            Optional.empty(),
            Optional.empty()
    );

    public static void init() {

    }
}
