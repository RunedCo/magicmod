package co.runed.magicmod.items;

import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellProperty;
import co.runed.magicmod.api.spell.components.BlockBreakSpellComponent;
import co.runed.magicmod.api.spell.components.BlockDropsToInventoryComponent;
import co.runed.magicmod.api.spell.components.TestSpellComponent;
import co.runed.magicmod.api.spell.components.VeinSpellComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WandItem extends BaseItem {
    private Spell spell = new Spell();

    public WandItem() {
        super(new Item.Settings().stackSize(1));

        this.setRegistryName("wand");

        this.spell
                .addProperty(SpellProperty.RANGE, 10.0D)
                .add(new VeinSpellComponent())
                //.add(new BlockDropsToInventoryComponent())
                .add(new BlockBreakSpellComponent());
    }

    @Override
    public boolean isEffectiveOn(BlockState blockState_1) {
        return true;
    }

    //TODO: fix item drops without drop tag not working
    //TODO: move to spell classes (spell manager, spell, and components?)
    //TODO: split into separate functions
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient()) {
            return ActionResult.PASS;
        }
        //if (this.vein == null) this.vein = new Vein(context.getWorld(), context.getBlockPos(), 3);

        PlayerEntity player = context.getPlayer();
        BlockPos position = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(position);
        Block block = blockState.getBlock();

        if(!this.spell.isBuilt() || !this.spell.getProperty(SpellProperty.INITIAL_BLOCK_POSITION).equals(position)) {
            this.spell
                    .setProperty(SpellProperty.WORLD, world)
                    .setProperty(SpellProperty.INITIAL_BLOCK_POSITION, position)
                    .setProperty(SpellProperty.ENTITY_CASTER, player);

            this.spell.build();
        }

        this.spell.run();

        //TODO: shorten
        /* ExtractionRecipe recipe = null;
        for (Recipe<?> r : MagicMod.RECIPE_LIBRARY.getAllOfType(MagicRecipeType.EXTRACTION)) {
            ExtractionRecipe extractionRecipe = (ExtractionRecipe) r;
            if (extractionRecipe.matches(block)) {
                recipe = extractionRecipe;

                break;
            }
        }

        if (recipe == null) return ActionResult.FAILURE; */

        /* BlockPos currentPosition = vein.getNext();

        if(block != this.vein.getBlockType() || currentPosition == null || !this.vein.getStartPosition().equals(position)) {
            this.vein = new Vein(world, position, 3);
            currentPosition = vein.getNext();
        }

        Block currentBlock = world.getBlockState(currentPosition).getBlock();

        world.breakBlock(currentPosition, true);

        this.vein.generateVein(currentPosition);
/*
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
        }

        if (recipe.getDrops().isEmpty() || recipe.shouldUseLootDrops()) {
            LootContext.Builder lootContextBuilder = (new LootContext.Builder((ServerWorld) world))
                    .setRandom(world.random)
                    .put(Parameters.BLOCK_STATE, breakState)
                    .put(Parameters.POSITION, this.currentBlock)
                    .put(Parameters.TOOL, player.getActiveItem())
                    .put(Parameters.THIS_ENTITY, player)
                    .putNullable(Parameters.BLOCK_ENTITY, world.getBlockEntity(this.currentBlock));


            if (recipe.shouldUseLootDrops()) {
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
*/
        return ActionResult.PASS;
    }


}

/* if (blockState.getBlock() == this.veinBlock && !this.blocksToBreak.contains(offsetPos)) {
                            if (!offsetPos.equals(startPos)) this.blocksToBreak.add(offsetPos);

                            Collections.shuffle(this.blocksToBreak);

                            //System.out.println("added " + offsetPos.toString() + " " + blockState.getBlock().getTranslationKey());
                        } */
