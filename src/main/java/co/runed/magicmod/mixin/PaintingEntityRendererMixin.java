package co.runed.magicmod.mixin;

import co.runed.magicmod.OutlineRender;
import net.minecraft.client.render.entity.PaintingEntityRenderer;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PaintingEntityRenderer.class)
public class PaintingEntityRendererMixin {
    @Inject(method = "method_4075", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;popMatrix()V", shift = At.Shift.AFTER))
    public void onRender(PaintingEntity entity, double x, double y, double z, float yaw, float ticks, CallbackInfo ci) {
        if(OutlineRender.lookingAtEntity(entity)) {
            OutlineRender.renderEntityBoundingBox(entity, x, y, z);
        }
    }
}
