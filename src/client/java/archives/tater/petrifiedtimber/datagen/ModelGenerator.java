package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.block.AppleBlock;
import archives.tater.petrifiedtimber.block.CropSaplingBlock;
import archives.tater.petrifiedtimber.block.PetrifiedSaplingCropBlock;
import archives.tater.petrifiedtimber.block.ResinCauldronBlock;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.loader.api.FabricLoader;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static net.minecraft.client.data.models.BlockModelGenerators.createSimpleBlock;
import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public class ModelGenerator extends FabricModelProvider {

    public static final TextureSlot APPLE = TextureSlot.create("apple");
    public static final ModelTemplate APPLE_ONE = new ModelTemplate(Optional.of(PetrifiedTimber.id("block/template_petrified_apple_one")), Optional.of("_one"), APPLE);
    public static final ModelTemplate APPLE_TWO = new ModelTemplate(Optional.of(PetrifiedTimber.id("block/template_petrified_apple_two")), Optional.of("_two"), APPLE);
    public static final ModelTemplate APPLE_THREE = new ModelTemplate(Optional.of(PetrifiedTimber.id("block/template_petrified_apple_three")), Optional.of("_three"), APPLE);
    public static final ModelTemplate APPLE_FOUR = new ModelTemplate(Optional.of(PetrifiedTimber.id("block/template_petrified_apple_four")), Optional.of("_four"), APPLE);
    public static final ModelTemplate APPLE_HANGING = new ModelTemplate(Optional.of(PetrifiedTimber.id("block/template_petrified_apple_hanging")), Optional.empty(), APPLE);

    private static TextureMapping apple(Block block) {
        return new TextureMapping().put(APPLE, TextureMapping.getBlockTexture(block));
    }

    private void createApple(Block main, Block hanging, BlockModelGenerators blockModelGenerators) {
        var mapping = apple(main);
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(main).with(PropertyDispatch.initial(AppleBlock.APPLES)
                .select(1, plainVariant(APPLE_ONE.create(main, mapping, blockModelGenerators.modelOutput)))
                .select(2, plainVariant(APPLE_TWO.create(main, mapping, blockModelGenerators.modelOutput)))
                .select(3, plainVariant(APPLE_THREE.create(main, mapping, blockModelGenerators.modelOutput)))
                .select(4, plainVariant(APPLE_FOUR.create(main, mapping, blockModelGenerators.modelOutput)))
        ));
        blockModelGenerators.blockStateOutput.accept(createSimpleBlock(hanging, plainVariant(APPLE_HANGING.create(hanging, mapping, blockModelGenerators.modelOutput))));
        blockModelGenerators.registerSimpleFlatItemModel(main.asItem());
    }

    public ModelGenerator(FabricPackOutput output) {
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
        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_WOOD);
        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_WOOD);
        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_WOOD);
        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_WOOD);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.WATCHING_PETRIFIED_OAK_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.PALE_WATCHING_PETRIFIED_OAK_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LEAVES, TexturedModel.LEAVES);
        createCropSapling(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING, PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED, blockModelGenerators);
        createCrossCrop(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING_CROP, PetrifiedSaplingCropBlock.AGE, blockModelGenerators);
        createPotted(
                PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING_CROP,
                BlockModelGenerators.PlantType.NOT_TINTED,
                blockModelGenerators,
                TextureMapping.plant(TextureMapping.getBlockTexture(PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING_CROP))
        );
        blockModelGenerators.createPlantWithDefaultItem(PetrifiedTimberBlocks.PETRIFIED_RED_FLOWER, PetrifiedTimberBlocks.POTTED_PETRIFIED_RED_FLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createPlantWithDefaultItem(PetrifiedTimberBlocks.PETRIFIED_YELLOW_FLOWER, PetrifiedTimberBlocks.POTTED_PETRIFIED_YELLOW_FLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createPlantWithDefaultItem(PetrifiedTimberBlocks.PETRIFIED_CYAN_FLOWER, PetrifiedTimberBlocks.POTTED_PETRIFIED_CYAN_FLOWER, BlockModelGenerators.PlantType.NOT_TINTED);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.STACKED_ROCKS, TexturedModel.CUBE);
        blockModelGenerators.createTrivialBlock(PetrifiedTimberBlocks.MOSSY_STACKED_ROCKS, TexturedModel.CUBE);
        blockModelGenerators.createShelf(PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF, PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG);
        blockModelGenerators.createHangingSign(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG, PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN, PetrifiedTimberBlocks.PETRIFIED_OAK_WALL_HANGING_SIGN);

        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.RESIN_COVERED_OAK_WOOD);
        blockModelGenerators.woodProvider(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG)
                .logWithHorizontal(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG)
                .wood(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_WOOD);
        blockModelGenerators.createTrivialCube(PetrifiedTimberBlocks.RESIN_COVERED_OAK_PLANKS);

        createApple(PetrifiedTimberBlocks.WHITE_PETRIFIED_APPLE, PetrifiedTimberBlocks.WHITE_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.ORANGE_PETRIFIED_APPLE, PetrifiedTimberBlocks.ORANGE_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.MAGENTA_PETRIFIED_APPLE, PetrifiedTimberBlocks.MAGENTA_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.LIGHT_BLUE_PETRIFIED_APPLE, PetrifiedTimberBlocks.LIGHT_BLUE_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.YELLOW_PETRIFIED_APPLE, PetrifiedTimberBlocks.YELLOW_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.LIME_PETRIFIED_APPLE, PetrifiedTimberBlocks.LIME_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.PINK_PETRIFIED_APPLE, PetrifiedTimberBlocks.PINK_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.GRAY_PETRIFIED_APPLE, PetrifiedTimberBlocks.GRAY_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.LIGHT_GRAY_PETRIFIED_APPLE, PetrifiedTimberBlocks.LIGHT_GRAY_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.CYAN_PETRIFIED_APPLE, PetrifiedTimberBlocks.CYAN_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.PURPLE_PETRIFIED_APPLE, PetrifiedTimberBlocks.PURPLE_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.BLUE_PETRIFIED_APPLE, PetrifiedTimberBlocks.BLUE_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.BROWN_PETRIFIED_APPLE, PetrifiedTimberBlocks.BROWN_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.GREEN_PETRIFIED_APPLE, PetrifiedTimberBlocks.GREEN_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.RED_PETRIFIED_APPLE, PetrifiedTimberBlocks.RED_HANGING_PETRIFIED_APPLE, blockModelGenerators);
        createApple(PetrifiedTimberBlocks.BLACK_PETRIFIED_APPLE, PetrifiedTimberBlocks.BLACK_HANGING_PETRIFIED_APPLE, blockModelGenerators);

        createResinCauldron(PetrifiedTimberBlocks.RESIN_CAULDRON, blockModelGenerators);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.PETRIFIED_OAK_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.PETRIFIED_OAK_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.MELTED_RESIN_BOTTLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(PetrifiedTimberItems.PETRIFIED_LEAF, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.itemModelOutput.accept(PetrifiedTimberItems.MELTED_RESIN_CAULDRON, ItemModelUtils.plainModel(
                ModelLocationUtils.getModelLocation(PetrifiedTimberBlocks.RESIN_CAULDRON, "_level" + ResinCauldronBlock.MAX_LEVEL)
        ));
    }

    public static final ModelTemplate CROSS_CROP = new ModelTemplate(Optional.of(PetrifiedTimber.id("block/cross_crop")), Optional.empty(), TextureSlot.CROP);

    private static void createCrossCrop(Block block, IntegerProperty ageProperty, BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(ageProperty).generate(age ->
                        plainVariant(blockModelGenerators.createSuffixedVariant(
                                block,
                                "_stage" + age,
                                CROSS_CROP,
                                TextureMapping::crop
                        ))
                ))
        );
        blockModelGenerators.registerSimpleFlatItemModel(block.asItem());
    }

    private static void createCropSapling(Block block, Block pottedBlock, BlockModelGenerators.PlantType plantType, BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.registerSimpleItemModel(block.asItem(), plantType.createItemModel(blockModelGenerators, block));
        var texture = TextureMapping.getBlockTexture(block);
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
                .with(PropertyDispatch.initial(CropSaplingBlock.CROP)
                        .select(false, plainVariant(ModelTemplates.CROSS.create(block, TextureMapping.cross(texture), blockModelGenerators.modelOutput)))
                        .select(true, plainVariant(CROSS_CROP.createWithSuffix(block, "_crop", TextureMapping.crop(texture), blockModelGenerators.modelOutput)))
                )
        );
        createPotted(block, pottedBlock, plantType, blockModelGenerators);
    }

    private static void createPotted(Block block, Block pottedBlock, BlockModelGenerators.PlantType plantType, BlockModelGenerators blockModelGenerators) {
        createPotted(pottedBlock, plantType, blockModelGenerators, plantType.getPlantTextureMapping(block));
    }

    private static void createPotted(Block pottedBlock, BlockModelGenerators.PlantType plantType, BlockModelGenerators blockModelGenerators, TextureMapping plantTextureMapping) {
        blockModelGenerators.blockStateOutput.accept(createSimpleBlock(
                pottedBlock, plainVariant(
                plantType.getCrossPot().create(
                        pottedBlock,
                        plantTextureMapping,
                        blockModelGenerators.modelOutput
                )
        )));
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

                    return plainVariant(id);
                }))
        );
    }
}
