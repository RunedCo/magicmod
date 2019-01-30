package co.runed.magicmod.item;

import co.runed.brace.registry.IRegisterable;
import net.minecraft.item.Item;

public class BaseItem extends Item implements IRegisterable {
    private String registryName;

    public BaseItem(Settings itemSettings) {
        super(itemSettings);
    }

    @Override
    public String getRegistryName() {
        return this.registryName;
    }

    @Override
    public BaseItem setRegistryName(String name) {
        this.registryName = name;

        return this;
    }
}
