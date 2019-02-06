package co.runed.magicmod.mixin;

import co.runed.magicmod.OutlineRender;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FallingBlockEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntityRenderer.class)
public class FallingBlockEntityRendererMixin {
    @Inject(method = "method_3965", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;popMatrix()V", shift = At.Shift.AFTER))
    public void onRender(FallingBlockEntity fallingBlockEntity_1, double x, double y, double z, float yaw, float ticks, CallbackInfo ci) {
        if(OutlineRender.lookingAtEntity(fallingBlockEntity_1)) {
            World world = fallingBlockEntity_1.world;
            BlockState state = fallingBlockEntity_1.getBlockState();
            BlockPos pos = fallingBlockEntity_1.getFallingBlockPos();
            Entity cameraEntity = MinecraftClient.getInstance().getCameraEntity();

            OutlineRender.renderVoxelShapeOutline(state.getOutlineShape(world, pos, VerticalEntityPosition.fromEntity(cameraEntity)), x - 0.5, y, z - 0.5);
        }
    }
}
