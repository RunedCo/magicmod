package co.runed.magicmod.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

public class WandItem extends BaseItem {
    private BlockPos startPos;
    private Block veinBlock;
    private List<BlockPos> blocksToBreak = new ArrayList<>();

    private float currentBreakingProgress = 0.0f;
    private BlockPos currentBlock;


    //TODO: rename temp variables
    private int field_14000;
    private int field_14009;
    private int field_14010;

    public WandItem() {
        super(new Item.Settings().stackSize(1));

        this.setRegistryName("wand");
    }

    @Override
    public boolean isEffectiveOn(BlockState state) {
        return true;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient()) return ActionResult.PASS;

        ServerPlayerEntity player = (ServerPlayerEntity) context.getPlayer();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(context.getPos());
        Block block = blockState.getBlock();

        if (block != this.veinBlock || this.startPos == null || !this.startPos.equals(context.getPos())) {
            blocksToBreak.clear();
            this.veinBlock = block;
            this.startPos = context.getPos();
            this.currentBlock = null;

            this.generateVein(world, context.getPos());
        }

        if (this.currentBlock == null) {
            if (this.blocksToBreak.isEmpty()) {
                this.currentBlock = this.startPos;
            } else {
                this.currentBlock = blocksToBreak.remove(0);
            }
        }

        //BlockState breakState = world.getBlockState(this.currentBlock);
        //int int_4;

        //player.interactionManager.tryBreakBlock(this.currentBlock);

//        context.getWorld().breakBlock(this.currentBlock, true);
//        if (this.currentBlock.equals(this.startPos)) this.startPos = null;
//        this.generateVein(world, this.currentBlock);
//        this.currentBlock = null;

        /* int int_1 = this.field_14000 - this.field_14010;
        this.currentBreakingProgress = breakState.calcBlockBreakingDelta(player, world, this.currentBlock) * (float) (int_1 + 1);
        int_4 = (int) (this.currentBreakingProgress * 10.0F);
        if (int_4 != this.field_14009) {
            world.setBlockBreakingProgress(player.getEntityId(), this.currentBlock, int_4);
            this.field_14009 = int_4;
        } */

        //player.interactionManager.interactBlock(player, )


        //if (this.currentBreakingProgress >= 1.0F) {
            world.breakBlock(this.currentBlock, true);
            if (this.currentBlock.equals(this.startPos)) this.startPos = null;
            this.generateVein(world, this.currentBlock);
            this.currentBlock = null;
        //}

        return ActionResult.PASS;
    }

    private void generateVein(World world, BlockPos blockPos) {
        List<BlockPos> blocks = new ArrayList<>();

        int[] dimRange = {-1, 0, 1};

        for (int dx : dimRange) {
            for (int dy : dimRange) {
                for (int dz : dimRange) {
                    if ((dx != 0) || (dy != 0) || (dz != 0)) {
                        BlockPos offsetPos = new BlockPos(blockPos.getX() + dx, blockPos.getY() + dy, blockPos.getZ() + dz);

                        Block block = world.getBlockState(offsetPos).getBlock();

                        if (!offsetPos.equals(this.startPos) &&
                                block.equals(this.veinBlock) &&
                                !this.blocksToBreak.contains(offsetPos) &&
                                this.startPos.distanceTo(offsetPos) <= 6.0D) {
                            //System.out.println(block.getTranslationKey() + " " + offsetPos);

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

/* if (blockState.getBlock() == this.veinBlock && !this.blocksToBreak.contains(offsetPos)) {
                            if (!offsetPos.equals(startPos)) this.blocksToBreak.add(offsetPos);

                            Collections.shuffle(this.blocksToBreak);

                            //System.out.println("added " + offsetPos.toString() + " " + blockState.getBlock().getTranslationKey());
                        } */
