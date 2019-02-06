package co.runed.magicmod.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LecternBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;

public class SpellbookItem extends BaseItem {
    public SpellbookItem() {
        super(new Item.Settings().itemGroup(ItemGroup.TOOLS).stackSize(1).rarity(Rarity.EPIC));

        this.setRegistryName("spell_book");
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world_1 = context.getWorld();
        BlockPos blockPos_1 = context.getBlockPos();
        BlockState blockState_1 = world_1.getBlockState(blockPos_1);
        if (blockState_1.getBlock() == Blocks.LECTERN) {
            return LecternBlock.putBookIfAbsent(world_1, blockPos_1, blockState_1, context.getItemStack()) ? ActionResult.SUCCESS : ActionResult.PASS;
        } else {
            return ActionResult.PASS;
        }
    }
}
