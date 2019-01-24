package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.blocks.MagicBlocks;
import co.runed.magicmod.api.items.MagicItems;
import co.runed.magicmod.blocks.BaseBlock;
import co.runed.magicmod.items.BaseItem;
import co.runed.magicmod.items.SpellbookItem;
import co.runed.magicmod.items.WandItem;
import net.minecraft.item.Item;

public class ItemSetup {
    public static void init() {
        ItemSetup.registerSpellItems();
        ItemSetup.registerBlockItems();
        ItemSetup.registerResources();
    }

    public static void registerSpellItems() {
        MagicItems.SPELL_BOOK = MagicMod.REGISTRY_UTIL.registerItem(new SpellbookItem());
        MagicItems.WAND = MagicMod.REGISTRY_UTIL.registerItem(new WandItem());
    }

    public static void registerBlockItems() {
        MagicItems.BLOCK_MAGIC_ORE = MagicMod.REGISTRY_UTIL.registerBlockItem((BaseBlock)MagicBlocks.MAGIC_ORE);
    }

    public static void registerResources() {
        MagicItems.GOLD_DUST = MagicMod.REGISTRY_UTIL.registerItem(new BaseItem(new Item.Settings()).setRegistryName("gold_dust"));
        MagicItems.IRON_DUST = MagicMod.REGISTRY_UTIL.registerItem(new BaseItem(new Item.Settings()).setRegistryName("iron_dust"));
        MagicItems.MAGIC_ESSENCE = MagicMod.REGISTRY_UTIL.registerItem(new BaseItem(new Item.Settings()).setRegistryName("magic_essence"));
    }
}
