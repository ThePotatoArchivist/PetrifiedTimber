package archives.tater.petrifiedtimber.mixin.client;

import archives.tater.petrifiedtimber.client.render.BigSignRendering;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.objectweb.asm.Opcodes;

import net.minecraft.client.renderer.blockentity.StandingSignRenderer;
import net.minecraft.client.renderer.blockentity.WallAndGroundTransformations;
import net.minecraft.client.renderer.blockentity.state.SignRenderState;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(StandingSignRenderer.class)
public class StandingSignRendererMixin {
	@ModifyExpressionValue(
			method = "extractRenderState(Lnet/minecraft/world/level/block/entity/SignBlockEntity;Lnet/minecraft/client/renderer/blockentity/state/StandingSignRenderState;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V",
			at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/blockentity/StandingSignRenderer;TRANSFORMATIONS:Lnet/minecraft/client/renderer/blockentity/WallAndGroundTransformations;", opcode = Opcodes.GETSTATIC)
	)
	private WallAndGroundTransformations<SignRenderState.SignTransformations> petrifiedSignTransformations(WallAndGroundTransformations<SignRenderState.SignTransformations> original, @Local(name = "blockState") BlockState state) {
		return state.is(PetrifiedTimberBlocks.PETRIFIED_OAK_SIGN) || state.is(PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_SIGN)
				? BigSignRendering.PETRIFIED_SIGN_TRANSFORMATIONS
				: original;
	}
}