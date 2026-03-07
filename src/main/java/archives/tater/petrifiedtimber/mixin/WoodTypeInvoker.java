package archives.tater.petrifiedtimber.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.level.block.state.properties.WoodType;

@Mixin(WoodType.class)
public interface WoodTypeInvoker {
    @Invoker
    static WoodType invokeRegister(WoodType woodType) {
        throw new UnsupportedOperationException();
    }
}
