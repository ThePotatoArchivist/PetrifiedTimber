package archives.tater.petrifiedtimber.datagen;

import archives.tater.petrifiedtimber.PetrifiedTimber;
import archives.tater.petrifiedtimber.registry.PetrifiedTimberSounds;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.util.valueproviders.ConstantFloat;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static net.minecraft.util.Util.makeDescriptionId;

public class SoundsGenerator extends SoundsProvider {
    public SoundsGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, registriesFuture);
    }

    public static final String WOOD_PETRIFIES_SUBTITLE = makeDescriptionId("subtitles.block", PetrifiedTimber.id("wood_petrifies"));
    public static final String DIP_RESIN_SUBTITLE = makeDescriptionId("subtitles.block", PetrifiedTimber.id("resin_cauldron.dip_resin"));
    public static final String RESIN_DRIP_SUBTITLE = makeDescriptionId("subtitles.block", PetrifiedTimber.id("resin_cauldron.resin_drip"));

    private static final Identifier STONE_DOOR_OPEN = PetrifiedTimber.id("stone_door_open");
    private static final Identifier STONE_DOOR_CLOSE = PetrifiedTimber.id("stone_door_close");
    private static final Identifier STONE_TRAPDOOR_OPEN = PetrifiedTimber.id("stone_trapdoor_open");
    private static final Identifier STONE_TRAPDOOR_CLOSE = PetrifiedTimber.id("stone_trapdoor_close");

    private static Sound soundStreamed(String path) {
        return new Sound(Identifier.withDefaultNamespace(path), ONE, ONE, 1, Sound.Type.FILE, true, false, DEFAULT_ATTENUATION);
    }

    private static Sound sound(Identifier file, float volume, float pitch) {
        return new Sound(file, ConstantFloat.of(volume), ConstantFloat.of(pitch), 1, Sound.Type.FILE, false, false, DEFAULT_ATTENUATION);
    }

    private static Stream<Sound> soundsVolume(Identifier file, float volume, float... pitches) {
        return IntStream.range(0, pitches.length).mapToObj(pitchIndex ->
                sound(file, volume, pitches[pitchIndex])
        );
    }

    private static Stream<Sound> sounds(Identifier file, float... pitches) {
        return soundsVolume(file, 1f, pitches);
    }

    private static Sound sound(Identifier file) {
        return sound(file, 1f, 1f);
    }

    @Override
    protected void addSounds(SoundsBuilder soundsBuilder) {
        soundsBuilder.add(PetrifiedTimberSounds.MUSIC_BIOME_PETRIFIED_FOREST, List.of(
                soundStreamed("music/game/minecraft"),
                soundStreamed("music/game/clark"),
                soundStreamed("music/game/sweden")
        ));

        soundsBuilder.add(PetrifiedTimberSounds.STONE_DOOR_OPEN,        sounds(STONE_DOOR_OPEN,  1f).toList(), "subtitles.block.door.toggle");
        soundsBuilder.add(PetrifiedTimberSounds.STONE_DOOR_CLOSE,       sounds(STONE_DOOR_CLOSE, 1f).toList(), "subtitles.block.door.toggle");
        soundsBuilder.add(PetrifiedTimberSounds.STONE_TRAPDOOR_OPEN,    sounds(STONE_DOOR_OPEN,  1.2f).toList(), "subtitles.block.trapdoor.open");
        soundsBuilder.add(PetrifiedTimberSounds.STONE_TRAPDOOR_CLOSE,   sounds(STONE_DOOR_CLOSE, 1.2f).toList(), "subtitles.block.trapdoor.close");
        soundsBuilder.add(PetrifiedTimberSounds.STONE_FENCE_GATE_OPEN,  sounds(STONE_DOOR_OPEN,  1.1f).toList(), "subtitles.block.fence_gate.toggle");
        soundsBuilder.add(PetrifiedTimberSounds.STONE_FENCE_GATE_CLOSE, sounds(STONE_DOOR_CLOSE, 1.1f).toList(), "subtitles.block.fence_gate.toggle");

        soundsBuilder.add(PetrifiedTimberSounds.WOOD_PETRIFIES, IntStream.rangeClosed(1, 4).mapToObj(index ->
                sound(Identifier.withDefaultNamespace("block/composter/ready" + index))
        ).toList(), WOOD_PETRIFIES_SUBTITLE);

        soundsBuilder.add(PetrifiedTimberSounds.DIP_RESIN, IntStream.rangeClosed(1, 3).mapToObj(index ->
                sounds(Identifier.withDefaultNamespace("item/honeycomb/wax_on" + index), 0.9f, 1f, 1.1f)
        ).flatMap(Function.identity()).toList(), DIP_RESIN_SUBTITLE);

        soundsBuilder.add(PetrifiedTimberSounds.RESIN_DRIP, IntStream.rangeClosed(1, 6).mapToObj(index ->
                soundsVolume(Identifier.withDefaultNamespace("block/beehive/drip" + index), 0.3f, 0.7f, 0.9f)
        ).flatMap(Function.identity()).toList(), RESIN_DRIP_SUBTITLE);
    }
}
