package archives.tater.petrifiedtimber.datagen;

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

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.storage.loot.LootPool.lootPool;
import static net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem;
import static net.minecraft.world.level.storage.loot.functions.SetItemCountFunction.setCount;
import static net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition.bonusLevelFlatChance;
import static net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly;
import static net.minecraft.world.level.storage.loot.providers.number.UniformGenerator.between;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {
    public BlockLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_PLANKS);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_SAPLING);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_STRIPPED_OAK_WOOD);
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
        add(PetrifiedTimberBlocks.PETRIFIED_OAK_DOOR, this::createDoorTable);
        dropOther(PetrifiedTimberBlocks.RESIN_CAULDRON, Items.CAULDRON);
        add(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES, this::createPetrifiedLeavesTable);
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
