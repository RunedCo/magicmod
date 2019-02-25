package co.runed.magicmod.item;

import co.runed.magicmod.api.SpellManager;
import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellEffects;
import co.runed.magicmod.api.spell.property.SpellProperties;
import co.runed.magicmod.client.gui.TestSpellScreen;
import co.runed.magicmod.network.packet.s2c.SyncSpellS2CPacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.StringTextComponent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class WandItem extends BaseItem {
    public WandItem() {
        super(new Item.Settings().stackSize(1));

        this.setRegistryName("wand");

        /* spell = new Spell();

        spell
                .putProperty(SpellProperties.RANGE, 10.0D)
                .putEffect(new VeinSpellEffect())
                .putEffect(new BlockDropsToInventoryEffect())
                .putEffect(new BlockBreakSpellEffect()); */
    }

    @Override
    public boolean isEffectiveOn(BlockState blockState_1) {
        return true;
    }

    //TODO: fix item drops without drop tag not working
    //TODO: split into separate functions
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient()) {
            //MinecraftClient.getInstance().openScreen(new TestSpellScreen());

            return ActionResult.PASS;
        }
        //if (this.vein == null) this.vein = new Vein(context.getWorld(), context.getBlockPos(), 3);

        //System.out.println(context.getWorld());

        ServerPlayerEntity player = (ServerPlayerEntity) context.getPlayer();
        BlockPos position = context.getBlockPos();
        World world = context.getWorld();
        BlockState blockState = world.getBlockState(position);
        Block block = blockState.getBlock();

        Spell spell = SpellManager.getActiveSpell(player);

        if (spell == null) {
            spell = new Spell()
                    .setCastType(Spell.CastType.USE_BLOCK)
                    .putProperty(SpellProperties.RANGE, 10.0D)
                    .putEffect(SpellEffects.VEIN_AREA.create())
                    .putEffect(SpellEffects.BREAK_BLOCK.create().setTier(3))
                    .putEffect(SpellEffects.ITEM_TO_INVENTORY.create());

            SpellManager.setActiveSpell(player, spell);
        }

        spell.putProperty(SpellProperties.WORLD, world);

        if (!spell.isBuilt() || !spell.getProperty(SpellProperties.START_POSITION).equals(position)) {
            ArrayList<BlockPos> posArrayList = new ArrayList<>();
            posArrayList.add(position);

            spell
                    .putProperty(SpellProperties.START_POSITION, position)
                    .putProperty(SpellProperties.BLOCK_POSITIONS, posArrayList)
                    .putProperty(SpellProperties.CASTER, player);

            spell.build();
        }

        player.addChatMessage(new StringTextComponent("Cost: " + spell.getManaCost()), true);

        if (spell.getCastType() == Spell.CastType.USE_BLOCK) {
            player.networkHandler.sendPacket(new SyncSpellS2CPacket(spell));

            spell.run();
        }

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
        return ActionResult.SUCCESS;
    }
}

/* if (blockState.getBlock() == this.veinBlock && !this.blocksToBreak.contains(offsetPos)) {
                            if (!offsetPos.equals(startPos)) this.blocksToBreak.putEffect(offsetPos);

                            Collections.shuffle(this.blocksToBreak);

                            //System.out.println("added " + offsetPos.toString() + " " + blockState.getBlock().getTranslationKey());
                        } */
