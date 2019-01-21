package co.runed.brace;

import co.runed.magicmod.blocks.BaseBlock;
import co.runed.magicmod.items.BaseItem;
import net.minecraft.util.registry.Registry;

public class RegistryUtil {
    private static String identifier = "minecraft";

    public static void setIdentifier(String id) {
        identifier = id;
    }

    public static <T> T register(Registry<? super T> registry, IRegisterable registerable) {
        return Registry.register(registry, identifier + ":" + registerable.getRegistryName(), (T)registerable);
    }

    public static BaseItem registerItem(BaseItem baseItem) {
        return RegistryUtil.register(Registry.ITEM, baseItem);
    }

    public static BaseBlock registerBlock(BaseBlock baseBlock) {
        Registry.register(Registry.ITEM, identifier + ":" + baseBlock.getRegistryName(), baseBlock.getItem());
        return RegistryUtil.register(Registry.BLOCK, baseBlock);
    }
}
