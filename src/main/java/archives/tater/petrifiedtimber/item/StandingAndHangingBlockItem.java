package archives.tater.petrifiedtimber.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

import org.jspecify.annotations.Nullable;

import java.util.Map;

public class StandingAndHangingBlockItem extends BlockItem {
    protected final Block hangingBlock;

    public StandingAndHangingBlockItem(Block block, Block hangingBlock, Properties properties) {
        super(block, properties);
        this.hangingBlock = hangingBlock;
    }

    protected boolean canPlace(LevelReader level, BlockState state, BlockPos pos) {
        return state.canSurvive(level, pos);
    }

    @Override
    protected @Nullable BlockState getPlacementState(BlockPlaceContext context) {
        var level = context.getLevel();
        var pos = context.getClickedPos();

        var state = context.getClickedFace() == Direction.DOWN
                ? hangingBlock.getStateForPlacement(context)
                : getBlock().getStateForPlacement(context);

        return state != null && canPlace(level, state, pos) && level.isUnobstructed(state, pos, CollisionContext.empty()) ? state : null;
    }

    @Override
    public void registerBlocks(Map<Block, Item> blockToItemMap, Item item) {
        super.registerBlocks(blockToItemMap, item);
        blockToItemMap.put(hangingBlock, item);
    }
}
