package co.runed.magicmod.client.render.entity;

import co.runed.magicmod.entity.TestEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.sortme.Living;
import net.minecraft.util.Identifier;

public class TestEntityRenderer<T extends Entity> extends EntityRenderer<T> {
    private static final Identifier SKIN = new Identifier("textures/entity/cow/cow.png");

    public TestEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
        super(entityRenderDispatcher_1);
    }

    @Override
    public void render(T entity_1, double double_1, double double_2, double double_3, float float_1, float float_2) {
        this.renderEntityLabel(entity_1, "test", double_1, double_2, double_3, 64);

        super.render(entity_1, double_1, double_2, double_3, float_1, float_2);
    }

    @Override
    protected Identifier getTexture(T var1) {
        return SKIN;
    }
}
