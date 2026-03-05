package archives.tater.petrifiedtimber.worldgen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class CuboidFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<CuboidFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            FoliagePlacer.foliagePlacerParts(instance).and(
                    Codec.intRange(0, 16).fieldOf("height").forGetter(placer -> placer.height)
            ).apply(instance, CuboidFoliagePlacer::new)
    );
    private final int height;

    public CuboidFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return PetrifiedTimberWorldgen.CUBOID_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        for (int y = offset; y > offset - foliageHeight; y--) {
            placeLeavesRow(level, blockSetter, random, config, attachment.pos(), foliageRadius, y, attachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
