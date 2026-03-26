package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItemTags;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;

import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static archives.tater.petrifiedtimber.advancement.LocationTrigger.TriggerInstance.dripstoneFillCauldron;
import static net.minecraft.advancements.Advancement.Builder.recipeAdvancement;
import static net.minecraft.advancements.criterion.BlockPredicate.Builder.block;
import static net.minecraft.advancements.criterion.InventoryChangeTrigger.TriggerInstance.hasItems;
import static net.minecraft.advancements.criterion.ItemPredicate.Builder.item;
import static net.minecraft.advancements.criterion.ItemUsedOnLocationTrigger.TriggerInstance.placedBlock;
import static net.minecraft.advancements.criterion.LocationPredicate.Builder.location;
import static net.minecraft.advancements.criterion.PlayerTrigger.TriggerInstance.located;
import static net.minecraft.data.recipes.RecipeProvider.getHasName;

public class AdvancementGenerator extends FabricAdvancementProvider {

    public static final AdvancementTranslations ROOT = new AdvancementTranslations(PetrifiedTimber.id("root"));
    public static final AdvancementTranslations MELT_RESIN = new AdvancementTranslations(PetrifiedTimber.id("melt_resin"));
    public static final AdvancementTranslations COLLECT_PETRIFIED_WOOD = new AdvancementTranslations(PetrifiedTimber.id("collect_petrified_wood"));
    public static final AdvancementTranslations COLLECT_PETRIFIED_LEAVES = new AdvancementTranslations(PetrifiedTimber.id("collet_petrified_leaves"));
    public static final AdvancementTranslations COLLECT_PETRIFIED_APPLE = new AdvancementTranslations(PetrifiedTimber.id("collet_petrified_apple"));
    public static final AdvancementTranslations PLANT_PETRIFIED_SEEDS = new AdvancementTranslations(PetrifiedTimber.id("plant_petrified_seeds"));
    public static final AdvancementTranslations COLLECT_ALL_LOGS = new AdvancementTranslations(PetrifiedTimber.id("collect_all_logs"));
    public static final AdvancementTranslations FIND_PETRIFIED_FOREST = new AdvancementTranslations(PetrifiedTimber.id("find_petrified_forest"));

    public static final List<Item> VARIANT_LOGS = List.of(
            PetrifiedTimberItems.SHADOW_PETRIFIED_OAK_LOG,
            PetrifiedTimberItems.WARM_PETRIFIED_OAK_LOG,
            PetrifiedTimberItems.WATCHING_PETRIFIED_OAK_LOG,
            PetrifiedTimberItems.CHERRY_PETRIFIED_OAK_LOG
    );

