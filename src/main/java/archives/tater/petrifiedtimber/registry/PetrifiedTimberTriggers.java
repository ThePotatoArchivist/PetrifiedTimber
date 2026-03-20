package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.advancement.LocationTrigger;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class PetrifiedTimberTriggers {

    public static <T extends CriterionTrigger<?>> T register(String path, T trigger) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, PetrifiedTimber.id(path), trigger);
    }

    public static final LocationTrigger DRIPSTONE_FILL_CAULDRON = register("cauldron_fill", new LocationTrigger());

    public static void init() {

    }
}
