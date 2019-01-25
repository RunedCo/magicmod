package co.runed.brace.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ItemStackUtil {
    public static final ItemStack EMPTY = new ItemStack((Item) Items.AIR, 0);

    public static ItemStack getDefaultStack(Item item) {
        return new ItemStack(item);
    }
}
