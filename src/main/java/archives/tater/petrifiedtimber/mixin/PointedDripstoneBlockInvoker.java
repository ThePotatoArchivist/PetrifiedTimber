package archives.tater.petrifiedtimber.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

@Mixin(PointedDripstoneBlock.class)
public interface PointedDripstoneBlockInvoker {
    @Invoker
    static Optional<PointedDripstoneBlock.FluidInfo> invokeGetFluidAboveStalactite(Level level, BlockPos pos, BlockState state) {
        throw new UnsupportedOperationException();
    }
}
