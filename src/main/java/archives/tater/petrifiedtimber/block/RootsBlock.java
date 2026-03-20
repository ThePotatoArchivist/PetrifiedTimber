package archives.tater.petrifiedtimber.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.MangroveRootsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RootsBlock extends MangroveRootsBlock {
    public RootsBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentState, Direction direction) {
        return adjacentState.is(this) && direction.getAxis() == Direction.Axis.Y;
    }

}
