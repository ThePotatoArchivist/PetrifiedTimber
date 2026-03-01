package archives.tater.petrifiedtimber.registry;

import archives.tater.petrifiedtimber.PetrifiedTimber;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;

import java.util.function.Consumer;

public class PetrifiedTimberEntities {

    private static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> type) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, PetrifiedTimber.id(path));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type.build(key));
    }

    private static <T extends Entity> EntityType<T> register(String path, EntityType.EntityFactory<T> factory, Consumer<? super EntityType.Builder<T>> init) {
        var builder = EntityType.Builder.of(factory, MobCategory.MISC);
        init.accept(builder);
        return register(path, builder);
    }

    private static final Consumer<EntityType.Builder<? extends AbstractBoat>> BOAT_TYPE = builder -> builder
            .noLootTable()
            .sized(1.375F, 0.5625F)
            .eyeHeight(0.5625F)
            .clientTrackingRange(10);

    public static final EntityType<Boat> PETRIFIED_OAK_BOAT = register(
            "petrified_oak_boat",
            (entityType, level) -> new Boat(entityType, level, () -> PetrifiedTimberItems.PETRIFIED_OAK_BOAT),
            BOAT_TYPE
    );

    public static final EntityType<ChestBoat> PETRIFIED_OAK_CHEST_BOAT = register(
            "petrified_oak_chest_boat",
            (entityType, level) -> new ChestBoat(entityType, level, () -> PetrifiedTimberItems.PETRIFIED_OAK_CHEST_BOAT),
            BOAT_TYPE
    );
}
