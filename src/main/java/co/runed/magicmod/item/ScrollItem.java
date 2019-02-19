package co.runed.magicmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ScrollItem extends BaseItem {
    public static final Item.Settings DEFAULT_SETTINGS = new Item.Settings().itemGroup(ItemGroup.BREWING);

    public ScrollItem() {
        super(DEFAULT_SETTINGS);

        this.setRegistryName("spell_scroll");
    }
}
