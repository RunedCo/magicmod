package co.runed.magicmod.api;

import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public interface ISpell {
    ActionResult useOnBlock(ItemUsageContext context);
}
