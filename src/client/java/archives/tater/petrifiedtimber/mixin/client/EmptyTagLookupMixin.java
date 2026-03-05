package archives.tater.petrifiedtimber.mixin.client;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.RegistrySetBuilder;

@Mixin(targets = "net.minecraft.core.RegistrySetBuilder$EmptyTagRegistryLookup")
public abstract class EmptyTagLookupMixin<T> extends RegistrySetBuilder.EmptyTagLookup<T> implements HolderLookup.RegistryLookup<T> {
    protected EmptyTagLookupMixin(HolderOwner<T> owner) {
        super(owner);
    }

    @Override
    public boolean canSerializeIn(HolderOwner<T> owner) {
        return this.owner.canSerializeIn(owner);
    }
}
