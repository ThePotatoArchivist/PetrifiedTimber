package archives.tater.petrifiedtimber.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public abstract class TransformingCropBlock extends CropBlock {
    public TransformingCropBlock(Properties properties) {
        super(properties);
    }

    protected abstract BlockState getResult();

    protected int getMaxBlockAge() {
        return super.getMaxAge();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }

    @Override
    public final int getMaxAge() {
        return getMaxBlockAge() + 1;
    }

    @Override
    public BlockState getStateForAge(int age) {
        return age > getMaxBlockAge() ? getResult() : super.getStateForAge(age);
    }
}
