package archives.tater.petrifiedtimber.mixin;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberTriggers;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@Mixin(AbstractCauldronBlock.class)
public class AbstractCauldronBlockMixin {
    @Inject(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/AbstractCauldronBlock;receiveStalactiteDrip(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/material/Fluid;)V", shift = At.Shift.AFTER)
    )
    private void cauldronTrigger(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        var center = Vec3.atCenterOf(pos);
        for (ServerPlayer serverPlayer : level.getEntitiesOfClass(ServerPlayer.class, AABB.ofSize(center, 17.0, 17.0, 17.0))) {
            PetrifiedTimberTriggers.DRIPSTONE_FILL_CAULDRON.trigger(serverPlayer, pos);
        }
    }
}
