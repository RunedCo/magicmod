package co.runed.magicmod.blocks;

import co.runed.brace.registry.RegistryUtil;
import co.runed.magicmod.blocks.ore.MagicOre;

public class MagicBlocks {
    public static MagicOre MAGIC_ORE = new MagicOre();

    public static void registerAll() {
        RegistryUtil.registerBlock(MAGIC_ORE);
    }
}
