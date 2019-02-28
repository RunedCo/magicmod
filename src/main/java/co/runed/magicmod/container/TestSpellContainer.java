package co.runed.magicmod.container;

import co.runed.magicmod.api.item.MagicItems;
import net.minecraft.container.AnvilContainer;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class TestSpellContainer extends Container {
    public PlayerInventory playerInventory;

    public Inventory inventory;

    public TestSpellContainer(int syncId, PlayerInventory playerInventory_1) {
        super(null, syncId);

        this.playerInventory = playerInventory_1;
        this.inventory = new BasicInventory(1) {
            public void markDirty() {
                super.markDirty();
                TestSpellContainer.this.onContentChanged(this);
            }
        };

        this.addSlot(new Slot(this.inventory, 0, -5, -37));

        for(int inventoryRow = 0; inventoryRow < 3; inventoryRow++) {
            for(int inventoryCol = 0; inventoryCol < 9; inventoryCol++) {
                this.addSlot(new Slot(playerInventory_1, inventoryCol + inventoryRow * 9 + 9, 8 + inventoryCol * 18, 211 - (4 - inventoryRow) * 18 - 10));
            }
        }

        for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++)
        {
            this.addSlot(new Slot(playerInventory, hotbarSlot, 8 + hotbarSlot * 18, 211 - 24));
        }
    }

    @Override
    public ItemStack transferSlot(PlayerEntity playerIn, int index)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slotList.get(index);

        if (slot != null && slot.hasStack())
        {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if (index < 1)
            {
                if (!this.insertItem(itemStack1, 0, this.slotList.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.insertItem(itemStack1, 0, 1, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemStack1.isEmpty())
            {
                slot.setStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onStackChanged(itemStack, itemStack1);
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity var1) {
        return true;
    }
}
