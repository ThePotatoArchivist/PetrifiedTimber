package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.Climate.Parameter;

import terrablender.api.ParameterUtils.ParameterPointListBuilder;
import terrablender.api.*;

import java.util.function.Consumer;

public class PetrifiedTimberTerraBlender implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new Region(PetrifiedTimber.id("overworld_with_petrified_forest"), RegionType.OVERWORLD, 2) {
            @Override
            public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
                var builder = new VanillaParameterOverlayBuilder();

                new ParameterPointListBuilder()
                        .continentalness(Parameter.span(-1.4F, -1.2F))
                        .build().forEach(point -> builder.add(point, PetrifiedTimberWorldgen.PETRIFIED_FOREST));

                new ParameterPointListBuilder()
                        .continentalness(Parameter.span(-1.2f, -1.19F))
                        .build().forEach(point -> builder.add(point, PetrifiedTimberWorldgen.PETRIFIED_FOREST_EDGE));

                builder.build().forEach(mapper);
            }
        });
    }
}
