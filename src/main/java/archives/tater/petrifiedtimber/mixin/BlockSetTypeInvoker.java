package archives.tater.petrifiedtimber.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.level.block.state.properties.BlockSetType;

@Mixin(BlockSetType.class)
public interface BlockSetTypeInvoker {
    @Invoker
    static BlockSetType invokeRegister(BlockSetType value) {
        throw new UnsupportedOperationException();
    }
}
