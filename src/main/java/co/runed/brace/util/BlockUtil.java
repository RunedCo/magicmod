package co.runed.brace.util;

import net.minecraft.block.BlockState;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtil {
    public static float calculateBlockBreakDelta(World world, BlockPos position, ToolMaterial toolMaterial) {
        BlockState state = world.getBlockState(position);

        float float_1 = state.getHardness(world, position);
        if (float_1 == -1.0F) {
            return 0.0F;
        } else {
            int int_1 = 30;
            return toolMaterial.getBlockBreakingSpeed() / float_1 / (float) int_1;
        }
    }
}
