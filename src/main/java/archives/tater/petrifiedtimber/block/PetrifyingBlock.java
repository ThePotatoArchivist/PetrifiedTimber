package archives.tater.petrifiedtimber.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PetrifyingBlock extends Block implements Petrifying {
    private final Block petrifiedBlock;

    public PetrifyingBlock(Block petrifiedBlock, Properties properties) {
        super(properties);
        this.petrifiedBlock = petrifiedBlock;
    }

    @Override
    public Block getPetrifiedBlock() {
        return petrifiedBlock;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        tickPetrification(state, level, pos, random);
    }
}
