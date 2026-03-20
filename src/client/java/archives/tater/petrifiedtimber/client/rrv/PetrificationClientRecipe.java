package archives.tater.petrifiedtimber.client.rrv;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberBlockTags;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import cc.cassian.rrv.api.recipe.ReliableClientRecipe;
import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import cc.cassian.rrv.common.recipe.inventory.SlotContent;

import java.util.List;

public record PetrificationClientRecipe(
        SlotContent input,
        SlotContent result,
        SlotContent cover,
        SlotContent catalyst,
        SlotContent accelerator
) implements ReliableClientRecipe {

    public static final String COVER_DESCRIPTION_TRANSLATION = "rrv.petrifiedtimber.petrification.cover";
    public static final String CATALYST_DESCRIPTION_TRANSLATION = "rrv.petrifiedtimber.petrification.catalyst";
    public static final String ACCELERATOR_DESCRIPTION_TRANSLATION = "rrv.petrifiedtimber.petrification.accelerator";

    private static final Style DESCRIPTION_STYLE = Style.EMPTY.applyFormats(ChatFormatting.DARK_PURPLE, ChatFormatting.ITALIC);

    private static final Component COVER_DESCRIPTION = Component.translatable(COVER_DESCRIPTION_TRANSLATION).withStyle(DESCRIPTION_STYLE);
    private static final Component CATALYST_DESCRIPTION = Component.translatable(CATALYST_DESCRIPTION_TRANSLATION).withStyle(DESCRIPTION_STYLE);
    private static final Component ACCELERATOR_DESCRIPTION = Component.translatable(ACCELERATOR_DESCRIPTION_TRANSLATION).withStyle(DESCRIPTION_STYLE);

    public static SlotContent blockTagContent(TagKey<Block> blockTag) {
        return BuiltInRegistries.BLOCK.get(blockTag)
                .map(holders -> SlotContent.ofItemList(holders.stream()
                        .map(holder -> holder.value().asItem())
                        .filter(item -> item != Items.AIR)
                        .toList()
                ))
                .orElseGet(SlotContent::of);
    }

    public PetrificationClientRecipe(Block input, Block result, TagKey<Block> cover, TagKey<Block> catalyst, TagKey<Block> accelerator) {
        this(
                SlotContent.of(input),
                SlotContent.of(result),
                blockTagContent(cover),
                blockTagContent(catalyst),
                blockTagContent(accelerator)
        );
    }

    public PetrificationClientRecipe(Block input, Block result) {
        this(
                input,
                result,
                PetrifiedTimberBlockTags.PETRIFICATION_COVER,
                PetrifiedTimberBlockTags.PETRIFICATION_CATALYST,
                PetrifiedTimberBlockTags.PETRIFICATION_ACCELERATOR
        );
    }

    @Override
    public ReliableClientRecipeType getViewType() {
        return PetrificationClientRecipeType.INSTANCE;
    }

    @Override
    public void bindSlots(RecipeViewMenu.SlotFillContext slotFillContext) {
        slotFillContext.bindSlot(0, input);

        slotFillContext.bindSlot(1, cover);
        slotFillContext.addAdditionalStackModifier(1, (stack, tooltip) -> {
            tooltip.add(COVER_DESCRIPTION);
        });

        slotFillContext.bindSlot(2, catalyst);
        slotFillContext.addAdditionalStackModifier(2, (stack, tooltip) -> {
            tooltip.add(CATALYST_DESCRIPTION);
        });

        slotFillContext.bindSlot(3, accelerator);
        slotFillContext.addAdditionalStackModifier(3, (stack, tooltip) -> {
            tooltip.add(ACCELERATOR_DESCRIPTION);
        });

        slotFillContext.bindSlot(4, result);
    }

    @Override
    public List<SlotContent> getIngredients() {
        return List.of(input, cover, catalyst, accelerator);
    }

    @Override
    public List<SlotContent> getResults() {
        return List.of(result);
    }
}
