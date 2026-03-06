package archives.tater.petrifiedtimber.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.data.loot.BlockLootSubProvider;

@Mixin(BlockLootSubProvider.class)
public interface BlockLootSubProviderAccessor {
    @Accessor
    static float[] getNORMAL_LEAVES_STICK_CHANCES() {
        throw new UnsupportedOperationException();
    }
}
