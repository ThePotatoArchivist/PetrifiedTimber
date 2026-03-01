package archives.tater.petrifiedtimber;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;

import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class PetrifiedTimberClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT,
				PetrifiedTimberBlocks.PETRIFIED_OAK_DOOR,
				PetrifiedTimberBlocks.PETRIFIED_OAK_TRAPDOOR,
				PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING,
				PetrifiedTimberBlocks.POTTED_PETRIFIED_OAK_SAPLING
		);
	}
}