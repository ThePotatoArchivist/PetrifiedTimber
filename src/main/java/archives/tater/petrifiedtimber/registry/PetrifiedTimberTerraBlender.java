package archives.tater.petrifiedtimber.registry;

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
        Regions.register(new Region(PetrifiedTimberWorldgen.PETRIFIED_FOREST.identifier(), RegionType.OVERWORLD, 2) {
            @Override
            public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
                var builder = new VanillaParameterOverlayBuilder();

                new ParameterPointListBuilder()
                        .continentalness(Parameter.span(-1.3F, -1.15F))
                        .build()
                        .forEach(point -> builder.add(point, PetrifiedTimberWorldgen.PETRIFIED_FOREST));

                builder.build().forEach(mapper);
            }
        });
    }
}
