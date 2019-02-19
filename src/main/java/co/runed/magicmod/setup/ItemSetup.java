package co.runed.magicmod.setup;

import co.runed.brace.registry.IRegisterable;
import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.block.MagicBlocks;
import co.runed.magicmod.api.item.MagicItems;
import co.runed.magicmod.block.BaseBlock;
import co.runed.magicmod.item.BaseItem;
import co.runed.magicmod.item.ScrollItem;
import co.runed.magicmod.item.SpellbookItem;
import co.runed.magicmod.item.WandItem;
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
        MagicItems.SCROLL = MagicMod.REGISTRY_UTIL.registerItem(new ScrollItem());
    }

    public static void registerBlockItems() {
        MagicItems.BLOCK_MAGIC_ORE = MagicMod.REGISTRY_UTIL.registerBlockItem((BaseBlock)MagicBlocks.MAGIC_ORE);
        MagicItems.DEMON_PORTAL = MagicMod.REGISTRY_UTIL.registerBlockItem((IRegisterable)MagicBlocks.DEMON_PORTAL);
    }

    public static void registerResources() {
        MagicItems.GOLD_DUST = MagicMod.REGISTRY_UTIL.registerItem(new BaseItem(new Item.Settings()).setRegistryName("gold_dust"));
        MagicItems.IRON_DUST = MagicMod.REGISTRY_UTIL.registerItem(new BaseItem(new Item.Settings()).setRegistryName("iron_dust"));
        MagicItems.MAGIC_ESSENCE = MagicMod.REGISTRY_UTIL.registerItem(new BaseItem(new Item.Settings()).setRegistryName("magic_essence"));
    }
}
