package archives.tater.petrifiedtimber.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

@Mixin(CauldronInteraction.Dispatcher.class)
public interface CauldronInteractionDispatcherAccessor {
    @Invoker
    void invokePut(final TagKey<Item> tag, final CauldronInteraction interaction);

    @Invoker
    void invokePut(final Item item, final CauldronInteraction interaction);
}
