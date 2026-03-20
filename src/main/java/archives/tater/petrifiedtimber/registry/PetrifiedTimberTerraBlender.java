package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.Climate.Parameter;

import terrablender.api.ParameterUtils.ParameterPointListBuilder;
import terrablender.api.ParameterUtils.Weirdness;
import terrablender.api.*;

import java.util.Arrays;
import java.util.function.Consumer;

public class PetrifiedTimberTerraBlender implements TerraBlenderApi {

    public static final Parameter HIGH_WEIRD = Weirdness.span(Weirdness.LOW_SLICE_VARIANT_ASCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING);
    public static final Parameter LOW_WEIRD = Weirdness.span(Weirdness.HIGH_SLICE_NORMAL_ASCENDING, Weirdness.LOW_SLICE_NORMAL_DESCENDING);

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new Region(PetrifiedTimber.id("overworld_with_petrified_forest"), RegionType.OVERWORLD, 2) {

            @Override
            public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
                var builder = new VanillaParameterOverlayBuilder();

                new ParameterPointListBuilder()
                        .continentalness(Parameter.span(-1.4F, -1.2F))
                        .weirdness(
                                Weirdness.HIGH_SLICE_NORMAL_ASCENDING,
                                Weirdness.HIGH_SLICE_NORMAL_DESCENDING,
                                Weirdness.LOW_SLICE_NORMAL_DESCENDING,
                                Weirdness.LOW_SLICE_VARIANT_ASCENDING,
                                Weirdness.HIGH_SLICE_VARIANT_ASCENDING,
                                Weirdness.HIGH_SLICE_VARIANT_DESCENDING
                        )
                        .build().forEach(point -> builder.add(point, PetrifiedTimberWorldgen.PETRIFIED_FOREST));

                new ParameterPointListBuilder()
                        .continentalness(Parameter.span(-1.4F, -1.2F))
                        .weirdness(
                                Weirdness.MID_SLICE_NORMAL_ASCENDING,
                                Weirdness.PEAK_NORMAL,
                                Weirdness.MID_SLICE_NORMAL_DESCENDING,
                                Weirdness.VALLEY,
                                Weirdness.MID_SLICE_VARIANT_ASCENDING,
                                Weirdness.PEAK_VARIANT,
                                Weirdness.MID_SLICE_VARIANT_DESCENDING
                        )
                        .build().forEach(point -> builder.add(point, PetrifiedTimberWorldgen.PETRIFIED_PLAINS));

                new ParameterPointListBuilder()
                        .continentalness(Parameter.span(-1.2f, -1.19F))
                        .weirdness(Arrays.stream(Weirdness.values()).filter(w -> w != Weirdness.FULL_RANGE).toArray(Weirdness[]::new)) // help
                        .build().forEach(point -> builder.add(point, PetrifiedTimberWorldgen.PETRIFIED_RIDGE));

                builder.build().forEach(mapper);
            }
        });
    }
}
