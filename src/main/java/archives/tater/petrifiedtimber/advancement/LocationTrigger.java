package archives.tater.petrifiedtimber.advancement;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberTriggers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;

import java.util.Optional;

import static net.minecraft.world.level.storage.loot.predicates.LocationCheck.checkLocation;

public class LocationTrigger extends SimpleCriterionTrigger<LocationTrigger.TriggerInstance> {
    @Override
    public Codec<TriggerInstance> codec() {
        return TriggerInstance.CODEC;
    }

    public void trigger(ServerPlayer player, BlockPos pos) {
        var serverLevel = player.level();
        var lootContext = new LootContext.Builder(new LootParams.Builder(serverLevel)
                .withParameter(LootContextParams.ORIGIN, pos.getCenter())
                .withParameter(LootContextParams.THIS_ENTITY, player)
                .withParameter(LootContextParams.BLOCK_STATE, serverLevel.getBlockState(pos))
                .withParameter(LootContextParams.TOOL, ItemStack.EMPTY) // Required
                .create(LootContextParamSets.ADVANCEMENT_LOCATION)).create(Optional.empty());
        this.trigger(player, triggerInstance -> triggerInstance.matches(lootContext));
    }

    public record TriggerInstance(
            Optional<ContextAwarePredicate> player,
            Optional<ContextAwarePredicate> location
    ) implements SimpleCriterionTrigger.SimpleInstance {
        private static final Codec<TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggerInstance::player),
                ContextAwarePredicate.CODEC.optionalFieldOf("location").forGetter(TriggerInstance::location)
        ).apply(instance, TriggerInstance::new));

        public boolean matches(LootContext context) {
            return location.isEmpty() || location.get().matches(context);
        }

        public static Criterion<TriggerInstance> dripstoneFillCauldron(Block block, StatePropertiesPredicate.Builder properties) {
            return PetrifiedTimberTriggers.DRIPSTONE_FILL_CAULDRON.createCriterion(new TriggerInstance(
                    Optional.empty(),
                    Optional.of(ContextAwarePredicate.create(
                            LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                    .setProperties(properties)
                                    .build()
                    ))
            ));
        }

        public static Criterion<TriggerInstance> dripstoneFillCauldron(BlockPredicate.Builder predicate) {
            return PetrifiedTimberTriggers.DRIPSTONE_FILL_CAULDRON.createCriterion(new TriggerInstance(
                    Optional.empty(),
                    Optional.of(ContextAwarePredicate.create(
                            checkLocation(
                                    LocationPredicate.Builder.location()
                                            .setBlock(predicate)
                            ).build()
                    ))
            ));
        }
    }
}
