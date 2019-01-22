package co.runed.magicmod.items;

import co.runed.brace.RegistryUtil;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class MagicItems {
    public static SpellbookItem SPELL_BOOK = new SpellbookItem();
    public static BaseItem WAND = new BaseItem(new Item.Settings()).setRegistryName("wand");

    public static void registerAll() {
        RegistryUtil.registerItem(SPELL_BOOK);
        RegistryUtil.registerItem(WAND);
    }
}
