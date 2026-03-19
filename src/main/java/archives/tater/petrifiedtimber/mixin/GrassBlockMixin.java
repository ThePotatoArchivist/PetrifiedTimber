package archives.tater.petrifiedtimber.mixin;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.GrassBlock;

@Mixin(GrassBlock.class)
public class GrassBlockMixin {
    @ModifyExpressionValue(
            method = "performBonemeal",
            at = @At(value = "INVOKE", target = "Ljava/util/Optional;isPresent()Z")
    )
    private boolean noGrass(boolean original, ServerLevel level, @Local(ordinal = 1) BlockPos plantPos) {
        return original && !level.getBiome(plantPos).is(PetrifiedTimberWorldgen.NO_GRASS_FROM_BONEMEAL);
    }
}
