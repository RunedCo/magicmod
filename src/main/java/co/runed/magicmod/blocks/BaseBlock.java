package co.runed.magicmod.blocks;

import co.runed.brace.IRegisterable;
import net.minecraft.block.Block;

public class BaseBlock extends Block implements IRegisterable {
    public String registryName = "";

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
