package co.runed.magicmod;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class FallingRenderHotReload {
    public static void render(FallingBlockEntity entity, double x, double y, double z, float yaw, float ticks, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        BlockState state = entity.getBlockState();
        BlockPos pos = entity.getFallingBlockPos();
        Entity cameraEntity = client.getCameraEntity();
        World world = cameraEntity.world;
        HitResult hitResult = client.hitResult;

        if(client.hitResult.getType() == HitResult.Type.ENTITY) {

            EntityHitResult entityHitResult = (EntityHitResult)hitResult;

            if(entityHitResult.getEntity() == entity) {
                GlStateManager.enableBlend();
                GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                GlStateManager.lineWidth(Math.max(2.5F, (float) client.window.getFramebufferWidth() / 1920.0F * 2.5F));
                GlStateManager.disableTexture();
                GlStateManager.depthMask(false);
                GlStateManager.matrixMode(5889);
                GlStateManager.pushMatrix();
                GlStateManager.scalef(1.0F, 1.0F, 0.999F);

                WorldRenderer.drawShapeOutline(state.getOutlineShape(world, pos, VerticalEntityPosition.fromEntity(cameraEntity)), x - 0.5, y, z - 0.5, 0.0F, 0.0F, 0.0F, 0.4F);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
                GlStateManager.depthMask(true);
                GlStateManager.enableTexture();
                GlStateManager.disableBlend();
            }
        }
    }
}
