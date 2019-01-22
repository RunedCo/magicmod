package co.runed.magicmod.blocks.ore;

import co.runed.magicmod.blocks.BaseBlock;
import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class BaseOre extends BaseBlock {
    public static final Settings DEFAULT_SETTINGS = Block.Settings.of(Material.STONE).strength(3.0F, 3.0F);

    public BaseOre() {
        super(FabricBlockSettings.of(Material.STONE).dropsLike(Blocks.STONE).strength(3.0F, 3.0F).build());
    }

    public BaseOre(Block.Settings settings) {
        super(settings);
    }
}
