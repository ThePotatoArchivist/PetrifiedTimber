package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlockTags;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(PetrifiedTimberBlockTags.PETRIFIED_OAK_LOGS).add(
                PetrifiedTimberBlocks.PETRIFIED_OAK_LOG,
                PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD,
                PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_LOG,
                PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_WOOD
        );
        valueLookupBuilder(BlockTags.LOGS).addTag(PetrifiedTimberBlockTags.PETRIFIED_OAK_LOGS);
        valueLookupBuilder(BlockTags.SIGNS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_SIGN);
        valueLookupBuilder(BlockTags.WALL_SIGNS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_SIGN);
        valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN);
        valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_HANGING_SIGN);
        valueLookupBuilder(BlockTags.FENCES).add(PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE_GATE);
        valueLookupBuilder(BlockTags.STONE_BUTTONS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_BUTTON);
        valueLookupBuilder(BlockTags.STONE_PRESSURE_PLATES).add(PetrifiedTimberBlocks.PETRIFIED_OAK_PRESSURE_PLATE);
        valueLookupBuilder(BlockTags.WOODEN_SHELVES).add(PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF);
        valueLookupBuilder(BlockTags.FLOWER_POTS).add(PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING);
        valueLookupBuilder(BlockTags.DOORS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_DOOR);
        valueLookupBuilder(BlockTags.TRAPDOORS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_TRAPDOOR);
        valueLookupBuilder(BlockTags.STAIRS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_STAIRS);
        valueLookupBuilder(BlockTags.SLABS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_SLAB);
        valueLookupBuilder(BlockTags.SAPLINGS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING);
        valueLookupBuilder(BlockTags.LEAVES).add(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES);
        valueLookupBuilder(BlockTags.PLANKS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD,
                PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS,
                PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING,
                PetrifiedTimberBlocks.PETRIFIED_OAK_LOG,
                PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_LOG,
                PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_WOOD,
                PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES,
                PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF,
                PetrifiedTimberBlocks.PETRIFIED_OAK_SIGN,
                PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_SIGN,
                PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN,
                PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_HANGING_SIGN,
                PetrifiedTimberBlocks.PETRIFIED_OAK_PRESSURE_PLATE,
                PetrifiedTimberBlocks.PETRIFIED_OAK_TRAPDOOR,
                PetrifiedTimberBlocks.PETRIFIED_OAK_BUTTON,
                PetrifiedTimberBlocks.PETRIFIED_OAK_STAIRS,
                PetrifiedTimberBlocks.PETRIFIED_OAK_SLAB,
                PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE_GATE,
                PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE,
                PetrifiedTimberBlocks.PETRIFIED_OAK_DOOR
        );
    }
}
