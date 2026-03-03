package archives.tater.petrifiedtimber.block;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlockTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface Petrifying {
    float PETRIFY_CHANCE = 0.01f;
    float ACCELERATED_PETRIFY_CHANCE = 0.05f;

    Block getPetrifiedBlock();

    default BlockState getPetrifiedState(BlockState state) {
        return getPetrifiedBlock().withPropertiesOf(state);
    }

    default void tickPetrification(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        for (var direction : Direction.values())
            if (!level.getBlockState(pos.relative(direction)).is(PetrifiedTimberBlockTags.PETRIFICATION_COVER)) return;

        var accelerated = level.getBlockState(pos.below()).is(PetrifiedTimberBlockTags.PETRIFICATION_CATALYST);

        if (!(random.nextFloat() < (accelerated ? ACCELERATED_PETRIFY_CHANCE : PETRIFY_CHANCE))) return;

        level.setBlockAndUpdate(pos, getPetrifiedState(state));
        level.playSound(null, pos, SoundEvents.COMPOSTER_READY, SoundSource.BLOCKS, 1f, 1f); // TODO custom sound event
    }
}
