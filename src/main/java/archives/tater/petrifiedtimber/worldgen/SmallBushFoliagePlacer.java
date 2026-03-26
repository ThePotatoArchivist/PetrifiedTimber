package archives.tater.petrifiedtimber.worldgen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import static java.lang.Math.abs;

public class SmallBushFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<SmallBushFoliagePlacer> CODEC =
            RecordCodecBuilder.mapCodec(instance -> foliagePlacerParts(instance).apply(instance, SmallBushFoliagePlacer::new));

    public SmallBushFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return PetrifiedTimberWorldgen.SMALL_BUSH_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(WorldGenLevel level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        for (int y = offset; y > offset - foliageHeight; y--) {
            placeLeavesRow(level, foliageSetter, random, config, foliageAttachment.pos(), leafRadius, y, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 2;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        var distance = abs(localX) + abs(localZ);
        return localY == -1
                ? switch (distance) {
                    case 1 -> random.nextInt(4) == 0;
                    case 2 -> random.nextInt(2) != 0;
                    default -> true;
                } : distance != 1 || random.nextInt(4) != 0;
    }
}
