package archives.tater.petrifiedtimber.block;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PetrifiedSaplingCropBlock extends TransformingCropBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_2;

    public PetrifiedSaplingCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected int getMaxBlockAge() {
        return 2;
    }

    @Override
    protected BlockState getResult() {
        return PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING.defaultBlockState()
                .setValue(CropSaplingBlock.CROP, true);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return false;
    }
}
