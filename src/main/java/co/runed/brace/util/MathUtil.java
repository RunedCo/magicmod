package co.runed.brace.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class MathUtil {
    public static double maxDistanceAway(BlockPos pos1, BlockPos pos2) {
        return MathHelper.absMax(pos1.getX() - pos2.getX(), MathHelper.absMax(pos1.getY() - pos2.getY(), pos1.getZ() - pos2.getZ()));
    }
}
