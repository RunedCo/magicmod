package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.blocks.MagicBlocks;
import co.runed.magicmod.blocks.ore.MagicOre;

public class BlockSetup {
    public static void init() {
        BlockSetup.registerOres();
    }

    public static void registerOres() {
        MagicBlocks.MAGIC_ORE = MagicMod.REGISTRY_UTIL.registerBlock(new MagicOre());
    }
}
