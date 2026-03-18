package archives.tater.petrifiedtimber.mixin;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlockTags;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberFluids;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberParticles;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.material.Fluid;

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin {
    @ModifyExpressionValue(
            method = "method_33279",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;getType()Lnet/minecraft/world/level/material/Fluid;")
    )
    private static Fluid resinFluid(Fluid original, Level level, @Local(ordinal = 1) BlockPos pos) {
        if (!level.getBlockState(pos).is(Blocks.RESIN_BLOCK)) return original;
        for (var direction : Direction.values()) {
            if (level.getBlockState(pos.relative(direction)).is(PetrifiedTimberBlockTags.MELTS_RESIN))
                return PetrifiedTimberFluids.MELTED_RESIN;
        }
        return original;
    }

    @ModifyReturnValue(
            method = "canFillCauldron",
            at = @At("RETURN")
    )
    private static boolean allowFillResin(boolean original, Fluid fluid) {
        return original || fluid == PetrifiedTimberFluids.MELTED_RESIN;
    }

    @Definition(id = "fluid", local = @Local(type = Fluid.class))
    @Definition(id = "WATER", field = "Lnet/minecraft/world/level/material/Fluids;WATER:Lnet/minecraft/world/level/material/FlowingFluid;")
    @Expression("fluid == WATER")
    @WrapOperation(
            method = "maybeTransferFluid",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static boolean skipFluidCheck(Object left, Object right, Operation<Boolean> original, @Share("resin") LocalBooleanRef resin) {
        if (original.call(left, right)) return true;
        if (left != PetrifiedTimberFluids.MELTED_RESIN) return false;
        resin.set(true);
        return true;
    }

    @Definition(id = "f", local = @Local(type = float.class, ordinal = 1))
    @Expression("f = @(0.17578125)")
    @ModifyExpressionValue(
            method = "maybeTransferFluid",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private static float resinChance(float original, @Share("resin") LocalBooleanRef resin) {
        return resin.get() ? 0.25f : original;
    }

    @ModifyReturnValue(
            method = "getDripParticle",
            at = @At("RETURN")
    )
    private static ParticleOptions resinParticle(ParticleOptions original, Level level, Fluid fluid) {
        return fluid == PetrifiedTimberFluids.MELTED_RESIN ? PetrifiedTimberParticles.DRIPPING_RESIN : original;
    }
}
