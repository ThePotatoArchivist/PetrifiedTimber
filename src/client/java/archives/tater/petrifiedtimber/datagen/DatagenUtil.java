package archives.tater.petrifiedtimber.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack.RegistryDependentFactory;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;

import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.function.BiConsumer;

public class DatagenUtil {
    public static <T> RegistryDependentFactory<FabricCodecDataProvider<T>> codecProvider(ResourceKey<? extends Registry<?>> registry, Codec<T> codec, BiConsumer<BiConsumer<ResourceKey<T>, T>, HolderLookup.Provider> configure) {
        return (output, registriesFuture) -> new FabricCodecDataProvider<>(output, registriesFuture, registry, codec) {
            @Override
            protected void configure(BiConsumer<Identifier, T> provider, HolderLookup.Provider lookup) {
                configure.accept((key, value) -> provider.accept(key.identifier(), value), lookup);
            }

            @Override
            public String getName() {
                return registry.identifier().toString();
            }
        };
    }
}
