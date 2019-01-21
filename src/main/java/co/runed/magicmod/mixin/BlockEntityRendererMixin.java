package co.runed.magicmod.mixin;

import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockEntityRenderer.class)
public class BlockEntityRendererMixin {

    @Shadow
    public void bindTexture(Identifier identifier) {}
}
