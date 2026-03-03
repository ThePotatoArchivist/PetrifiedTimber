package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.block.ResinCauldronBlock;
import archives.tater.petrifiedtimber.fluid.FakeFluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.*;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.EmptyItemFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.FullItemFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;

import java.util.Iterator;
import java.util.Optional;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.minecraft.util.Util.makeDescriptionId;

public class PetrifiedTimberFluids {

    private static Fluid register(String path, Fluid fluid) {
        return Registry.register(BuiltInRegistries.FLUID, PetrifiedTimber.id(path), fluid);
    }

    public static final Fluid MELTED_RESIN = register("melted_resin", new FakeFluid());

    public static void init() {
        FluidVariantAttributes.register(MELTED_RESIN, new FluidVariantAttributeHandler() {
            @Override
            public Component getName(FluidVariant fluidVariant) {
                return Component.translatable(makeDescriptionId("fluid", BuiltInRegistries.FLUID.getKey(MELTED_RESIN)));
            }

            @Override
            public Optional<SoundEvent> getFillSound(FluidVariant variant) {
                return Optional.of(SoundEvents.BUCKET_FILL_LAVA);
            }

            @Override
            public Optional<SoundEvent> getEmptySound(FluidVariant variant) {
                return Optional.of(SoundEvents.BUCKET_EMPTY_LAVA);
            }
        });

        FluidStorage.combinedItemApiProvider(Items.GLASS_BOTTLE).register(context -> new EmptyItemFluidStorage(
                context,
                PetrifiedTimberItems.MELTED_RESIN_BOTTLE,
                MELTED_RESIN,
                FluidConstants.BOTTLE
        ));

        FluidStorage.ITEM.registerForItems((stack, context) -> new FullItemFluidStorage(
                context,
                Items.GLASS_BOTTLE,
                FluidVariant.of(MELTED_RESIN),
                FluidConstants.BOTTLE
        ), PetrifiedTimberItems.MELTED_RESIN_BOTTLE);

        FluidStorage.SIDED.registerForBlocks((world, pos, state, blockEntity, context) -> {
            var fillLevel = state.getValue(ResinCauldronBlock.LEVEL);
            return new Storage<>() {
                @Override
                public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
                    var amount = min(maxAmount, ResinCauldronBlock.MAX_LEVEL - fillLevel);
                    return 0;
                }

                @Override
                public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
                    var amount = max(maxAmount, fillLevel);
                    return 0;
                }

                @Override
                public Iterator<StorageView<FluidVariant>> iterator() {
                    return null;
                }
            };
        }, PetrifiedTimberBlocks.RESIN_CAULDRON);
    }
}
