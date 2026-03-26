package archives.tater.petrifiedtimber.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.renderer.blockentity.StandingSignRenderer;

import org.joml.Vector3fc;

@Mixin(StandingSignRenderer.class)
public interface StandingSignRendererAccessor {
    @Accessor
    static Vector3fc getTEXT_OFFSET() {
        throw new UnsupportedOperationException();
    }
}
