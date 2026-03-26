package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlockTags;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

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
                PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG,
                PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_WOOD
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.SHADOW_PETRIFIED_OAK_LOGS).add(
                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG,
                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_WOOD
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.WARM_PETRIFIED_OAK_LOGS).add(
                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG,
                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_WOOD
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.WATCHING_PETRIFIED_OAK_LOGS).add(
                PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LOG,
                PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_WOOD
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.CHERRY_PETRIFIED_OAK_LOGS).add(
                PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LOG,
                PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_WOOD
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.ALL_PETRIFIED_OAK_LOGS)
                .addTag(PetrifiedTimberBlockTags.PETRIFIED_OAK_LOGS)
                .addTag(PetrifiedTimberBlockTags.SHADOW_PETRIFIED_OAK_LOGS)
                .addTag(PetrifiedTimberBlockTags.WARM_PETRIFIED_OAK_LOGS)
                .addTag(PetrifiedTimberBlockTags.CHERRY_PETRIFIED_OAK_LOGS);
        valueLookupBuilder(PetrifiedTimberBlockTags.PETRIFIED_OAK_LEAVES).add(
                PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES,
                PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LEAVES,
                PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LEAVES,
                PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LEAVES,
                PetrifiedTimberBlocks.PALE_WATCHING_PETRIFIED_OAK_LEAVES,
                PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LEAVES
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.RESIN_COVERED_OAK_LOGS).add(
                PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG,
                PetrifiedTimberBlocks.RESIN_COVERED_OAK_WOOD,
                PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG,
                PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_WOOD
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.PETRIFICATION_CATALYST)
                .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
                .forceAddTag(BlockTags.DIRT)
                .forceAddTag(ConventionalBlockTags.STONES);
        valueLookupBuilder(PetrifiedTimberBlockTags.PETRIFICATION_ACCELERATOR)
                .forceAddTag(BlockTags.ICE);
        valueLookupBuilder(PetrifiedTimberBlockTags.PETRIFICATION_COVER)
                .addTag(PetrifiedTimberBlockTags.PETRIFICATION_CATALYST)
                .addTag(PetrifiedTimberBlockTags.PETRIFICATION_ACCELERATOR)
                .addTag(PetrifiedTimberBlockTags.ALL_PETRIFIED_OAK_LOGS)
                .addTag(PetrifiedTimberBlockTags.RESIN_COVERED_OAK_LOGS)
                .add(
                        PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS,
                        PetrifiedTimberBlocks.RESIN_COVERED_OAK_PLANKS
                );
        valueLookupBuilder(PetrifiedTimberBlockTags.MELTS_RESIN).add(
                Blocks.MAGMA_BLOCK,
                Blocks.LAVA,
                Blocks.LAVA_CAULDRON,
                Blocks.FIRE,
                Blocks.SOUL_FIRE,
                Blocks.CAMPFIRE,
                Blocks.SOUL_CAMPFIRE
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.SUPPORTS_HANGING_APPLE)
                .forceAddTag(BlockTags.LEAVES)
                .forceAddTag(BlockTags.LOGS)
                .addTag(PetrifiedTimberBlockTags.ALL_PETRIFIED_OAK_LOGS)
                .addTag(PetrifiedTimberBlockTags.PETRIFIED_OAK_LEAVES);
        valueLookupBuilder(PetrifiedTimberBlockTags.PETRIFIED_SAPLING_GROWS_ON).add(
                Blocks.FARMLAND
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.PETRIFIED_APPLES).add(
                PetrifiedTimberBlocks.WHITE_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.ORANGE_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.MAGENTA_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.LIGHT_BLUE_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.YELLOW_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.LIME_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.PINK_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.GRAY_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.LIGHT_GRAY_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.CYAN_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.PURPLE_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.BLUE_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.BROWN_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.GREEN_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.RED_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.BLACK_PETRIFIED_APPLE
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.HANGING_PETRIFIED_APPLES).add(
                PetrifiedTimberBlocks.WHITE_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.ORANGE_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.MAGENTA_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.LIGHT_BLUE_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.YELLOW_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.LIME_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.PINK_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.GRAY_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.LIGHT_GRAY_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.CYAN_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.PURPLE_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.BLUE_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.BROWN_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.GREEN_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.RED_HANGING_PETRIFIED_APPLE,
                PetrifiedTimberBlocks.BLACK_HANGING_PETRIFIED_APPLE
        );
        valueLookupBuilder(PetrifiedTimberBlockTags.ALL_PETRIFIED_APPLES)
                .addTag(PetrifiedTimberBlockTags.PETRIFIED_APPLES)
                .addTag(PetrifiedTimberBlockTags.HANGING_PETRIFIED_APPLES);

        valueLookupBuilder(BlockTags.SIGNS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_SIGN);
        valueLookupBuilder(BlockTags.WALL_SIGNS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_SIGN);
        valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN);
        valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_HANGING_SIGN);
        valueLookupBuilder(BlockTags.FENCES).add(PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE_GATE);
        valueLookupBuilder(BlockTags.STONE_BUTTONS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_BUTTON);
        valueLookupBuilder(BlockTags.STONE_PRESSURE_PLATES).add(PetrifiedTimberBlocks.PETRIFIED_OAK_PRESSURE_PLATE);
        valueLookupBuilder(BlockTags.WOODEN_SHELVES).add(PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF);
        valueLookupBuilder(BlockTags.FLOWER_POTS).add(
                PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING,
                PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING_CROP,
                PetrifiedTimberBlocks.POTTED_PETRIFIED_RED_FLOWER,
                PetrifiedTimberBlocks.POTTED_PETRIFIED_YELLOW_FLOWER,
                PetrifiedTimberBlocks.POTTED_PETRIFIED_CYAN_FLOWER
        );
        valueLookupBuilder(BlockTags.DOORS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_DOOR);
        valueLookupBuilder(BlockTags.TRAPDOORS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_TRAPDOOR);
        valueLookupBuilder(BlockTags.STAIRS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_STAIRS);
        valueLookupBuilder(BlockTags.SLABS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_SLAB);
        valueLookupBuilder(BlockTags.SAPLINGS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING);
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(PetrifiedTimberBlockTags.ALL_PETRIFIED_OAK_LOGS)
                .addTag(PetrifiedTimberBlockTags.PETRIFIED_OAK_LEAVES).add(
                PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS,
                PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING,
                PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING_CROP,
                PetrifiedTimberBlocks.PETRIFIED_RED_FLOWER,
                PetrifiedTimberBlocks.PETRIFIED_YELLOW_FLOWER,
                PetrifiedTimberBlocks.PETRIFIED_CYAN_FLOWER,
                PetrifiedTimberBlocks.STACKED_ROCKS,
                PetrifiedTimberBlocks.MOSSY_STACKED_ROCKS,
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
        valueLookupBuilder(BlockTags.LOGS_THAT_BURN).addTag(PetrifiedTimberBlockTags.RESIN_COVERED_OAK_LOGS);
        valueLookupBuilder(BlockTags.PLANKS).add(PetrifiedTimberBlocks.RESIN_COVERED_OAK_PLANKS);
        valueLookupBuilder(BlockTags.CAULDRONS).add(PetrifiedTimberBlocks.RESIN_CAULDRON);
        valueLookupBuilder(BlockTags.CROPS).add(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING_CROP);
        valueLookupBuilder(BlockTags.MAINTAINS_FARMLAND).add(
                PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING,
                PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING_CROP
        );
        valueLookupBuilder(ConventionalBlockTags.NORMAL_COBBLESTONES).add(PetrifiedTimberBlocks.STACKED_ROCKS);
        valueLookupBuilder(ConventionalBlockTags.MOSSY_COBBLESTONES).add(PetrifiedTimberBlocks.MOSSY_STACKED_ROCKS);
    }
}
