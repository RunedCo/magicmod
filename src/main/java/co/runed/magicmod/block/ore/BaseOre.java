package co.runed.magicmod.block.ore;

import co.runed.magicmod.block.BaseBlock;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class BaseOre extends BaseBlock {
    public static final Settings DEFAULT_SETTINGS = FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F).build();

    public BaseOre() {
        super(DEFAULT_SETTINGS);
    }

    public BaseOre(Block.Settings settings) {
        super(settings);
    }
}
