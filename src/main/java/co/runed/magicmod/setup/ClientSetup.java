package co.runed.magicmod.setup;

import co.runed.magicmod.client.render.entity.TestEntityRenderer;
import co.runed.magicmod.entity.TestEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.client.render.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class ClientSetup {
    public static void init() {
        EntityRendererRegistry.INSTANCE.register(TestEntity.class, (manager, context) -> new TestEntityRenderer(manager));
    }
}
