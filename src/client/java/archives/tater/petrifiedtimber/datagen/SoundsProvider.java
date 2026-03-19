package archives.tater.petrifiedtimber.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundEventRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.FloatProvider;

import org.jspecify.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class SoundsProvider extends FabricCodecDataProvider<Map<String, SoundEventRegistration>> {
    public static final FloatProvider ONE = ConstantFloat.of(1);

    @SuppressWarnings("DataFlowIssue")
    public static final Codec<Sound.Type> SOUND_TYPE_CODEC = Codec.stringResolver(Sound.Type::name, Sound.Type::getByName);

    public static final Codec<Sound> SOUND_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("name").forGetter(Sound::getLocation),
            FloatProvider.codec(0, Float.MAX_VALUE).optionalFieldOf("volume", ONE).forGetter(sound -> (FloatProvider) sound.getVolume()),
            FloatProvider.codec(0, Float.MAX_VALUE).optionalFieldOf("pitch", ONE).forGetter(sound -> (FloatProvider) sound.getPitch()),
            ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("weight", 1).forGetter(Sound::getWeight),
            SOUND_TYPE_CODEC.optionalFieldOf("type", Sound.Type.FILE).forGetter(Sound::getType),
            Codec.BOOL.optionalFieldOf("stream", false).forGetter(Sound::shouldStream),
            Codec.BOOL.optionalFieldOf("preload", false).forGetter(Sound::shouldPreload),
            ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("attenuation_distance", 16).forGetter(Sound::getAttenuationDistance)
    ).apply(instance, Sound::new));

    public static final Codec<Sound> SHORT_SOUND_CODEC = Codec.either(SOUND_CODEC, Identifier.CODEC).xmap(
            either -> either.map(Function.identity(), id -> new Sound(id, ONE, ONE, 1, Sound.Type.FILE, false, false, 16)),
            sound -> isDefault(sound) ? Either.right(sound.getLocation()) : Either.left(sound)
    );

    private static boolean isDefault(Sound sound) {
        return sound.getVolume().equals(ONE) &&
                sound.getPitch().equals(ONE) &&
                sound.getWeight() == 1 &&
                sound.getType() == Sound.Type.FILE &&
                !sound.shouldStream() &&
                !sound.shouldPreload() &&
                sound.getAttenuationDistance() == 16;
    }

    public static final Codec<SoundEventRegistration> SOUND_EVENT_REGISTRATION_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            SHORT_SOUND_CODEC.listOf().fieldOf("sounds").forGetter(SoundEventRegistration::getSounds),
            Codec.BOOL.optionalFieldOf("replace", false).forGetter(SoundEventRegistration::isReplace),
            Codec.STRING.optionalFieldOf("subtitle").forGetter(registration -> Optional.ofNullable(registration.getSubtitle()))
    ).apply(instance, (sounds, replace, subtitle) -> new SoundEventRegistration(sounds, replace, subtitle.orElse(null))));

    public static final Codec<Map<String, SoundEventRegistration>> CODEC = Codec.unboundedMap(
            Codec.STRING,
            SOUND_EVENT_REGISTRATION_CODEC
    );

    public SoundsProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, registriesFuture, PackOutput.Target.RESOURCE_PACK, "", CODEC);
    }

    @Override
    protected void configure(BiConsumer<Identifier, Map<String, SoundEventRegistration>> provider, HolderLookup.Provider lookup) {
        var namespaces = new HashMap<String, Map<String, SoundEventRegistration>>();
        addSounds((event, soundEventRegistration) ->
                namespaces.computeIfAbsent(event.getNamespace(), _key -> new HashMap<>())
                        .put(event.getPath(), soundEventRegistration));

        namespaces.forEach((namespace, events) ->
            provider.accept(Identifier.fromNamespaceAndPath(namespace, "sounds"), events)
        );
    }

    @FunctionalInterface
    public interface SoundsBuilder {
        void add(Identifier event, SoundEventRegistration soundEventRegistration);

        default void add(Identifier event, List<Sound> sounds, @Nullable String subtitle) {
            add(event, new SoundEventRegistration(sounds, false, subtitle));
        }

        default void add(Holder<SoundEvent> event, List<Sound> sounds, @Nullable String subtitle) {
            add(event.unwrapKey().orElseThrow().identifier(), sounds, subtitle);
        }

        default void add(SoundEvent event, List<Sound> sounds, @Nullable String subtitle) {
            add(BuiltInRegistries.SOUND_EVENT.wrapAsHolder(event), sounds, subtitle);
        }

        default void add(Identifier event, List<Sound> sounds) {
            add(event, sounds, null);
        }

        default void add(Holder<SoundEvent> event, List<Sound> sounds) {
            add(event, sounds, null);
        }

        default void add(SoundEvent event, List<Sound> sounds) {
            add(event, sounds, null);
        }
    }

    protected abstract void addSounds(SoundsBuilder soundsBuilder);

    @Override
    public String getName() {
        return "Sounds";
    }
}
