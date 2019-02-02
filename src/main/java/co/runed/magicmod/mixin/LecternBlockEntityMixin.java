package co.runed.magicmod.mixin;

import co.runed.magicmod.api.item.MagicItems;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LecternBlockEntity.class)
public abstract class LecternBlockEntityMixin {
    @Shadow
    ItemStack book;

    @Inject(method = "hasBook", at = @At(value = "RETURN"))
    public void onHasBook(CallbackInfoReturnable<Boolean> ci) {
        Item item_1 = this.book.getItem();
        boolean bool = item_1 == MagicItems.SPELL_BOOK;

        ci.setReturnValue(ci.getReturnValue() || bool);
    }
}
