package co.runed.brace.world;

import co.runed.brace.util.MathUtil;
import com.sun.istack.internal.Nullable;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VeinBlockArea extends BlockArea {
    private final World world;
    private final BlockPos startPosition;

    private final Block blockType;

    private double range;

    private final List<BlockPos> blockPositionList = new ArrayList<>();

    private final Random random;

    public VeinBlockArea(World world, BlockPos startPos, double range) {
        this.world = world;
        this.startPosition = startPos;
        this.blockType = world.getBlockState(startPos).getBlock();
        this.range = range;
        this.random = new Random(startPosition.asLong());

        this.generateAndAdd(this.startPosition);
    }

    public Block getBlockType() {
        return this.blockType;
    }

    public BlockPos getStart() {
        return this.startPosition;
    }

    public BlockPos getNext() {
        if(this.blockPositionList.isEmpty()) {
            return this.startPosition;
        }

        return this.blockPositionList.remove(0);
    }

    public List<BlockPos> generate(BlockPos blockPos) {
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
                                !this.blockPositionList.contains(offsetPos) &&
                                MathUtil.maxDistanceAway(this.startPosition, offsetPos) <= this.range) {

                            blocks.add(offsetPos);
                        }
                    }
                }
            }
        }

        Collections.shuffle(blocks, this.random);

        return blocks;
    }

    public List<BlockPos> generateAndAdd(BlockPos pos) {
        List<BlockPos> vein = this.generate(pos);

        this.blockPositionList.addAll(vein);

        return vein;
    }

    public List<BlockPos> generateFull(BlockPos startPos) {
        List<BlockPos> list = new ArrayList<>();

        list.add(startPos);
        list.addAll(this.iterateFull(startPos, null));

        return list;
    }

    private List<BlockPos> iterateFull(BlockPos position, @Nullable List<BlockPos> list) {
        List<BlockPos> previousPositions = list !=  null ? list : new ArrayList<>();
        List<BlockPos> generatedPositions = this.generate(position);

        for (BlockPos pos : generatedPositions) {
            if(!previousPositions.contains(pos)) {
                previousPositions.add(pos);

                this.iterateFull(pos, previousPositions);
            }
        }

        return previousPositions;
    }
}
