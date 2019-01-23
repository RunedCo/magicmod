package co.runed.magicmod.blocks;

import co.runed.brace.registry.IRegisterable;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.block.BlockItem;

public class BaseBlock extends Block implements IRegisterable {
    private String registryName = "";

    public BaseBlock(Settings blockSettings) {
        super(blockSettings);
    }

    @Override
    public String getRegistryName() {
        return this.registryName;
    }

    @Override
    public BaseBlock setRegistryName(String name) {
        this.registryName = name;

        return this;
    }
}
