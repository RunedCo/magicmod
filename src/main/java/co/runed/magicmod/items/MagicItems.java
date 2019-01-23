package co.runed.magicmod.items;

import co.runed.brace.registry.RegistryUtil;
import co.runed.magicmod.MagicMod;
import co.runed.magicmod.blocks.MagicBlocks;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;

public class MagicItems {
    public static SpellbookItem SPELL_BOOK;
    public static BaseItem WAND;

    // BLOCK ITEMS
    public static BlockItem BLOCK_MAGIC_ORE;

    public static void registerAll() {
        SPELL_BOOK = MagicMod.REGISTRY_UTIL.registerItem(new SpellbookItem());
        WAND = MagicMod.REGISTRY_UTIL.registerItem(new WandItem());

        BLOCK_MAGIC_ORE = MagicMod.REGISTRY_UTIL.registerBlockItem(MagicBlocks.MAGIC_ORE);
    }
}
