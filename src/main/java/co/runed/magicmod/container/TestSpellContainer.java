package co.runed.magicmod.container;

import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;

public class TestSpellContainer extends Container {
    public PlayerInventory playerInventory;

    public TestSpellContainer(int syncId, PlayerInventory playerInventory_1) {
        super(null, syncId);

        this.playerInventory = playerInventory_1;
        
        for(int int_8 = 0; int_8 < 3; int_8++) {
            for(int int_7 = 0; int_7 < 9; int_7++) {
                this.addSlot(new Slot(playerInventory_1, int_7 + int_8 * 9 + 9, 8 + int_7 * 18, 211 - (4 - int_8) * 18 - 10));
            }
        }
    }

    @Override
    public boolean canUse(PlayerEntity var1) {
        return true;
    }
}
