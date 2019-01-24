package co.runed.magicmod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.Iterator;

public class SpellbookItem extends BaseItem {
    public SpellbookItem() {
        super(new Item.Settings().itemGroup(ItemGroup.TOOLS).stackSize(1).rarity(Rarity.EPIC));

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
