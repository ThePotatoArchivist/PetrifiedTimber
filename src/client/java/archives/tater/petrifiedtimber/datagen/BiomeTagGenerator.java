package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class BiomeTagGenerator extends FabricTagProvider<Biome> {
    public BiomeTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        builder(PetrifiedTimberWorldgen.HAS_MEGA_PETRIFIED_OAK_TREE)
                .forceAddTag(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_SPRUCE)
                .forceAddTag(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_JUNGLE)
                .forceAddTag(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_DARK_OAK)
                .forceAddTag(ConventionalBiomeTags.PRIMARY_WOOD_TYPE_PALE_OAK);
        builder(PetrifiedTimberWorldgen.HAS_SWAMP_BUSH)
                .forceAddTag(ConventionalBiomeTags.IS_SWAMP);
        builder(PetrifiedTimberWorldgen.NO_GRASS_FROM_BONEMEAL)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST_EDGE);
        builder(BiomeTags.WITHOUT_ZOMBIE_SIEGES)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST_EDGE);
        builder(BiomeTags.IS_OVERWORLD)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST_EDGE);
        builder(BiomeTags.HAS_MINESHAFT)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST_EDGE);
        builder(BiomeTags.HAS_RUINED_PORTAL_STANDARD)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST_EDGE);
        builder(BiomeTags.STRONGHOLD_BIASED_TO)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST)
                .add(PetrifiedTimberWorldgen.PETRIFIED_FOREST_EDGE);
    }
}
