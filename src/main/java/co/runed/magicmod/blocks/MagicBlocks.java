package co.runed.magicmod.blocks;

import co.runed.brace.registry.RegistryUtil;
import co.runed.magicmod.blocks.ore.MagicOre;
import net.minecraft.block.Blocks;

public class MagicBlocks {
    public static MagicOre MAGIC_ORE;

    public static void registerAll() {
        MAGIC_ORE = RegistryUtil.registerBlock(new MagicOre());
    }
}
