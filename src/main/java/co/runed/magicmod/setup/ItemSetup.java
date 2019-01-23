package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.blocks.MagicBlocks;
import co.runed.magicmod.api.items.MagicItems;
import co.runed.magicmod.blocks.BaseBlock;
import co.runed.magicmod.items.SpellbookItem;
import co.runed.magicmod.items.WandItem;

public class ItemSetup {
    public static void init() {
        ItemSetup.registerSpellItems();
        ItemSetup.registerBlockItems();
    }

    public static void registerSpellItems() {
        MagicItems.SPELL_BOOK = MagicMod.REGISTRY_UTIL.registerItem(new SpellbookItem());
        MagicItems.WAND = MagicMod.REGISTRY_UTIL.registerItem(new WandItem());
    }

    public static void registerBlockItems() {
        MagicItems.BLOCK_MAGIC_ORE = MagicMod.REGISTRY_UTIL.registerBlockItem((BaseBlock)MagicBlocks.MAGIC_ORE);
    }
}
