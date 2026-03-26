package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.block.ResinCauldronBlock;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberItems;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class WorldInteractionGenerator extends FabricCodecDataProvider<WorldInteractionGenerator.WorldInteractionRecipe> {

    public WorldInteractionGenerator(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, registriesFuture, PackOutput.Target.RESOURCE_PACK, "rrv/recipe", WorldInteractionRecipe.CODEC);
    }

    public record WorldInteractionRecipe(Ingredient left, Ingredient right, Ingredient result) {

        private WorldInteractionRecipe(Object ignored, Ingredient left, Ingredient right, Ingredient result) {
            this(left, right, result);
        }

        public static final Codec<WorldInteractionRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.STRING.fieldOf("type").forGetter(ignored -> "rrv:world_interaction"),
                Ingredient.CODEC.fieldOf("left").forGetter(WorldInteractionRecipe::left),
                Ingredient.CODEC.fieldOf("right").forGetter(WorldInteractionRecipe::right),
                Ingredient.CODEC.fieldOf("result").forGetter(WorldInteractionRecipe::result)
        ).apply(instance, WorldInteractionRecipe::new));

    }

    @Override
    protected void configure(BiConsumer<Identifier, WorldInteractionRecipe> provider, HolderLookup.Provider lookup) {
        var cauldron = Ingredient.of(PetrifiedTimberItems.MELTED_RESIN_CAULDRON);

        ResinCauldronBlock.RESIN_COATING.forEach((input, result) ->
                provider.accept(BuiltInRegistries.ITEM.getKey(result), new WorldInteractionRecipe(
                        Ingredient.of(input),
                        cauldron,
                        Ingredient.of(result)
                )));
    }

    @Override
    public String getName() {
        return "RRV Recipes";
    }
}
