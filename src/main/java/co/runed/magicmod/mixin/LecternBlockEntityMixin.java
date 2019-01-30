package co.runed.magicmod.mixin;

import co.runed.magicmod.api.item.MagicItems;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LecternBlockEntity.class)
public abstract class LecternBlockEntityMixin {
    @Shadow
    ItemStack field_17388;

    public boolean method_17522() {
        Item item_1 = this.field_17388.getItem();
        boolean bool = (item_1 == Items.WRITABLE_BOOK || item_1 == Items.WRITTEN_BOOK || item_1 == MagicItems.SPELL_BOOK);

        return bool;
    }
}
