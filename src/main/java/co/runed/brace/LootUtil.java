package co.runed.brace;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.Parameters;

import java.util.List;

public class LootUtil {
    public static List<ItemStack> getBlockDropsAt(World world, BlockPos pos, PlayerEntity player) {
        BlockState blockState = world.getBlockState(pos);

        LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld) world))
                .setRandom(world.random)
                .put(Parameters.BLOCK_STATE, blockState)
                .put(Parameters.POSITION, pos)
                .put(Parameters.TOOL, player.getActiveItem())
                .put(Parameters.THIS_ENTITY, player)
                .putNullable(Parameters.BLOCK_ENTITY, world.getBlockEntity(pos));

        return blockState.getDroppedStacks(lootContextBuilder);
    }
}
