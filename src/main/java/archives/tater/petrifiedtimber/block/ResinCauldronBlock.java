package archives.tater.petrifiedtimber.block;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ResinCauldronBlock extends AbstractCauldronBlock {
    public static final int LEVELS_PER_BOTTLE = 9;
    public static final int MAX_LEVEL = 3 * LEVELS_PER_BOTTLE;
    public static final double HEIGHT_PER_LEVEL = 11.0 / MAX_LEVEL;
    private static final VoxelShape[] FILLED_SHAPES = Block.boxes(MAX_LEVEL - 1, level ->
            Shapes.or(AbstractCauldronBlock.SHAPE, Block.column(12.0, 4.0, getPixelContentHeight(level + 1)))
    );
    public static final Property<Integer> LEVEL = IntegerProperty.create("level", 1, MAX_LEVEL);
    public static final CauldronInteraction.InteractionMap INTERACTION = CauldronInteraction.newInteractionMap(PetrifiedTimber.MOD_ID + ":resin");

    public static CauldronInteraction fillItemInteraction(Item result, int amount, SoundEvent soundEvent, Holder.Reference<GameEvent> event) {
        return (blockState, level, blockPos, player, interactionHand, itemStack) -> {
            var fillLevel = getFillLevel(blockState) + amount;
            if (fillLevel < 0 || fillLevel > MAX_LEVEL) return InteractionResult.TRY_WITH_EMPTY_HAND;

            if (level.isClientSide()) return InteractionResult.SUCCESS;

            var item = itemStack.getItem();
            player.setItemInHand(interactionHand, ItemUtils.createFilledResult(itemStack, player, itemStack.transmuteCopy(result, 1)));
            player.awardStat(Stats.USE_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(item));
            setFillLevel(blockState, level, blockPos, fillLevel);
            level.playSound(null, blockPos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, event, blockPos);

            return InteractionResult.SUCCESS;
        };
    }

    public static CauldronInteraction resinCoverInteraction(Item result) {
        return fillItemInteraction(result, -1, SoundEvents.HONEY_BLOCK_PLACE, GameEvent.FLUID_PICKUP);
    }

    public static void bootstrap() {
        var resin = INTERACTION.map();
        resin.put(Items.OAK_LOG, resinCoverInteraction(PetrifiedTimberItems.RESIN_COVERED_OAK_LOG));
        resin.put(Items.OAK_WOOD, resinCoverInteraction(PetrifiedTimberItems.RESIN_COVERED_OAK_WOOD));
        resin.put(Items.STRIPPED_OAK_LOG, resinCoverInteraction(PetrifiedTimberItems.RESIN_COVERED_STRIPPED_OAK_LOG));
        resin.put(Items.STRIPPED_OAK_WOOD, resinCoverInteraction(PetrifiedTimberItems.RESIN_COVERED_STRIPPED_OAK_WOOD));
        resin.put(Items.OAK_PLANKS, resinCoverInteraction(PetrifiedTimberItems.RESIN_COVERED_OAK_PLANKS));
        resin.put(Items.GLASS_BOTTLE, fillItemInteraction(PetrifiedTimberItems.MELTED_RESIN_BOTTLE, -LEVELS_PER_BOTTLE, SoundEvents.BOTTLE_FILL, GameEvent.FLUID_PICKUP));
        resin.put(PetrifiedTimberItems.MELTED_RESIN_BOTTLE, fillItemInteraction(Items.GLASS_BOTTLE, LEVELS_PER_BOTTLE, SoundEvents.BOTTLE_EMPTY, GameEvent.FLUID_PLACE));
        var empty = CauldronInteraction.EMPTY.map();
        empty.put(PetrifiedTimberItems.MELTED_RESIN_BOTTLE, fillItemInteraction(Items.GLASS_BOTTLE, LEVELS_PER_BOTTLE, SoundEvents.BOTTLE_EMPTY, GameEvent.FLUID_PLACE));
    }

    private static int getFillLevel(BlockState state) {
        return state.hasProperty(LEVEL) ? state.getValue(LEVEL) : 0;
    }

    /**
     * @see net.minecraft.world.level.block.LayeredCauldronBlock#lowerFillLevel
     */
    public static void setFillLevel(BlockState state, Level level, BlockPos pos, int fillLevel) {
        var blockState = fillLevel == 0
                ? Blocks.CAULDRON.defaultBlockState()
                : (state.hasProperty(LEVEL)
                        ? state
                        : PetrifiedTimberBlocks.RESIN_CAULDRON.defaultBlockState()
                ).setValue(LEVEL, fillLevel);
        level.setBlockAndUpdate(pos, blockState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));
    }

    public ResinCauldronBlock(CauldronInteraction.InteractionMap interactions, Properties properties) {
        super(properties, interactions);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.getValue(LEVEL) >= MAX_LEVEL;
    }

    // TODO
//    @Override
//    protected boolean canReceiveStalactiteDrip(Fluid fluid) {
//        return super.canReceiveStalactiteDrip(fluid);
//    }

//    @Override
//    protected void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos, Fluid fluid) {
//        super.receiveStalactiteDrip(state, level, pos, fluid);
//    }

    @Override
    protected double getContentHeight(BlockState state) {
        return getPixelContentHeight(state.getValue(LEVEL)) / 16.0;
    }

    public static double getPixelContentHeight(int level) {
        return (4 + HEIGHT_PER_LEVEL * level);
    }

    @Override
    protected VoxelShape getEntityInsideCollisionShape(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
        return FILLED_SHAPES[state.getValue(LEVEL) - 1];
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier, boolean intersects) {
        super.entityInside(state, level, pos, entity, applier, intersects); // TODO stack waxing
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return 0; // TODO
    }
}
