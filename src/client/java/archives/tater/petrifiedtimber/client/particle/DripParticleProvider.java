package archives.tater.petrifiedtimber.client.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry.PendingParticleFactory;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluid;

public class DripParticleProvider {
    private DripParticleProvider() {}

    @FunctionalInterface
    private interface DripParticleFactory {
        SingleQuadParticle createParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite);
    }

    private static PendingParticleFactory<SimpleParticleType> provider(float red, float green, float blue, DripParticleFactory factory) {
        return sprite -> (particleType, level, x, y, z, xSpeed, ySpeed, zSpeed, random) -> {
            var particle = factory.createParticle(level, x, y, z, sprite.get(random));
            particle.setColor(red, green, blue);
            return particle;
        };
    }

    public static PendingParticleFactory<SimpleParticleType> dripHang(float red, float green, float blue, Fluid fluid, ParticleOptions next) {
        return provider(red, green, blue, (level, x, y, z, sprite) ->
                new DripParticle.DripHangParticle(level, x, y, z, fluid, next, sprite)
        );
    }

    public static PendingParticleFactory<SimpleParticleType> dripFalling(float red, float green, float blue, Fluid fluid, ParticleOptions next) {
        return provider(red, green, blue, (level, x, y, z, sprite) ->
                new DripParticle.DripstoneFallAndLandParticle(level, x, y, z, fluid, next, sprite)
        );
    }

    public static PendingParticleFactory<SimpleParticleType> landing(float red, float green, float blue, Fluid fluid) {
        return provider(red, green, blue, (level, x, y, z, sprite) ->
                new DripParticle.DripLandParticle(level, x, y, z, fluid, sprite)
        );
    }

    public static void registerDrippingParticles(
            float red,
            float green,
            float blue,
            Fluid fluid,
            SimpleParticleType hang,
            SimpleParticleType fall,
            SimpleParticleType land
    ) {
        ParticleFactoryRegistry.getInstance().register(hang, dripHang(red, green, blue, fluid, fall));
        ParticleFactoryRegistry.getInstance().register(fall, dripFalling(red, green, blue, fluid, land));
        ParticleFactoryRegistry.getInstance().register(land, landing(red, green, blue, fluid));
    }
}