    public AdvancementGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    private static AdvancementHolder register(Consumer<AdvancementHolder> consumer, AdvancementTranslations translations, Advancement.Builder builder) {
        var holder = builder.build(translations.id);
        consumer.accept(holder);
        return holder;
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        var items = registryLookup.lookupOrThrow(Registries.ITEM);

        var root = register(consumer, ROOT, recipeAdvancement()
                .display(ROOT.getDisplayInfo(
                        new ItemStackTemplate(PetrifiedTimberItems.PETRIFIED_OAK_SAPLING),
                        Optional.of(new ClientAsset.ResourceTexture(TextureMapping.getBlockTexture(PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS).sprite())),
                        AdvancementType.TASK,
                        false,
                        false,
                        false
                ))
                .addCriterion("inventory_changed", hasItems(new ItemPredicate[]{}))
        );

        var meltResin = register(consumer, MELT_RESIN, recipeAdvancement()
                .parent(root)
                .display(MELT_RESIN.getDisplayInfo(
                        PetrifiedTimberItems.MELTED_RESIN_CAULDRON,
                        AdvancementType.TASK
                ))
                .addCriterion("fill_cauldron", dripstoneFillCauldron(block()
                        .of(registryLookup.lookupOrThrow(Registries.BLOCK), PetrifiedTimberBlocks.RESIN_CAULDRON)
                ))
        );

        var petrifiedWood = register(consumer, COLLECT_PETRIFIED_WOOD, recipeAdvancement()
                .parent(meltResin)
                .display(COLLECT_PETRIFIED_WOOD.getDisplayInfo(
                        PetrifiedTimberItems.PETRIFIED_OAK_LOG,
                        AdvancementType.TASK
                ))
                .addCriterion("has_petrified_wood", hasItems(item().of(items, PetrifiedTimberItemTags.CAN_ACQUIRE_FROM_PETRIFYING)))
        );

        var petrifiedLeaves = register(consumer, COLLECT_PETRIFIED_LEAVES, recipeAdvancement()
                .parent(root)
                .display(COLLECT_PETRIFIED_LEAVES.getDisplayInfo(
                        PetrifiedTimberItems.PETRIFIED_OAK_LEAVES,
                        AdvancementType.TASK
                ))
                .addCriterion(getHasName(PetrifiedTimberItems.PETRIFIED_OAK_LEAVES), hasItems(PetrifiedTimberItems.PETRIFIED_OAK_LEAVES))
                .addCriterion(getHasName(PetrifiedTimberItems.PETRIFIED_LEAF), hasItems(PetrifiedTimberItems.PETRIFIED_LEAF))
                .requirements(AdvancementRequirements.Strategy.OR)
        );

        var petrifiedApple = register(consumer, COLLECT_PETRIFIED_APPLE, recipeAdvancement()
                .parent(petrifiedLeaves)
                .display(COLLECT_PETRIFIED_APPLE.getDisplayInfo(
                        PetrifiedTimberItems.RED_PETRIFIED_APPLE,
                        AdvancementType.TASK
                ))
                .addCriterion("has_petrified_apple", hasItems(item().of(items, PetrifiedTimberItemTags.PETRIFIED_APPLES)))
        );

        var plantSeeds = register(consumer, PLANT_PETRIFIED_SEEDS, recipeAdvancement()
                .parent(petrifiedApple)
                .display(PLANT_PETRIFIED_SEEDS.getDisplayInfo(
                        PetrifiedTimberItems.PETRIFIED_OAK_SEEDS,
                        AdvancementType.TASK
                ))
                .addCriterion("plant_seeds", placedBlock(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING_CROP))
        );

        var allLogsBuilder = recipeAdvancement()
                .parent(plantSeeds)
                .display(COLLECT_ALL_LOGS.getDisplayInfo(
                        PetrifiedTimberItems.WATCHING_PETRIFIED_OAK_LOG,
                        AdvancementType.GOAL
                ))
                .requirements(AdvancementRequirements.Strategy.AND);
        for (var log : VARIANT_LOGS)
            allLogsBuilder.addCriterion(getHasName(log), hasItems(log));
        var allLogs = register(consumer, COLLECT_ALL_LOGS, allLogsBuilder);

        var findForest = register(consumer, FIND_PETRIFIED_FOREST, recipeAdvancement()
                .parent(petrifiedLeaves)
                .display(FIND_PETRIFIED_FOREST.getDisplayInfo(
                        PetrifiedTimberItems.PETRIFIED_RED_FLOWER,
                        AdvancementType.CHALLENGE,
                        true
                ))
                .addCriterion("in_biome", located(location().setBiomes(registryLookup.getOrThrow(PetrifiedTimberWorldgen.IS_PETRIFIED))))
        );
    }

    public record AdvancementTranslations(
            Identifier id,
            String title,
            String description
    ) {
        public AdvancementTranslations(Identifier id) {
            this(
                    id,
                    id.toLanguageKey("advancements", "title"),
                    id.toLanguageKey("advancements", "description")
            );
        }

        public DisplayInfo getDisplayInfo(ItemStackTemplate icon, Optional<ClientAsset.ResourceTexture> background, AdvancementType type, boolean showToast, boolean announceChat, boolean hidden) {
            return new DisplayInfo(
                    icon,
                    Component.translatable(title),
                    Component.translatable(description),
                    background,
                    type,
                    showToast,
                    announceChat,
                    hidden
            );
        }

        public DisplayInfo getDisplayInfo(Item icon, AdvancementType type, boolean showToast, boolean announceChat, boolean hidden) {
            return getDisplayInfo(new ItemStackTemplate(icon), Optional.empty(), type, showToast, announceChat, hidden);
        }

        public DisplayInfo getDisplayInfo(Item icon, AdvancementType type, boolean hidden) {
            return getDisplayInfo(icon, type, true, true, hidden);
        }

        public DisplayInfo getDisplayInfo(Item icon, AdvancementType type) {
            return getDisplayInfo(icon, type, false);
        }
    }
}
