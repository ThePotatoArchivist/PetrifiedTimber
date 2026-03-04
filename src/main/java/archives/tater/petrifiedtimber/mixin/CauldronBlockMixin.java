package archives.tater.petrifiedtimber.mixin;

import archives.tater.petrifiedtimber.block.ResinCauldronBlock;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberFluids;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

@Mixin(CauldronBlock.class)
public class CauldronBlockMixin {
    @Inject(
            method = "receiveStalactiteDrip",
            at = @At("TAIL")
    )
    private void dripResin(BlockState state, Level level, BlockPos pos, Fluid fluid, CallbackInfo ci) {
        if (fluid != PetrifiedTimberFluids.MELTED_RESIN) return;
        ResinCauldronBlock.receiveStalactiteDrip(state, level, pos);
    }
}
