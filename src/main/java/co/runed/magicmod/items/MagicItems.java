package co.runed.magicmod.items;

import co.runed.brace.RegistryUtil;
import net.minecraft.util.registry.Registry;

public class MagicItems {
    public static SpellbookItem SPELL_BOOK = new SpellbookItem();

    public static void registerAll() {
        RegistryUtil.register(Registry.ITEM, SPELL_BOOK);
    }
}
