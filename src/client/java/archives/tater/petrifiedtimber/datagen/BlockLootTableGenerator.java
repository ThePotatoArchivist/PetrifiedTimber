package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

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
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_LEAVES);
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
        dropSelf(PetrifiedTimberBlocks.PETRIFIED_OAK_DOOR);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_LOG);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_STRIPPED_OAK_WOOD);
        dropSelf(PetrifiedTimberBlocks.RESIN_COVERED_OAK_PLANKS);
        dropOther(PetrifiedTimberBlocks.RESIN_CAULDRON, Items.CAULDRON);
    }
}
