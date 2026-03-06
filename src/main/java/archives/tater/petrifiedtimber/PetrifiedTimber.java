package archives.tater.petrifiedtimber;

import archives.tater.petrifiedtimber.registry.*;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetrifiedTimber implements ModInitializer {
	public static final String MOD_ID = "petrifiedtimber";

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static String sId(String path) {
		return MOD_ID + ":" + path;
	}

	public static String uId(String path) {
		return MOD_ID + "_" + path;
	}

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		PetrifiedTimberBlocks.init();
		PetrifiedTimberItems.init();
		PetrifiedTimberFluids.init();
		PetrifiedTimberEntities.init();
		PetrifiedTimberWorldgen.init();
	}
}