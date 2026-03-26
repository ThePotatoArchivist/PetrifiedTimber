package archives.tater.petrifiedtimber.block;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlockTags;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class PetrifiedSaplingBlock extends CropSaplingBlock {
    public PetrifiedSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (canGrowNaturally(level, pos))
            super.randomTick(state, level, pos, random);
    }

    public static boolean canGrowNaturally(Level level, BlockPos pos) {
        var below = level.getBlockState(pos.below());
        return below.is(PetrifiedTimberBlockTags.PETRIFIED_SAPLING_GROWS_ON) && below.getValueOrElse(FarmlandBlock.MOISTURE, 1) > 0;
    }
}
