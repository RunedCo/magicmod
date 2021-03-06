package co.runed.magicmod.mixin;

import co.runed.magicmod.OutlineRender;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TallPlantBlock.class)
public class TallPlantBlockMixin {
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, VerticalEntityPosition verticalEntityPosition) {
        return OutlineRender.getTallBlockOutline(VoxelShapes.fullCube(), blockState, blockView, blockPos, TallPlantBlock.HALF);
    }
}
