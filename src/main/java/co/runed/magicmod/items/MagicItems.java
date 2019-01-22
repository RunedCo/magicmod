package co.runed.magicmod.items;

import co.runed.brace.RegistryUtil;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class MagicItems {
    public static SpellbookItem SPELL_BOOK = new SpellbookItem();
    public static BaseItem WAND = new WandItem();

    public static void registerAll() {
        RegistryUtil.registerItem(SPELL_BOOK);
        RegistryUtil.registerItem(WAND);
    }
}
