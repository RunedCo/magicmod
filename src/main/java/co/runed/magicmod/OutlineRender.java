package co.runed.magicmod;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.ChestType;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class OutlineRender {
    public static boolean lookingAtEntity(Entity entity) {
        MinecraftClient client = MinecraftClient.getInstance();
        Entity cameraEntity = client.getCameraEntity();
        HitResult hitResult = client.hitResult;

        if(client.hitResult.getType() == HitResult.Type.ENTITY) {

            EntityHitResult entityHitResult = (EntityHitResult)hitResult;

            if(entityHitResult.getEntity() == entity) {
                return true;
            }
        }

        return false;
    }

    public static void renderVoxelShapeOutline(VoxelShape shape, double x, double y, double z) {
        MinecraftClient client = MinecraftClient.getInstance();

        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.lineWidth(Math.max(2.5F, (float) client.window.getFramebufferWidth() / 1920.0F * 2.5F));
        GlStateManager.disableTexture();
        GlStateManager.depthMask(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.scalef(1.0F, 1.0F, 0.999F);

        WorldRenderer.drawShapeOutline(shape, x, y, z, 0.0F, 0.0F, 0.0F, 0.4F);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture();
        GlStateManager.disableBlend();
    }

    public static void renderEntityBoundingBox(Entity entity, double x, double y, double z) {
        BoundingBox box = entity.getBoundingBox();

        double minX = box.minX - entity.x;
        double minY = box.minY - entity.y;
        double minZ = box.minZ - entity.z;

        double maxX = box.maxX - entity.x;
        double maxY = box.maxY - entity.y;
        double maxZ = box.maxZ - entity.z;

        VoxelShape shape = VoxelShapes.cube(minX, minY, minZ, maxX, maxY, maxZ);

        OutlineRender.renderVoxelShapeOutline(shape, x, y, z);
    }

    public static VoxelShape getTallBlockOutline(VoxelShape shape, BlockState blockState, BlockView blockView, BlockPos blockPos, EnumProperty<DoubleBlockHalf> halfProperty) {
        if (!checkHitSameBlock(shape, blockPos)) return shape;

        DoubleBlockHalf half = blockState.get(halfProperty);
        Vec3i offset = half == DoubleBlockHalf.LOWER ? new Vec3i(0, 1, 0) : new Vec3i(0, -1, 0);
        BlockPos otherHalfPos = blockPos.add(offset);
        BlockState otherHalf = blockView.getBlockState(otherHalfPos);

        if(otherHalf.getBlock() == blockState.getBlock()) {
            if(otherHalf.get(halfProperty) != half) {
                shape = VoxelShapes.union(shape, otherHalf.getOutlineShape(blockView, otherHalfPos).offset(offset.getX(), offset.getY(), offset.getZ()));
            }
        }

        return shape;
    }

    public static VoxelShape getLongBlockOutline(VoxelShape shape, BlockState blockState, BlockView blockView, BlockPos blockPos, Direction direction) {
        if (!checkHitSameBlock(shape, blockPos)) return shape;

        LongBlockSide side = LongBlockSide.fromBlockState(blockState);
        Vec3i offset = side == LongBlockSide.RIGHT ? direction.getVector() : new Vec3i(-direction.getOffsetX(), -direction.getOffsetY(), -direction.getOffsetZ());

        BlockPos otherHalfPos = blockPos.add(offset);
        BlockState otherHalf = blockView.getBlockState(otherHalfPos);

        if(otherHalf.getBlock() == blockState.getBlock()) {
            if(LongBlockSide.fromBlockState(otherHalf) != side) {
                shape = VoxelShapes.union(shape, otherHalf.getOutlineShape(blockView, otherHalfPos).offset(offset.getX(), offset.getY(), offset.getZ()));
            }
        }

        return shape;
    }

    private static boolean checkHitSameBlock(VoxelShape shape, BlockPos blockPos) {
        HitResult hitResult = MinecraftClient.getInstance().hitResult;

        if(hitResult != null && hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult)hitResult;

            if(blockHitResult.getBlockPos().equals(blockPos)) {
                return true;
            }
        }
        return false;
    }

    public enum LongBlockSide {
        LEFT,
        RIGHT,
        NONE;

        public static LongBlockSide fromBlockState(BlockState state) {
            if(state.getBlock() instanceof ChestBlock) {
                return chestTypeToLBS(state.get(ChestBlock.CHEST_TYPE));
            }

            if(state.getBlock() instanceof BedBlock) {
                return bedPartToLBS(state.get(BedBlock.PART));
            }

            return LongBlockSide.NONE;
        }

        private static LongBlockSide chestTypeToLBS(ChestType chestType) {
            switch (chestType) {
                case LEFT:
                    return LongBlockSide.LEFT;
                case RIGHT:
                    return LongBlockSide.RIGHT;
                default:
                    return LongBlockSide.NONE;
            }
        }

        private static LongBlockSide bedPartToLBS(BedPart bedPart) {
            switch (bedPart) {
                case HEAD:
                    return LongBlockSide.LEFT;
                case FOOT:
                    return LongBlockSide.RIGHT;
                default:
                    return LongBlockSide.NONE;
            }
        }
    }
}
