package co.runed.magicmod.spells;

import co.runed.brace.registry.IRegisterable;
import co.runed.magicmod.api.ISpell;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public abstract class VeinSpell implements ISpell, IRegisterable {
    @Override
    public String getRegistryName() {
        return "vein_spell";
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return null;
    }
}
