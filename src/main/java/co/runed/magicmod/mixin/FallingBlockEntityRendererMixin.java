package co.runed.magicmod.mixin;

import co.runed.magicmod.FallingRenderHotReload;
import net.minecraft.client.render.entity.FallingBlockEntityRenderer;
import net.minecraft.entity.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntityRenderer.class)
public class FallingBlockEntityRendererMixin {
    @Inject(method = "method_3965", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;popMatrix()V", shift = At.Shift.AFTER))
    public void onRender(FallingBlockEntity fallingBlockEntity_1, double x, double y, double z, float yaw, float ticks, CallbackInfo ci) {
        FallingRenderHotReload.render(fallingBlockEntity_1, x, y, z, yaw, ticks, ci);
    }
}
