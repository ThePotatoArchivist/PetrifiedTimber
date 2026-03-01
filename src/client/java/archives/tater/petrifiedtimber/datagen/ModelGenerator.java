package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
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
        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_WOOD);
        blockModelGenerators.createPlantWithDefaultItem(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING, PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createShelf(PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF, PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_LOG);
        blockModelGenerators.createHangingSign(PetrifiedTimberBlocks.STRIPPED_PETRIFIED_OAK_LOG, PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN, PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_HANGING_SIGN);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {

    }
}
