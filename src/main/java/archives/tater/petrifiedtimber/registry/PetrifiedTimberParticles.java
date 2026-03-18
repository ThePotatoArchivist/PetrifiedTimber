package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class PetrifiedTimberParticles {
    private static <T extends ParticleType<?>> T register(String path, T type) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, PetrifiedTimber.id(path), type);
    }

    private static SimpleParticleType register(String path) {
        return register(path, FabricParticleTypes.simple());
    }

    public static final SimpleParticleType DRIPPING_RESIN = register("dripping_resin");
    public static final SimpleParticleType FALLING_RESIN = register("falling_resin");
    public static final SimpleParticleType LANDING_RESIN = register("landing_resin");

    public static void init() {}
}
