package co.runed.magicmod.container;

import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.entity.player.PlayerEntity;

public class TestSpellContainer extends Container {
    protected TestSpellContainer(ContainerType<?> containerType_1, int int_1) {
        super(containerType_1, int_1);
    }

    @Override
    public boolean canUse(PlayerEntity var1) {
        return true;
    }
}
