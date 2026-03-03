package archives.tater.petrifiedtimber.mixin.client;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(SignRenderer.class)
public class SignRendererMixin {
	@Inject(
			method = "translateSign",
			at = @At("HEAD")
	)
	private void bigStandingSign(PoseStack poseStack, float yRot, BlockState state, CallbackInfo ci) {
        if (!state.is(PetrifiedTimberBlocks.PETRIFIED_OAK_SIGN)) return;
        poseStack.translate(0.5f, 0, 0.5f);
		poseStack.scale(1.5f, 1.5f, 1.5f);
		poseStack.translate(-0.5f, 0, -0.5f);
    }

	@Inject(
			method = "translateSign",
			at = @At("TAIL")
	)
	private void bigWallSign(PoseStack poseStack, float yRot, BlockState state, CallbackInfo ci) {
		if (!state.is(PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_SIGN)) return;
		poseStack.translate(0, 0.3125f, 0);
		poseStack.scale(1.5f, 1.5f, 1.5f);
		poseStack.translate(0, -0.3125f, 0);
	}
}