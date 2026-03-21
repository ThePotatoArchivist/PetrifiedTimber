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

    public static final SoundEvent STONE_DOOR_OPEN = of("block.stone_door.open");
    public static final SoundEvent STONE_DOOR_CLOSE = of("block.stone_door.close");
    public static final SoundEvent STONE_TRAPDOOR_OPEN = of("block.stone_trapdoor.open");
    public static final SoundEvent STONE_TRAPDOOR_CLOSE = of("block.stone_trapdoor.close");
    public static final SoundEvent STONE_FENCE_GATE_OPEN = of("block.stone_fence_gate.open");
    public static final SoundEvent STONE_FENCE_GATE_CLOSE = of("block.stone_fence_gate.close");

    public static final SoundEvent WOOD_PETRIFIES = of("block.wood_petrifies");
    public static final SoundEvent DIP_RESIN = of("block.resin_cauldron.dip_resin");
    public static final SoundEvent RESIN_DRIP = of("block.resin_cauldron.resin_drip");

    public static void init() {}
}
