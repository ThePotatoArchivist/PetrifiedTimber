package archives.tater.petrifiedtimber.mixin;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberWorldgen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import org.jspecify.annotations.Nullable;

@Mixin(TreeGrower.class)
public class TreeGrowerMixin {
    @SuppressWarnings("ConstantValue")
    @ModifyExpressionValue(
            method = "growTree",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/grower/TreeGrower;getConfiguredMegaFeature(Lnet/minecraft/util/RandomSource;)Lnet/minecraft/resources/ResourceKey;")
    )
    private @Nullable ResourceKey<ConfiguredFeature<?, ?>> skipMegaPetrifiedOak(@Nullable ResourceKey<ConfiguredFeature<?, ?>> original, ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos) {
        return (Object) this != PetrifiedTimberWorldgen.PETRIFIED_OAK_GROWER || level.getBiome(pos).is(PetrifiedTimberWorldgen.HAS_MEGA_PETRIFIED_OAK_TREE)
                ? original
                : null;
    }
}
