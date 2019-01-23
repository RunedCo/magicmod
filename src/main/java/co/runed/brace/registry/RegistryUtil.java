package co.runed.brace.registry;

import co.runed.magicmod.blocks.BaseBlock;
import co.runed.magicmod.items.BaseItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;

public class RegistryUtil {
    private static String identifier = "minecraft";

    public static void setIdentifier(String id) {
        identifier = id;
    }

    public static <T> T register(Registry<? super T> registry, IRegisterable registerable) {
        return RegistryUtil.register(registry, identifier, registerable.getRegistryName(), (T)registerable);
    }

    public static <T> T register(Registry<? super T> registry, String id, T obj) {
        return Registry.register(registry, identifier + ":" + id, obj);
    }

    public static <T> T register(Registry<? super T> registry, String namespace, String id, T obj) {
        return Registry.register(registry, namespace + ":" + id, obj);
    }

    public static <T extends Item> T registerItem(BaseItem baseItem) {
        return RegistryUtil.register(Registry.ITEM, baseItem);
    }

    public static <T extends Block> T registerBlock(BaseBlock baseBlock) {
        return RegistryUtil.register(Registry.BLOCK, baseBlock);
    }

    public static BlockItem registerBlockItem(BaseBlock block) {
        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        return RegistryUtil.register(Registry.ITEM, block.getRegistryName(), blockItem);
    }
}
