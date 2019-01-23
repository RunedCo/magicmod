package co.runed.magicmod.items;

import co.runed.magicmod.api.recipes.MagicRecipeType;
import co.runed.magicmod.api.recipes.extraction.ExtractionRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.block.BlockItem;
import net.minecraft.recipe.Recipe;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.*;

import java.util.*;

public class WandItem extends BaseItem {
    private BlockPos startPos;
    private Block veinBlock;
    private List<BlockPos> blocksToBreak = new ArrayList<>();
    private BlockPos currentBlock;

    public WandItem() {
        super(new Item.Settings().stackSize(1));

        this.setRegistryName("wand");
    }

    @Override
    public boolean isEffectiveOn(BlockState state) {
        return true;
    }

    //TODO: move to spell classes (spell manager, spell, and components?)
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient()) return ActionResult.PASS;

        ServerPlayerEntity player = (ServerPlayerEntity) context.getPlayer();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(context.getPos());
        Block block = blockState.getBlock();

        //TODO: cache recipes
        ExtractionRecipe recipe = null;
        for (Recipe r : world.getRecipeManager().values()) {
            if(r.getType() == MagicRecipeType.EXTRACTION) {
                ExtractionRecipe exr = (ExtractionRecipe)r;

                System.out.println(exr.matches(block));

                if(exr.matches(block)) {
                    recipe = exr;

                    break;
                }
            }
        }

        if(recipe == null) return ActionResult.PASS;

        // vein component (should return blockPos)
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

        BlockItem blockItem = (BlockItem) recipe.getOutputBlock().getItem();
        BlockState breakState = world.getBlockState(this.currentBlock);

        if(recipe.shouldUseLootDrops()) {
            BlockItem bi = (BlockItem)recipe.getDrops().getItem();

            breakState = bi.getBlock().getDefaultState();
        }

        if(recipe.getDrops() == ItemStack.EMPTY || recipe.shouldUseLootDrops()) {
            LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld) world))
                    .setRandom(world.random)
                    .put(Parameters.BLOCK_STATE, breakState)
                    .put(Parameters.POSITION, this.currentBlock)
                    .put(Parameters.TOOL, player.getActiveItem())
                    .put(Parameters.THIS_ENTITY, player)
                    .putNullable(Parameters.BLOCK_ENTITY, world.getBlockEntity(this.currentBlock));


            //TODO: finish and tidy
            LootContextType lct = LootContextTypes.BLOCK;
            world.getServer().getLootManager()
                    .getSupplier(new Identifier(recipe.getLootTable()))
                    .drop(lootContextBuilder.build(lct), player.inventory::insertStack);

            for (ItemStack stack : breakState.getDroppedStacks(lootContextBuilder)) {
                player.inventory.insertStack(stack);
            }
        }

        player.inventory.insertStack(recipe.getDrops());

        blockState.getBlock().onStacksDropped(blockState, world, this.startPos, player.getActiveItem());

        world.breakBlock(this.currentBlock, false);
        world.setBlockState(this.currentBlock, blockItem.getBlock().getDefaultState());

        if (this.currentBlock.equals(this.startPos)) this.startPos = null;
        this.generateVein(world, this.currentBlock);
        this.currentBlock = null;

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
