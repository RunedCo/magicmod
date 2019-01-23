package co.runed.magicmod.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;

public class SpellbookItem extends BaseItem {
    public SpellbookItem() {
        super(new Item.Settings().itemGroup(ItemGroup.TOOLS).stackSize(1));

        this.setRegistryName("spell_book");
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Iterator<Identifier> keys = context.getWorld().getRecipeManager().keys().iterator();

        while(keys.hasNext()) {
            System.out.println(keys.next());
        }

        return ActionResult.PASS;
    }
}
