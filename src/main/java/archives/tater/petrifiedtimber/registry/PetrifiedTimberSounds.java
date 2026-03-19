package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class PetrifiedTimberSounds {
    private static SoundEvent of(Identifier id) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    private static SoundEvent of(String path) {
        return of(PetrifiedTimber.id(path));
    }

    private static Holder.Reference<SoundEvent> holderOf(Identifier id) {
        return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    private static Holder.Reference<SoundEvent> holderOf(String path) {
        return holderOf(PetrifiedTimber.id(path));
    }

    public static final Holder.Reference<SoundEvent> MUSIC_BIOME_PETRIFIED_FOREST = holderOf("music.overworld.petrified_forest");

    public static void init() {}
}
