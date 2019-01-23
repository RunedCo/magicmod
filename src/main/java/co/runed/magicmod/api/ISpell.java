package co.runed.magicmod.api;

import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;

public interface ISpell {
    ActionResult useOnBlock(ItemUsageContext context);

    CompoundTag toTag();

    void fromTag(CompoundTag tag);
}
