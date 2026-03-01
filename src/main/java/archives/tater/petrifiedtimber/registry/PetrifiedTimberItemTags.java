package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PetrifiedTimberItemTags {
    private static TagKey<Item> of(String path) {
        return TagKey.create(Registries.ITEM, PetrifiedTimber.id(path));
    }

    public static final TagKey<Item> PETRIFIED_OAK_LOGS = of("petrified_oak_logs");
}
