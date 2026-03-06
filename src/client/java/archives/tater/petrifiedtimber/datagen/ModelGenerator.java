package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.block.ResinCauldronBlock;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

        createResinCauldron(PetrifiedTimberBlocks.RESIN_CAULDRON, blockModelGenerators);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.PETRIFIED_OAK_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.PETRIFIED_OAK_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.MELTED_RESIN_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.PETRIFIED_LEAF, ModelTemplates.FLAT_ITEM);
    }

    private static final Path RESIN_CAULDRON_TEMPLATE = FabricLoader.getInstance()
            .getModContainer(PetrifiedTimber.MOD_ID).orElseThrow()
            .findPath("assets/" + PetrifiedTimber.MOD_ID + "/template_resin_cauldron.json")
            .orElseThrow();

    private void createResinCauldron(Block block, BlockModelGenerators blockModelGenerators) {
        JsonElement template;
        try (var reader = Files.newBufferedReader(RESIN_CAULDRON_TEMPLATE); var jsonReader = new JsonReader(reader)) {
            template = JsonParser.parseReader(jsonReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(ResinCauldronBlock.LEVEL).generate(level -> {
                    var id = ModelLocationUtils.getModelLocation(block, "_level" + level);

                    blockModelGenerators.modelOutput.accept(id, () -> {
                        var model = template.deepCopy();
                        var elements = model.getAsJsonObject()
                                .getAsJsonArray("elements");
                        elements.get(elements.size() - 1).getAsJsonObject()
                                .getAsJsonArray("to")
                                .set(1, new JsonPrimitive(ResinCauldronBlock.getPixelContentHeight(level)));
                        return model;
                    });

                    return BlockModelGenerators.plainVariant(id);
                }))
        );
    }
}
