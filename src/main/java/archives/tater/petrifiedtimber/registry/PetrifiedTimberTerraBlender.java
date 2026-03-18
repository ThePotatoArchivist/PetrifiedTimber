package archives.tater.petrifiedtimber.registry;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

import java.util.function.Consumer;

public class PetrifiedTimberTerraBlender implements TerraBlenderApi {

    public static final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1f, 1f);

    private final Climate.Parameter mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
    private final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
    private final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
    private final ResourceKey<Biome>[][] OCEANS = new ResourceKey[][]{
            {Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN},
            {Biomes.FROZEN_OCEAN, Biomes.COLD_OCEAN, Biomes.OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN}
    };
    private final Climate.Parameter[] temperatures = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.45F),
            Climate.Parameter.span(-0.45F, -0.15F),
            Climate.Parameter.span(-0.15F, 0.2F),
            Climate.Parameter.span(0.2F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new Region(PetrifiedTimberWorldgen.PETRIFIED_FOREST.identifier(), RegionType.OVERWORLD, 2) {
            @Override
            public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
                mapper.accept(new Pair<>(Climate.parameters(FULL_RANGE, FULL_RANGE, mushroomFieldsContinentalness, FULL_RANGE, FULL_RANGE, FULL_RANGE, 0f), PetrifiedTimberWorldgen.PETRIFIED_FOREST));

                for (int i = 0; i < temperatures.length; i++) {
                    var temperature = temperatures[i];
                    mapper.accept(new Pair<>(Climate.parameters(temperature, FULL_RANGE, deepOceanContinentalness, FULL_RANGE, FULL_RANGE, FULL_RANGE, 0f), OCEANS[0][i]));
                    mapper.accept(new Pair<>(Climate.parameters(temperature, FULL_RANGE, oceanContinentalness, FULL_RANGE, FULL_RANGE, FULL_RANGE, 0f), OCEANS[1][i]));
                }
            }
        });
    }
}
