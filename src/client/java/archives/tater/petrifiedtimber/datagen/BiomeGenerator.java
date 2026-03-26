package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberSounds;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.concurrent.CompletableFuture;

public class BiomeGenerator extends FabricDynamicRegistryProvider {
    public BiomeGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
    }

    @Override
    public String getName() {
        return "Biomes";
    }


    private static BiomeGenerationSettings.Builder petrifiedForestGenSettings(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        var genSettings = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
        OverworldBiomes.globalOverworldGeneration(genSettings);
        BiomeDefaultFeatures.addDefaultOres(genSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(genSettings);
        return genSettings;
    }

    private static Biome petrifiedForestLike(BiomeGenerationSettings.Builder genSettings) {
        return OverworldBiomes.baseBiome(0.7f, 0.8f)
                .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
                .setAttribute(EnvironmentAttributes.CAN_PILLAGER_PATROL_SPAWN, false)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(PetrifiedTimberSounds.MUSIC_BIOME_PETRIFIED_FOREST))
                .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x79A6FF)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x295DFF)
                        .grassColorOverride(0xA8FF69)
                        .foliageColorOverride(0x28FF08)
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 1, 4))
                        .creatureGenerationProbability(0.4f)
                        .build())
                .generationSettings(genSettings.build())
                .build();
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var worldCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(PetrifiedTimberWorldgen.PETRIFIED_FOREST, petrifiedForestLike(petrifiedForestGenSettings(placedFeatures, worldCarvers)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PetrifiedTimberWorldgen.PLACED_TREES_PETRIFIED_OAK)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PetrifiedTimberWorldgen.PLACED_PETRIFIED_FLOWER)));
        context.register(PetrifiedTimberWorldgen.PETRIFIED_PLAINS, petrifiedForestLike(petrifiedForestGenSettings(placedFeatures, worldCarvers)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PetrifiedTimberWorldgen.PLACED_TREES_PETRIFIED_OAK_SPARSE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PetrifiedTimberWorldgen.PLACED_PETRIFIED_FLOWER)));
        context.register(PetrifiedTimberWorldgen.PETRIFIED_RIDGE, petrifiedForestLike(petrifiedForestGenSettings(placedFeatures, worldCarvers)
                .addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, PetrifiedTimberWorldgen.PLACED_ROCK)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PetrifiedTimberWorldgen.PLACED_TREES_PETRIFIED_OAK_SPARSE)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PetrifiedTimberWorldgen.PLACED_PETRIFIED_FLOWER_SPARSE)));
    }
}
