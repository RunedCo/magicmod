package co.runed.brace;

import co.runed.brace.util.MathUtil;
import com.sun.istack.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Vein {
    private final World world;
    private final BlockPos startPosition;
    private BlockPos currentPosition;

    private final Block blockType;

    private double maxDistance = 16.0D;

    private final List<BlockPos> blocksToBreak = new ArrayList<>();

    private final Random rand;

    public Vein(World world, BlockPos startPos) {
        this(world, startPos, 16.0D);
    }

    public Vein(World world, BlockPos startPos,  double maxDistance) {
        this.world = world;
        this.startPosition = startPos;
        this.blockType = world.getBlockState(startPos).getBlock();
        this.maxDistance = maxDistance;
        this.rand = new Random(startPosition.asLong());

        this.generateVeinAndAdd(this.startPosition);
    }

    public Block getBlockType() {
        return this.blockType;
    }

    public BlockPos getStartPosition() {
        return this.startPosition;
    }

    public BlockPos getNext() {
        if(this.blocksToBreak.isEmpty()) {
            return this.startPosition;
        }

        this.currentPosition = this.blocksToBreak.remove(0);

        return this.currentPosition;
    }

    public List<BlockPos> generateVein(BlockPos blockPos) {
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
                                MathUtil.maxDistanceAway(this.startPosition, offsetPos) <= this.maxDistance) {

                            blocks.add(offsetPos);
                        }
                    }
                }
            }
        }

        Collections.shuffle(blocks, this.rand);

        return blocks;
    }

    public List<BlockPos> generateVeinAndAdd(BlockPos pos) {
        List<BlockPos> vein = this.generateVein(pos);

        this.blocksToBreak.addAll(vein);

        return vein;
    }

    public List<BlockPos> generateFullVein(BlockPos startPos) {
        List<BlockPos> list = new ArrayList<>();

        list.add(startPos);
        list.addAll(this.iterateFullVein(startPos, null));

        return list;
    }

    private List<BlockPos> iterateFullVein(BlockPos position, @Nullable List<BlockPos> list) {
        List<BlockPos> previousPositions = list !=  null ? list : new ArrayList<>();
        List<BlockPos> generatedPositions = this.generateVein(position);

        for (BlockPos pos : generatedPositions) {
            if(!previousPositions.contains(pos)) {
                previousPositions.add(pos);

                this.iterateFullVein(pos, previousPositions);
            }
        }

        return previousPositions;
    }
}
