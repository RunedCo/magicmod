package co.runed.magicmod.blocks.ore;

import co.runed.magicmod.blocks.BaseBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Settings;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;

public class BaseOre extends BaseBlock {
    public BaseOre() {
        super(Block.Settings.of(Material.STONE));
    }
}
