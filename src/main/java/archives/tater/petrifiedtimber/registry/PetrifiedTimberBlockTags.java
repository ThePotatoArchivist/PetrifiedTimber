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
    public static final TagKey<Block> SHADOW_PETRIFIED_OAK_LOGS = of("shadow_petrified_oak_logs");
    public static final TagKey<Block> WARM_PETRIFIED_OAK_LOGS = of("warm_petrified_oak_logs");
    public static final TagKey<Block> WATCHING_PETRIFIED_OAK_LOGS = of("watching_petrified_oak_logs");
    public static final TagKey<Block> CHERRY_PETRIFIED_OAK_LOGS = of("cherry_petrified_oak_logs");
    public static final TagKey<Block> ALL_PETRIFIED_OAK_LOGS = of("all_petrified_oak_logs");
    public static final TagKey<Block> RESIN_COVERED_OAK_LOGS = of("resin_covered_oak_logs");
    public static final TagKey<Block> PETRIFIED_OAK_LEAVES = of("petrified_oak_leaves");
    public static final TagKey<Block> PETRIFICATION_COVER = of("petrification_cover");
    public static final TagKey<Block> PETRIFICATION_CATALYST = of("petrification_catalyst");
    public static final TagKey<Block> PETRIFICATION_ACCELERATOR = of("petrification_accelerator");
    public static final TagKey<Block> MELTS_RESIN = of("melts_resin");
    public static final TagKey<Block> SUPPORTS_HANGING_APPLE = of("supports_hanging_apple");
    public static final TagKey<Block> PETRIFIED_SAPLING_GROWS_ON = of("petrified_sapling_grows_on");
    public static final TagKey<Block> PETRIFIED_APPLES = of("petrified_apples");
    public static final TagKey<Block> HANGING_PETRIFIED_APPLES = of("hanging_petrified_apples");
    public static final TagKey<Block> ALL_PETRIFIED_APPLES = of("all_petrified_apples");
}
