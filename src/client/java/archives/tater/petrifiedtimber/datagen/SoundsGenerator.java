package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.registry.PetrifiedTimberSounds;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SoundsGenerator extends SoundsProvider {
    public SoundsGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, registriesFuture);
    }

    private static Sound soundStreamed(String path) {
        return new Sound(Identifier.withDefaultNamespace(path), ONE, ONE, 1, Sound.Type.FILE, true, false, 16);
    }

    @Override
    protected void addSounds(SoundsBuilder soundsBuilder) {
        soundsBuilder.add(PetrifiedTimberSounds.MUSIC_BIOME_PETRIFIED_FOREST, List.of(
                soundStreamed("music/game/minecraft"),
                soundStreamed("music/game/clark"),
                soundStreamed("music/game/sweden")
        ));
    }
}
