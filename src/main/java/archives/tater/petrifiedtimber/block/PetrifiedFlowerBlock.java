package archives.tater.petrifiedtimber.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PetrifiedFlowerBlock extends VegetationBlock {
    private static final VoxelShape SHAPE = Block.column(6.0, 0.0, 10.0);

    public PetrifiedFlowerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends PetrifiedFlowerBlock> codec() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.move(state.getOffset(pos));
    }
}
