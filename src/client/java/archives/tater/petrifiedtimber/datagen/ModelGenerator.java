package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.family(PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS)
                .generateFor(PetrifiedTimberBlocks.PETRIFIED_OAK_FAMILY);
        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD);
        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_WOOD);
        blockModelGenerators.createPlantWithDefaultItem(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING, PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createShelf(PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF, PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG);
        blockModelGenerators.createHangingSign(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG, PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN, PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_HANGING_SIGN);

        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.RESIN_COVERED_OAK_WOOD);
        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_WOOD);
        blockModelGenerators.createTrivialCube(PetrifiedTimberBlocks.RESIN_COVERED_OAK_PLANKS);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.PETRIFIED_OAK_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.PETRIFIED_OAK_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
    }
}
