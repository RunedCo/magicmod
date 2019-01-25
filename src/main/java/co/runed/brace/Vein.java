package co.runed.brace;

import com.sun.istack.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vein {
    private World world;
    private BlockPos startPosition;
    private BlockPos currentPosition;

    private Block blockType;

    private double maxDistance = 16.0D;

    private List<BlockPos> blocksToBreak = new ArrayList<>();

    public Vein(World world, BlockPos startPos) {
        this(world, startPos, 16.0D);
    }

    public Vein(World world, BlockPos startPos, double maxDistance) {
        this.world = world;
        this.startPosition = startPos;
        this.blockType = world.getBlockState(startPos).getBlock();
        this.maxDistance = maxDistance;
    }

    public BlockPos getStartPosition() {
        return startPosition;
    }

    public BlockPos getNext() {
        return this.currentPosition;
    }

    private void generateVein(BlockPos blockPos) {
        List<BlockPos> blocks = new ArrayList<>();

        int[] offsets = {-1, 0, 1};

        for (int dx : offsets) {
            for (int dy : offsets) {
                for (int dz : offsets) {
                    if ((dx != 0) || (dy != 0) || (dz != 0)) {
                        BlockPos offsetPos = new BlockPos(blockPos.getX() + dx, blockPos.getY() + dy, blockPos.getZ() + dz);

                        Block block = world.getBlockState(offsetPos).getBlock();

                        if (!offsetPos.equals(this.startPosition) &&
                                block.equals(this.blockType) &&
                                !this.blocksToBreak.contains(offsetPos) &&
                                this.startPosition.distanceTo(offsetPos) <= this.maxDistance) {

                            blocks.add(offsetPos);
                        }
                    }
                }
            }
        }

        Collections.shuffle(blocks);

        this.blocksToBreak.addAll(blocks);
    }
}