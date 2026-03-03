package archives.tater.petrifiedtimber.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;

public class NoParticleLeavesBlock extends LeavesBlock {
    public NoParticleLeavesBlock(Properties properties) {
        super(0f, properties);
    }

    @Override
    public MapCodec<NoParticleLeavesBlock> codec() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) {

    }
}
