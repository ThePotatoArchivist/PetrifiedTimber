package archives.tater.petrifiedtimber;

import archives.tater.petrifiedtimber.datagen.*;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

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
	}
}
