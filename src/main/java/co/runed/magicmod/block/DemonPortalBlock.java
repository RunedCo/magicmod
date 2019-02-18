package co.runed.magicmod.block;

import co.runed.brace.registry.IRegisterable;
import net.minecraft.block.PortalBlock;

public class DemonPortalBlock extends PortalBlock implements IRegisterable {
    private String registryName = "demon_portal";

    public DemonPortalBlock(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    public String getRegistryName() {
        return this.registryName;
    }

    @Override
    public IRegisterable setRegistryName(String name) {
        this.registryName = name;

        return this;
    }
}
