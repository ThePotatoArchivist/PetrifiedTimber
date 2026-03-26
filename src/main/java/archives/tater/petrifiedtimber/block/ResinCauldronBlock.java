package archives.tater.petrifiedtimber.block;

import archives.tater.petrifiedtimber.mixin.CauldronInteractionDispatcherAccessor;
import archives.tater.petrifiedtimber.mixin.PointedDripstoneBlockInvoker;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberFluids;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberSounds;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Map;

public class ResinCauldronBlock extends AbstractCauldronBlock {
    public static final int LEVELS_PER_BOTTLE = 5;
    public static final int MAX_LEVEL = 3 * LEVELS_PER_BOTTLE;
    public static final long FLUID_PER_LEVEL = FluidConstants.BLOCK / MAX_LEVEL;
    public static final double HEIGHT_PER_LEVEL = 11.0 / MAX_LEVEL;
    private static final VoxelShape[] FILLED_SHAPES = Block.boxes(MAX_LEVEL - 1, level ->
            Shapes.or(AbstractCauldronBlock.SHAPE, Block.column(12.0, 4.0, getPixelContentHeight(level + 1)))
    );
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", 1, MAX_LEVEL);
    public static final CauldronInteraction.Dispatcher INTERACTION = new CauldronInteraction.Dispatcher();

    public static final Map<Item, Item> RESIN_COATING = new HashMap<>();

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

    public static void bootstrap() {
        RESIN_COATING.put(Items.OAK_LOG, PetrifiedTimberItems.RESIN_COVERED_OAK_LOG);
        RESIN_COATING.put(Items.OAK_WOOD, PetrifiedTimberItems.RESIN_COVERED_OAK_WOOD);
        RESIN_COATING.put(Items.STRIPPED_OAK_LOG, PetrifiedTimberItems.RESIN_COVERED_STRIPPED_OAK_LOG);
        RESIN_COATING.put(Items.STRIPPED_OAK_WOOD, PetrifiedTimberItems.RESIN_COVERED_STRIPPED_OAK_WOOD);
        RESIN_COATING.put(Items.OAK_PLANKS, PetrifiedTimberItems.RESIN_COVERED_OAK_PLANKS);

        var resin = (CauldronInteractionDispatcherAccessor) INTERACTION;
        RESIN_COATING.forEach((input, result) ->
                resin.invokePut(input, fillItemInteraction(result, -1, PetrifiedTimberSounds.DIP_RESIN, GameEvent.FLUID_PICKUP))
        );
        resin.invokePut(Items.GLASS_BOTTLE, fillItemInteraction(PetrifiedTimberItems.MELTED_RESIN_BOTTLE, -LEVELS_PER_BOTTLE, SoundEvents.BOTTLE_FILL, GameEvent.FLUID_PICKUP));
        resin.invokePut(PetrifiedTimberItems.MELTED_RESIN_BOTTLE, fillItemInteraction(Items.GLASS_BOTTLE, LEVELS_PER_BOTTLE, SoundEvents.BOTTLE_EMPTY, GameEvent.FLUID_PLACE));
        var empty = (CauldronInteractionDispatcherAccessor) CauldronInteractions.EMPTY;
        empty.invokePut(PetrifiedTimberItems.MELTED_RESIN_BOTTLE, fillItemInteraction(Items.GLASS_BOTTLE, LEVELS_PER_BOTTLE, SoundEvents.BOTTLE_EMPTY, GameEvent.FLUID_PLACE));
    }

    private static int getFillLevel(BlockState state) {
        return state.getValueOrElse(LEVEL, 0);
    }

    /**
     * @see net.minecraft.world.level.block.LayeredCauldronBlock#lowerFillLevel
     */
    public static BlockState setFillLevel(BlockState state, Level level, BlockPos pos, int fillLevel) {
        var blockState = fillLevel == 0
                ? Blocks.CAULDRON.defaultBlockState()
                : (state.hasProperty(LEVEL)
                        ? state
                        : PetrifiedTimberBlocks.RESIN_CAULDRON.defaultBlockState()
                ).setValue(LEVEL, fillLevel);
        level.setBlockAndUpdate(pos, blockState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockState));
        return blockState;
    }

    public ResinCauldronBlock(CauldronInteraction.Dispatcher interactions, Properties properties) {
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

    @Override
    protected boolean canReceiveStalactiteDrip(Fluid fluid) {
        return fluid == PetrifiedTimberFluids.MELTED_RESIN;
    }

    public static void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos) {
        var dripstonePos = PointedDripstoneBlock.findStalactiteTipAboveCauldron(level, pos);
        if (dripstonePos == null) return;

        var fluidInfo = PointedDripstoneBlockInvoker.invokeGetFluidAboveStalactite(level, dripstonePos, level.getBlockState(dripstonePos)).orElse(null);
        if (fluidInfo == null) return;

        if (!fluidInfo.sourceState().is(Blocks.RESIN_BLOCK)) return;

        level.destroyBlock(fluidInfo.pos(), false);
        var newState = setFillLevel(state, level, pos, MAX_LEVEL);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
        level.playSound(null, pos, PetrifiedTimberSounds.RESIN_DRIP, SoundSource.BLOCKS, 2f, 0.1f * level.getRandom().nextFloat() + 0.9f);
    }

    @Override
    protected void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos, Fluid fluid) {
        if (state.getValue(LEVEL) < MAX_LEVEL)
            receiveStalactiteDrip(state, level, pos);
    }

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
        if (entity instanceof LivingEntity)
            entity.makeStuckInBlock(state, new Vec3(0.4F, 0.5, 0.4F));

        if (!(entity instanceof ItemEntity itemEntity)) return;

        var stack = itemEntity.getItem();

        var resultItem = RESIN_COATING.get(stack.getItem());
        if (resultItem == null) return;

        int fillLevel = state.getValue(LEVEL);
        var count = stack.getCount();

        if (count > fillLevel) {
            var newItem = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), stack.split(fillLevel).transmuteCopy(resultItem), 0, 0, 0);
            newItem.setDefaultPickUpDelay();
            level.addFreshEntity(newItem);
            setFillLevel(state, level, pos, 0);
        } else {
            itemEntity.setItem(stack.transmuteCopy(resultItem));
            setFillLevel(state, level, pos, fillLevel - count);
        }
        level.playSound(null, pos, PetrifiedTimberSounds.DIP_RESIN, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return state.getValue(LEVEL);
    }
}
