package archives.tater.petrifiedtimber;

import archives.tater.petrifiedtimber.client.particle.DripParticleProvider;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberEntities;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberFluids;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberParticles;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class PetrifiedTimberClient implements ClientModInitializer {

	public static final ModelLayerLocation PETRIFIED_OAK_BOAT = registerModelLayer("boat/petrified_oak");
	public static final ModelLayerLocation PETRIFIED_OAK_CHEST_BOAT = registerModelLayer("chest_boat/petrified_oak");

    private static ModelLayerLocation registerModelLayer(String path) {
		return new ModelLayerLocation(PetrifiedTimber.id(path), "main");
	}

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT,
				PetrifiedTimberBlocks.PETRIFIED_OAK_DOOR,
				PetrifiedTimberBlocks.PETRIFIED_OAK_TRAPDOOR,
				PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING,
				PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING,
				PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING_CROP,
				PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING_CROP,
				PetrifiedTimberBlocks.PETRIFIED_RED_FLOWER,
				PetrifiedTimberBlocks.POTTED_PETRIFIED_RED_FLOWER,
				PetrifiedTimberBlocks.PETRIFIED_YELLOW_FLOWER,
				PetrifiedTimberBlocks.POTTED_PETRIFIED_YELLOW_FLOWER,
				PetrifiedTimberBlocks.PETRIFIED_CYAN_FLOWER,
				PetrifiedTimberBlocks.POTTED_PETRIFIED_CYAN_FLOWER,
				PetrifiedTimberBlocks.WHITE_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.WHITE_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.ORANGE_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.ORANGE_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.MAGENTA_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.MAGENTA_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.LIGHT_BLUE_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.LIGHT_BLUE_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.YELLOW_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.YELLOW_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.LIME_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.LIME_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.PINK_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.PINK_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.GRAY_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.GRAY_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.LIGHT_GRAY_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.LIGHT_GRAY_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.CYAN_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.CYAN_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.PURPLE_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.PURPLE_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.BLUE_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.BLUE_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.BROWN_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.BROWN_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.GREEN_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.GREEN_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.RED_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.RED_HANGING_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.BLACK_PETRIFIED_APPLE,
				PetrifiedTimberBlocks.BLACK_HANGING_PETRIFIED_APPLE
		);

		EntityModelLayerRegistry.registerModelLayer(PETRIFIED_OAK_BOAT, BoatModel::createBoatModel);
		EntityModelLayerRegistry.registerModelLayer(PETRIFIED_OAK_CHEST_BOAT, BoatModel::createChestBoatModel);

		EntityRenderers.register(PetrifiedTimberEntities.PETRIFIED_OAK_BOAT, context -> new BoatRenderer(context, PETRIFIED_OAK_BOAT));
		EntityRenderers.register(PetrifiedTimberEntities.PETRIFIED_OAK_CHEST_BOAT, context -> new BoatRenderer(context, PETRIFIED_OAK_CHEST_BOAT));

		var meltedResinTexture = PetrifiedTimber.id("block/melted_resin");
		FluidRenderHandlerRegistry.INSTANCE.register(PetrifiedTimberFluids.MELTED_RESIN, new SimpleFluidRenderHandler(meltedResinTexture, meltedResinTexture));

		DripParticleProvider.registerDrippingParticles(
				0.87f,
				0.43f,
				0.02f,
				PetrifiedTimberFluids.MELTED_RESIN,
				PetrifiedTimberParticles.DRIPPING_RESIN,
				PetrifiedTimberParticles.FALLING_RESIN,
				PetrifiedTimberParticles.LANDING_RESIN
		);
	}
}