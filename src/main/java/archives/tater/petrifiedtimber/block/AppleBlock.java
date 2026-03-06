package archives.tater.petrifiedtimber.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import org.jspecify.annotations.Nullable;

public class AppleBlock extends Block implements SimpleWaterloggedBlock {

    public static final IntegerProperty APPLES = IntegerProperty.create("apples", 1, 4);
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape[] SHAPES = {
            box(4, 0, 4, 12, 7, 12),
            box(1, 0, 1, 15, 7, 16),
            box(1, 0, 1, 15, 7, 16),
            box(1, 0, 1, 15, 14, 16),
    };

    public AppleBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any()
                .setValue(APPLES, 1)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(APPLES, WATERLOGGED);
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return !useContext.isSecondaryUseActive()
                && state.getValue(APPLES) < 4
                && useContext.getItemInHand().is(this.asItem())
                || super.canBeReplaced(state, useContext);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        var currentState = context.getLevel().getBlockState(context.getClickedPos());
        return (currentState.is(this)
                ? currentState.setValue(APPLES, currentState.getValue(APPLES) + 1)
                : defaultBlockState())
                .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED))
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES[state.getValue(APPLES) - 1];
    }
}
