package co.runed.magicmod.mixin;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.items.MagicItems;
import net.minecraft.block.entity.LecternBlockEntity;
import net.minecraft.client.render.block.entity.LecternBlockEntityRenderer;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(LecternBlockEntityRenderer.class)
public class LecternBlockEntityRendererMixin extends BlockEntityRendererMixin {

    private static final Identifier MAGIC_BOOK_TEXTURE = new Identifier(MagicMod.ID, "textures/entity/spell_book.png");

    @Inject(method = "method_17582", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/LecternBlockEntityRenderer;bindTexture(Lnet/minecraft/util/Identifier;)V", shift = At.Shift.AFTER))
    public void onMethod_17582(LecternBlockEntity lecternBlockEntity_1, double double_1, double double_2, double double_3, float float_1, int int_1, CallbackInfo ci) {
        this.bindTexture(MAGIC_BOOK_TEXTURE);
    }
}
