package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.block.AppleBlock;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.advancements.criterion.StatePropertiesPredicate.Builder.properties;
import static net.minecraft.world.level.storage.loot.LootPool.lootPool;
import static net.minecraft.world.level.storage.loot.LootTable.lootTable;
import static net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem;
import static net.minecraft.world.level.storage.loot.functions.SetItemCountFunction.setCount;
import static net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition.bonusLevelFlatChance;
import static net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition.hasBlockStateProperties;
import static net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly;
import static net.minecraft.world.level.storage.loot.providers.number.UniformGenerator.between;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {
    public BlockLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.SHADOW_PETRIFIED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.WARM_PETRIFIED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.CHERRY_PETRIFIED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_SHELF);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_SIGN);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_HANGING_SIGN);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_PRESSURE_PLATE);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_TRAPDOOR);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_BUTTON);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_STAIRS);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_SLAB);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE_GATE);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_FENCE);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_OAK_PLANKS);

        dropAppleCount(PetrifiedTimberBlocks.WHITE_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.WHITE_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.WHITE_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.ORANGE_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.ORANGE_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.ORANGE_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.MAGENTA_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.MAGENTA_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.MAGENTA_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.LIGHT_BLUE_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.LIGHT_BLUE_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.LIGHT_BLUE_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.YELLOW_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.YELLOW_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.YELLOW_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.LIME_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.LIME_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.LIME_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.PINK_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.PINK_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.PINK_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.GRAY_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.GRAY_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.GRAY_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.LIGHT_GRAY_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.LIGHT_GRAY_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.LIGHT_GRAY_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.CYAN_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.CYAN_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.CYAN_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.PURPLE_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.PURPLE_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.PURPLE_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.BLUE_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.BLUE_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.BLUE_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.BROWN_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.BROWN_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.BROWN_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.GREEN_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.GREEN_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.GREEN_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.RED_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.RED_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.RED_PETRIFIED_APPLE);
        dropAppleCount(PetrifiedTimberBlocks.BLACK_PETRIFIED_APPLE);
        dropOther(PetrifiedTimberBlocks.BLACK_HANGING_PETRIFIED_APPLE, PetrifiedTimberItems.BLACK_PETRIFIED_APPLE);

        add(PetrifiedTimberBlocks.PETRIFIED_OAK_DOOR, this::createDoorTable);
        dropOther(PetrifiedTimberBlocks.RESIN_CAULDRON, Items.CAULDRON);
        add(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES, this::createPetrifiedLeavesTable);
    }

    private void dropAppleCount(Block block) {
        add(block, lootTable().withPool(lootPool()
                .setRolls(exactly(1))
                .add(applyExplosionDecay(block, lootTableItem(block)
                        .apply(List.of(2, 3, 4), count -> setCount(exactly(count))
                                .when(hasBlockStateProperties(block).setProperties(properties()
                                        .hasProperty(AppleBlock.APPLES, count)
                                ))
                        )
                ))
        ));
    }

    private LootTable.Builder createPetrifiedLeavesTable(Block block) {
        var enchantments = registries.lookupOrThrow(Registries.ENCHANTMENT);
        var fortune = enchantments.getOrThrow(Enchantments.FORTUNE);

        return createSilkTouchDispatchTable(
                block,
                applyExplosionCondition(block, lootTableItem(Items.APPLE)) // TODO
                        .when(bonusLevelFlatChance(fortune, 1f / 50, 1f / 45, 1f / 40, 1f / 30, 1f / 10))
        )
                .withPool(lootPool()
                        .setRolls(exactly(1))
                        .when(doesNotHaveSilkTouch())
                        .add(applyExplosionDecay(block,
                                lootTableItem(PetrifiedTimberItems.PETRIFIED_LEAF)
                                    .apply(setCount(between(1, 2))))
                                .when(bonusLevelFlatChance(fortune, 1f / 20, 1f / 16, 1f / 12, 1f / 10))
                        )
                );
    }
}
