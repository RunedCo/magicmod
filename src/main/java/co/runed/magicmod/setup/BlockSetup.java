package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.block.MagicBlocks;
import co.runed.magicmod.block.DemonPortalBlock;
import co.runed.magicmod.block.ore.MagicOre;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BlockSetup {
    public static void init() {
        BlockSetup.registerOres();
    }

    public static void registerOres() {
        MagicBlocks.MAGIC_ORE = MagicMod.REGISTRY_UTIL.registerBlock(new MagicOre());

        MagicBlocks.DEMON_PORTAL = MagicMod.REGISTRY_UTIL.registerBlock(new DemonPortalBlock(FabricBlockSettings.of(Material.PORTAL).noCollision().ticksRandomly().strength(-1.0F, -1.0f).sounds(BlockSoundGroup.GLASS).lightLevel(11).build()));
    }
}
