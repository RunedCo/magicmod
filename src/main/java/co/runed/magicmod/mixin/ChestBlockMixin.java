package co.runed.magicmod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.enums.ChestType;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBlock.class)
public class ChestBlockMixin {
    @Inject(method = "getOutlineShape", at = @At(value = "RETURN"), cancellable = true)
    public void onGetOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, VerticalEntityPosition verticalEntityPosition, CallbackInfoReturnable<VoxelShape> ci) {
        VoxelShape shape = ci.getReturnValue();
        ChestType half = blockState.get(ChestBlock.CHEST_TYPE);
        Direction direction = blockState.get(ChestBlock.FACING).rotateYCounterclockwise();
        Vec3i offset = half == ChestType.RIGHT ? direction.getVector() : new Vec3i(-direction.getOffsetX(), -direction.getOffsetY(), -direction.getOffsetZ());

        BlockPos otherHalfPos = blockPos.add(offset);
        BlockState otherHalf = blockView.getBlockState(otherHalfPos);

        if(otherHalf.getBlock() == blockState.getBlock()) {
            if(otherHalf.get(ChestBlock.CHEST_TYPE) != half) {
                shape = VoxelShapes.union(shape, blockState.getOutlineShape(blockView, otherHalfPos).offset(offset.getX() * 0.9375, offset.getY() * 0.9375, offset.getZ() * 0.9375));
            }
        }

        ci.setReturnValue(shape);
    }
}
