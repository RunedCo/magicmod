package co.runed.magicmod.items;

import co.runed.brace.Vein;
import co.runed.magicmod.api.recipes.MagicRecipeType;
import co.runed.magicmod.recipes.extraction.ExtractionRecipe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.block.BlockItem;
import net.minecraft.recipe.Recipe;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.LootContext;
import net.minecraft.world.loot.context.LootContextType;
import net.minecraft.world.loot.context.LootContextTypes;
import net.minecraft.world.loot.context.Parameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WandItem extends BaseItem {
    private BlockPos startPos;
    private Block veinBlock;
    private BlockPos currentBlock;

    private Vein vein;

    public WandItem() {
        super(new Item.Settings().stackSize(1));

        this.setRegistryName("wand");
    }

    //TODO: fix item drops without drop tag not working
    //TODO: move to spell classes (spell manager, spell, and components?)
    //TODO: split into separate functions
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

        /* if(recipe.shouldUseLootDrops()) {
            BlockItem bi = (BlockItem)recipe.getDrops().getItem();

            breakState = bi.getBlock().getDefaultState();
        } */

        if(recipe.getDrops().isEmpty() || recipe.shouldUseLootDrops()) {
            LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld) world))
                    .setRandom(world.random)
                    .put(Parameters.BLOCK_STATE, breakState)
                    .put(Parameters.POSITION, this.currentBlock)
                    .put(Parameters.TOOL, player.getActiveItem())
                    .put(Parameters.THIS_ENTITY, player)
                    .putNullable(Parameters.BLOCK_ENTITY, world.getBlockEntity(this.currentBlock));


            if(recipe.shouldUseLootDrops()) {
                //TODO: finish and tidy

                LootContextType lct = LootContextTypes.BLOCK;
                world.getServer().getLootManager()
                        .getSupplier(new Identifier(recipe.getLootTable()))
                        .drop(lootContextBuilder.build(lct), player.inventory::insertStack);
            }

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


}

/* if (blockState.getBlock() == this.veinBlock && !this.blocksToBreak.contains(offsetPos)) {
                            if (!offsetPos.equals(startPos)) this.blocksToBreak.add(offsetPos);

                            Collections.shuffle(this.blocksToBreak);

                            //System.out.println("added " + offsetPos.toString() + " " + blockState.getBlock().getTranslationKey());
                        } */
