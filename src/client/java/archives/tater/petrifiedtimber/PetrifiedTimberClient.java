package archives.tater.petrifiedtimber;

import archives.tater.petrifiedtimber.client.particle.DripParticleProvider;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberEntities;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberFluids;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberParticles;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.resources.model.sprite.Material;

public class PetrifiedTimberClient implements ClientModInitializer {

	private static ModelLayerLocation registerModelLayer(String path) {
		return new ModelLayerLocation(PetrifiedTimber.id(path), "main");
	}

	public static final ModelLayerLocation PETRIFIED_OAK_BOAT = registerModelLayer("boat/petrified_oak");
	public static final ModelLayerLocation PETRIFIED_OAK_CHEST_BOAT = registerModelLayer("chest_boat/petrified_oak");

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ModelLayerRegistry.registerModelLayer(PETRIFIED_OAK_BOAT, BoatModel::createBoatModel);
		ModelLayerRegistry.registerModelLayer(PETRIFIED_OAK_CHEST_BOAT, BoatModel::createChestBoatModel);

		EntityRenderers.register(PetrifiedTimberEntities.PETRIFIED_OAK_BOAT, context -> new BoatRenderer(context, PETRIFIED_OAK_BOAT));
		EntityRenderers.register(PetrifiedTimberEntities.PETRIFIED_OAK_CHEST_BOAT, context -> new BoatRenderer(context, PETRIFIED_OAK_CHEST_BOAT));

		var meltedResinTexture = new Material(PetrifiedTimber.id("block/melted_resin"));
		FluidRenderingRegistry.register(PetrifiedTimberFluids.MELTED_RESIN, new FluidModel.Unbaked(
				meltedResinTexture,
				meltedResinTexture,
				null,
				null
		));

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