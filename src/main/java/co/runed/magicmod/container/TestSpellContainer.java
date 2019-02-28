package co.runed.magicmod.container;

import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;

public class TestSpellContainer extends Container {
    public PlayerInventory playerInventory;

    public Inventory inventory;

    public TestSpellContainer(int syncId, PlayerInventory playerInventory_1) {
        super(null, syncId);

        this.playerInventory = playerInventory_1;
        this.inventory = new BasicInventory();

        this.addSlot(new Slot(this.inventory, 0, 1, 1));

        for(int inventoryRow = 0; inventoryRow < 3; inventoryRow++) {
            for(int inventoryCol = 0; inventoryCol < 9; inventoryCol++) {
                this.addSlot(new Slot(playerInventory_1, inventoryCol + inventoryRow * 9 + 9, 8 + inventoryCol * 18, 211 - (4 - inventoryRow) * 18 - 10));
            }
        }
    }

    @Override
    public boolean canUse(PlayerEntity var1) {
        return true;
    }
}
