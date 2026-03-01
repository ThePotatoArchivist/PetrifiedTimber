package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class PetrifiedTimberBlockTags {
    private static TagKey<Block> of(String path) {
        return TagKey.create(Registries.BLOCK, PetrifiedTimber.id(path));
    }

    public static final TagKey<Block> PETRIFIED_OAK_LOGS = of("petrified_oak_logs");
}
