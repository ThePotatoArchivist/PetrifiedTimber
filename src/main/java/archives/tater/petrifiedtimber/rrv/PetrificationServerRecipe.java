package archives.tater.petrifiedtimber.rrv;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import cc.cassian.rrv.api.TagUtil;
import cc.cassian.rrv.api.recipe.ReliableServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;

public class PetrificationServerRecipe implements ReliableServerRecipe {
    private Block input = Blocks.AIR;
    private Block output = Blocks.AIR;

    public PetrificationServerRecipe() {}

    public PetrificationServerRecipe(Block input, Block output) {
        this.input = input;
        this.output = output;
    }

    public static final ReliableServerRecipeType<PetrificationServerRecipe> TYPE = ReliableServerRecipeType.register(
            PetrifiedTimber.id("petrification"),
            PetrificationServerRecipe::new
    );

    @Override
    public void writeToTag(CompoundTag tag) {
        tag.putString("input", TagUtil.blockToString(input));
        tag.putString("output", TagUtil.blockToString(output));
    }

    @Override
    public void loadFromTag(CompoundTag tag) {
        input = tag.getString("input").map(TagUtil::blockFromString).orElse(Blocks.AIR);
        output = tag.getString("output").map(TagUtil::blockFromString).orElse(Blocks.AIR);
    }

    @Override
    public ReliableServerRecipeType<? extends ReliableServerRecipe> getRecipeType() {
        return TYPE;
    }
}
