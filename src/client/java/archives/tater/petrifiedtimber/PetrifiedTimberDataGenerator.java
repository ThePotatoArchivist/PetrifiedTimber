package archives.tater.petrifiedtimber;

import archives.tater.petrifiedtimber.datagen.*;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;
import archives.tater.petrifiedtimber.worldgen.BiomeDependentFeature;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

import static archives.tater.petrifiedtimber.datagen.DatagenUtil.codecProvider;
import static archives.tater.petrifiedtimber.worldgen.BiomeDependentFeature.entry;

public class PetrifiedTimberDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(BlockLootTableGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(LangGenerator::new);
		var blockTags = pack.addProvider(BlockTagGenerator::new);
		pack.addProvider((output, registriesFuture) -> new ItemTagGenerator(output, registriesFuture, blockTags));
		pack.addProvider(EntityTagGenerator::new);
		pack.addProvider(codecProvider(Registries.CONFIGURED_FEATURE, ConfiguredFeature.DIRECT_CODEC, (provider, lookup) -> {
			provider.accept(PetrifiedTimberWorldgen.PETRIFIED_OAK_FEATURE, new ConfiguredFeature<>(
					PetrifiedTimberWorldgen.BIOME_DEPENDENT,
					new BiomeDependentFeature.Configuration(
							Holder.direct(new PlacedFeature(lookup.getOrThrow(TreeFeatures.OAK), List.of())),
							entry(
									Holder.direct(new PlacedFeature(lookup.getOrThrow(TreeFeatures.SPRUCE), List.of())),
									HolderSet.direct(lookup::getOrThrow,
											Biomes.TAIGA,
											Biomes.WINDSWEPT_FOREST,
											Biomes.WINDSWEPT_HILLS,
											Biomes.SNOWY_PLAINS,
											Biomes.SNOWY_TAIGA,
											Biomes.OLD_GROWTH_PINE_TAIGA,
											Biomes.OLD_GROWTH_SPRUCE_TAIGA,
											Biomes.GROVE
									)
							)
					)
			));
		}));
	}
}
