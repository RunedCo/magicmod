package co.runed.magicmod.setup;

import co.runed.magicmod.api.entity.MagicEntityType;
import co.runed.magicmod.entity.TestEntity;
import net.fabricmc.fabric.entity.EntityTrackingRegistry;
import net.fabricmc.fabric.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

public class EntitySetup {
    public static void init() {
        //TODO: ADD REGISTRY UTIL FOR THIS
        MagicEntityType.TEST = Registry.register(Registry.ENTITY_TYPE, "magicmod:test", FabricEntityTypeBuilder.create(EntityCategory.MISC, TestEntity::new).trackable(64, 1, true).build());

    }


    public static <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> builder) {
        return Registry.register(Registry.ENTITY_TYPE, (String)name, builder.build());
    }
}
