package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.block.ResinCauldronBlock;
import archives.tater.petrifiedtimber.fluid.FakeFluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.*;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.EmptyItemFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.FullItemFluidStorage;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class PetrifiedTimberFluids {

    private static Fluid register(String path, Fluid fluid) {
        return Registry.register(BuiltInRegistries.FLUID, PetrifiedTimber.id(path), fluid);
    }

    public static final Fluid MELTED_RESIN = register("melted_resin", new FakeFluid());

    public static void init() {
        FluidVariantAttributes.register(MELTED_RESIN, new FluidVariantAttributeHandler() {
            @Override
            public Optional<SoundEvent> getFillSound(FluidVariant variant) {
                return Optional.of(SoundEvents.BUCKET_FILL_LAVA);
            }

            @Override
            public Optional<SoundEvent> getEmptySound(FluidVariant variant) {
                return Optional.of(SoundEvents.BUCKET_EMPTY_LAVA);
            }

            @Override
            public int getViscosity(FluidVariant variant, @Nullable Level world) {
                return FluidConstants.LAVA_VISCOSITY;
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

        CauldronFluidContent.registerCauldron(PetrifiedTimberBlocks.RESIN_CAULDRON, MELTED_RESIN, ResinCauldronBlock.FLUID_PER_LEVEL, ResinCauldronBlock.LEVEL);
    }
}
