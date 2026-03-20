package archives.tater.petrifiedtimber.client.rrv;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import cc.cassian.rrv.api.recipe.ReliableClientRecipeType;
import cc.cassian.rrv.common.recipe.inventory.RecipeViewMenu;
import org.jetbrains.annotations.Nullable;

public class PetrificationClientRecipeType implements ReliableClientRecipeType {
    public static final String DISPLAY_NAME = "rrv.client_recipe_type.petrifiedtimer.petrification";
    public static final Identifier ID = PetrifiedTimber.id("petrification");
    public static final PetrificationClientRecipeType INSTANCE = new PetrificationClientRecipeType();

    private PetrificationClientRecipeType() {}

    @Override public Component getDisplayName() {
        return Component.translatable(DISPLAY_NAME);
    }

    @Override public int getDisplayWidth() { return 106; }

    @Override public int getDisplayHeight() { return 88; }

    @Override
    public @Nullable Identifier getGuiTexture() {
        return PetrifiedTimber.id("textures/gui/rrv/petrification.png");
    }

    @Override
    public int getSlotCount() {
        return 5;
    }

    @Override
    public void placeSlots(RecipeViewMenu.SlotDefinition slotDefinition) {
        slotDefinition.addItemSlot(0, 19, 53);
        slotDefinition.addItemSlot(1, 1, 71);
        slotDefinition.addItemSlot(2, 19, 71);
        slotDefinition.addItemSlot(3, 37, 71);
        slotDefinition.addItemSlot(4, 89, 61);
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    public ItemStack getIcon() {
        return PetrifiedTimberItems.PETRIFIED_OAK_LOG.getDefaultInstance();
    }
}
