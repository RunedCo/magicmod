package co.runed.magicmod.setup;

import co.runed.magicmod.api.entity.MagicEntityType;
import co.runed.magicmod.client.render.entity.TestEntityRenderer;
import co.runed.magicmod.entity.TestEntity;
import co.runed.magicmod.network.packet.SymbolEntitySpawnClientPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.client.render.EntityRendererRegistry;
import net.fabricmc.fabric.entity.EntityTrackingRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class ClientSetup {
    public static void init() {
        EntityRendererRegistry.INSTANCE.register(TestEntity.class, (manager, context) -> new TestEntityRenderer(manager));

        //registerEntitySpawnPacket(MagicEntityType.TEST);
    }

    public static void registerEntitySpawnPacket(EntityType type) {
        //EntityTrackingRegistry.INSTANCE.registerSpawnPacketProvider(type, entity -> new SymbolEntitySpawnClientPacket(entity, Registry.ENTITY_TYPE.getRawId(type)));
    }
}
