package archives.tater.petrifiedtimber.worldgen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class CornerCutFoliagePlacer extends CuboidFoliagePlacer {

    public static final MapCodec<CornerCutFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            cuboidFoliagePlacerParts(instance).and(
                    Codec.intRange(0, 16).fieldOf("cut").forGetter(placer -> placer.cut)
            ).apply(instance, CornerCutFoliagePlacer::new)
    );

    private final int cut;

    public CornerCutFoliagePlacer(IntProvider radius, IntProvider offset, int height, int cut) {
        super(radius, offset, height);
        this.cut = cut;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return PetrifiedTimberWorldgen.CORNER_CUT_FOLIAGE_PLACER;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return max(-localY, range - min(localX, localZ)) < cut;
    }
}
