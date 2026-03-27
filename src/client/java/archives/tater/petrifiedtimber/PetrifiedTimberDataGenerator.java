package archives.tater.petrifiedtimber;

import archives.tater.petrifiedtimber.datagen.*;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class PetrifiedTimberDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.PLACED_FEATURE, FeatureGenerator::bootstrapPlaced);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, FeatureGenerator::bootstrapConfigured);
		registryBuilder.add(Registries.BIOME, BiomeGenerator::bootstrap);
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(ParticleGenerator::new);
		pack.addProvider(LangGenerator::new);
		pack.addProvider(SoundsGenerator::new);

		pack.addProvider(BlockLootTableGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		var blockTags = pack.addProvider(BlockTagGenerator::new);
		pack.addProvider((output, registriesFuture) -> new ItemTagGenerator(output, registriesFuture, blockTags));
		pack.addProvider(EntityTagGenerator::new);
		pack.addProvider(FeatureGenerator::new);
		pack.addProvider(FeatureTagGenerator::new);
		pack.addProvider(BiomeGenerator::new);
		pack.addProvider(BiomeTagGenerator::new);
		pack.addProvider(AdvancementGenerator::new);
	}

}
