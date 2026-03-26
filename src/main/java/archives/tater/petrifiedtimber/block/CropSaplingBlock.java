package archives.tater.petrifiedtimber.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import org.jspecify.annotations.Nullable;

public class CropSaplingBlock extends SaplingBlock {

    public static final BooleanProperty CROP = BooleanProperty.create("crop");

    public CropSaplingBlock(TreeGrower treeGrower, Properties properties) {
        super(treeGrower, properties);
        registerDefaultState(defaultBlockState().setValue(CROP, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CROP);
    }

    private static boolean isCropPlantable(BlockState state) {
        return state.getBlock() instanceof FarmlandBlock;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        var state = super.getStateForPlacement(context);
        if (state == null) return null;
        return state.setValue(CROP, isCropPlantable(context.getLevel().getBlockState(context.getClickedPos().below())));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        var newState = super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
        if (!newState.is(this)) return newState;
        return newState.setValue(CROP, isCropPlantable(level.getBlockState(pos.below())));
    }
}
