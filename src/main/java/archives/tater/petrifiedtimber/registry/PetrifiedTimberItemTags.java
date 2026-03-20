package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PetrifiedTimberItemTags {
    private static TagKey<Item> of(String path) {
        return TagKey.create(Registries.ITEM, PetrifiedTimber.id(path));
    }

    private static TagKey<Item> of(TagKey<Block> tag) {
        return TagKey.create(Registries.ITEM, tag.location());
    }

    public static final TagKey<Item> PETRIFIED_OAK_LOGS = of(PetrifiedTimberBlockTags.PETRIFIED_OAK_LOGS);
    public static final TagKey<Item> SHADOW_PETRIFIED_OAK_LOGS = of(PetrifiedTimberBlockTags.SHADOW_PETRIFIED_OAK_LOGS);
    public static final TagKey<Item> WARM_PETRIFIED_OAK_LOGS = of(PetrifiedTimberBlockTags.WARM_PETRIFIED_OAK_LOGS);
    public static final TagKey<Item> WATCHING_PETRIFIED_OAK_LOGS = of(PetrifiedTimberBlockTags.WATCHING_PETRIFIED_OAK_LOGS);
    public static final TagKey<Item> CHERRY_PETRIFIED_OAK_LOGS = of(PetrifiedTimberBlockTags.CHERRY_PETRIFIED_OAK_LOGS);
    public static final TagKey<Item> ALL_PETRIFIED_OAK_LOGS = of(PetrifiedTimberBlockTags.ALL_PETRIFIED_OAK_LOGS);
    public static final TagKey<Item> RESIN_COVERED_OAK_LOGS = of(PetrifiedTimberBlockTags.RESIN_COVERED_OAK_LOGS);
    public static final TagKey<Item> PETRIFIED_OAK_LEAVES = of(PetrifiedTimberBlockTags.PETRIFIED_OAK_LEAVES);
    public static final TagKey<Item> CAN_ACQUIRE_FROM_PETRIFYING = of("petrified_wood_blocks");
    public static final TagKey<Item> PETRIFIED_APPLES = of("petrified_apples");
}
