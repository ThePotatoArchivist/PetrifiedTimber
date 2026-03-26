package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberEntities;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends FabricTagsProvider.EntityTypeTagsProvider {
    public EntityTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(EntityTypeTags.BOAT).add(
                PetrifiedTimberEntities.PETRIFIED_OAK_BOAT,
                PetrifiedTimberEntities.PETRIFIED_OAK_CHEST_BOAT
        );
    }
}
