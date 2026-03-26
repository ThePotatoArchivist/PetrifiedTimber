package archives.tater.petrifiedtimber.client.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry.PendingParticleProvider;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;

@FunctionalInterface
public interface DripParticleProvider {
    SingleQuadParticle createParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite);

    private static PendingParticleProvider<SimpleParticleType> provider(float red, float green, float blue, DripParticleProvider factory) {
        return sprite -> (_, level, x, y, z, _, _, _, random) -> {
            var particle = factory.createParticle(level, x, y, z, sprite.get(random));
            particle.setColor(red, green, blue);
            return particle;
        };
    }

    static PendingParticleProvider<SimpleParticleType> dripHang(float red, float green, float blue, Fluid fluid, ParticleOptions next) {
        return provider(red, green, blue, (level, x, y, z, sprite) ->
                new DripParticle.DripHangParticle(level, x, y, z, fluid, next, sprite)
        );
    }

    static PendingParticleProvider<SimpleParticleType> dripFalling(float red, float green, float blue, Fluid fluid, ParticleOptions next) {
        return provider(red, green, blue, (level, x, y, z, sprite) ->
                new DripParticle.DripstoneFallAndLandParticle(level, x, y, z, fluid, next, sprite)
        );
    }

    static PendingParticleProvider<SimpleParticleType> landing(float red, float green, float blue, Fluid fluid) {
        return provider(red, green, blue, (level, x, y, z, sprite) ->
                new DripParticle.DripLandParticle(level, x, y, z, fluid, sprite)
        );
    }

    static void registerDrippingParticles(
            float red,
            float green,
            float blue,
            Fluid fluid,
            SimpleParticleType hang,
            SimpleParticleType fall,
            SimpleParticleType land
    ) {
        ParticleProviderRegistry.getInstance().register(hang, dripHang(red, green, blue, fluid, fall));
        ParticleProviderRegistry.getInstance().register(fall, dripFalling(red, green, blue, fluid, land));
        ParticleProviderRegistry.getInstance().register(land, landing(red, green, blue, fluid));
    }
}
