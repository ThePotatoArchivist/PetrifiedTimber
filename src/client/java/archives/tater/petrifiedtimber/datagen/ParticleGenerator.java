package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberParticles;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;

import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static java.util.Objects.requireNonNull;

public class ParticleGenerator extends FabricCodecDataProvider<List<Identifier>> {

    public static final Codec<List<Identifier>> CODEC = Identifier.CODEC.listOf(1, Integer.MAX_VALUE).fieldOf("textures").codec();

    public ParticleGenerator(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, registriesFuture, PackOutput.Target.RESOURCE_PACK, "particles", CODEC);
    }

    @Override
    protected void configure(BiConsumer<Identifier, List<Identifier>> provider, HolderLookup.Provider lookup) {
        generateParticles((type, textures) -> provider.accept(requireNonNull(BuiltInRegistries.PARTICLE_TYPE.getKey(type)), textures));
    }

    protected void generateParticles(BiConsumer<ParticleType<?>, List<Identifier>> provider) {
        provider.accept(PetrifiedTimberParticles.DRIPPING_RESIN, List.of(Identifier.withDefaultNamespace("drip_hang")));
        provider.accept(PetrifiedTimberParticles.FALLING_RESIN, List.of(Identifier.withDefaultNamespace("drip_fall")));
        provider.accept(PetrifiedTimberParticles.LANDING_RESIN, List.of(Identifier.withDefaultNamespace("drip_land")));
    }

    @Override
    public String getName() {
        return "Particles";
    }
}
