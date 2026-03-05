package archives.tater.petrifiedtimber.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BiomeDependentFeature extends Feature<BiomeDependentFeature.Configuration> {

    public BiomeDependentFeature(Codec<Configuration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<Configuration> context) {
        var biome = context.level().getBiome(context.origin());
        for (var entry : context.config().entries)
            if (entry.biomes.contains(biome))
                return entry.feature.value().place(context.level(), context.chunkGenerator(), context.random(), context.origin());
        return context.config().defaultFeature.value().place(context.level(), context.chunkGenerator(), context.random(), context.origin());
    }

    public static Builder builder(Holder<PlacedFeature> defaultFeature) {
        return new Builder(defaultFeature);
    }

    public record Configuration(
            List<Entry> entries,
            Holder<PlacedFeature> defaultFeature
    ) implements FeatureConfiguration {

        public static final Codec<Configuration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Entry.CODEC.listOf().fieldOf("entries").forGetter(Configuration::entries),
                PlacedFeature.CODEC.fieldOf("default").forGetter(Configuration::defaultFeature)
        ).apply(instance, Configuration::new));

        @Override
        public Stream<ConfiguredFeature<?, ?>> getFeatures() {
            return entries.stream().flatMap(entry -> entry.feature.value().getFeatures());
        }

        public record Entry(
                Holder<PlacedFeature> feature, HolderSet<Biome> biomes
        ) {
            public static final Codec<Entry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(Entry::feature),
                    RegistryCodecs.homogeneousList(Registries.BIOME, true).fieldOf("biomes").forGetter(Entry::biomes)
            ).apply(instance, Entry::new));
        }
    }

    public static class Builder {
        private final Holder<PlacedFeature> defaultFeature;
        private final List<Configuration.Entry> entries = new ArrayList<>();

        public Builder(Holder<PlacedFeature> defaultFeature) {
            this.defaultFeature = defaultFeature;
        }

        public Builder entry(Holder<PlacedFeature> feature, HolderSet<Biome> biomes) {
            entries.add(new Configuration.Entry(feature, biomes));
            return this;
        }

        public Configuration build() {
            return new Configuration(entries, defaultFeature);
        }
    }
}
