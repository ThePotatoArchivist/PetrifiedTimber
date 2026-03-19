package archives.tater.petrifiedtimber.worldgen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.HashSet;
import java.util.List;

import static net.minecraft.util.Util.getRandom;
import static net.minecraft.util.Util.shuffledCopy;

public class SubmergedAttachedToLeavesDecorator extends AttachedToLeavesDecorator {

    public static final MapCodec<SubmergedAttachedToLeavesDecorator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter(decorator -> decorator.probability),
            Codec.intRange(0, 16).fieldOf("exclusion_radius_xz").forGetter(decorator -> decorator.exclusionRadiusXZ),
            Codec.intRange(0, 16).fieldOf("exclusion_radius_y").forGetter(decorator -> decorator.exclusionRadiusY),
            BlockStateProvider.CODEC.fieldOf("block_provider").forGetter(decorator -> decorator.blockProvider),
            Codec.intRange(1, 16).fieldOf("required_empty_blocks").forGetter(decorator -> decorator.requiredEmptyBlocks),
            ExtraCodecs.nonEmptyList(Direction.CODEC.listOf()).fieldOf("directions").forGetter(decorator -> decorator.directions)
    ).apply(instance, SubmergedAttachedToLeavesDecorator::new));

    public SubmergedAttachedToLeavesDecorator(float probability, int exclusionRadiusXZ, int exclusionRadiusY, BlockStateProvider blockProvider, int requiredEmptyBlocks, List<Direction> directions) {
        super(probability, exclusionRadiusXZ, exclusionRadiusY, blockProvider, requiredEmptyBlocks, directions);
    }

    @Override
    public void place(TreeDecorator.Context context) {
        var excluded = new HashSet<BlockPos>();
        var random = context.random();

        for (var leafPos : shuffledCopy(context.leaves(), random)) {
            var direction = getRandom(directions, random);
            var attachmentPos = leafPos.relative(direction);
            if (!excluded.contains(attachmentPos) && random.nextFloat() < probability && hasRequiredEmptyBlocks(context, leafPos, direction)) {

                for (var excludePos : BlockPos.betweenClosed(
                        attachmentPos.offset(-exclusionRadiusXZ, -exclusionRadiusY, -exclusionRadiusXZ),
                        attachmentPos.offset(exclusionRadiusXZ, exclusionRadiusY, exclusionRadiusXZ)
                )) excluded.add(excludePos.immutable());

                context.setBlock(attachmentPos, blockProvider.getState(random, attachmentPos));
            }
        }
    }

    private boolean hasRequiredEmptyBlocks(TreeDecorator.Context context, BlockPos pos, Direction direction) {
        for (int i = 1; i <= requiredEmptyBlocks; i++) {
            var checkPos = pos.relative(direction, i);
            if (!context.isAir(checkPos) && !context.checkBlock(checkPos, state -> state.is(Blocks.WATER))) return false;
        }

        return true;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return PetrifiedTimberWorldgen.SUBMERGED_ATTACHED_TO_LEAVES_DECORATOR;
    }
}
