package archives.tater.petrifiedtimber.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PetrifiedLeavesBlock extends LeavesBlock {
    public PetrifiedLeavesBlock(Properties properties) {
        super(0f, properties);
    }

    @Override
    public MapCodec<PetrifiedLeavesBlock> codec() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {

    }
}
