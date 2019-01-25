package co.runed.magicmod.api.spell;

import co.runed.brace.INbtSerializable;
import net.minecraft.nbt.CompoundTag;

public interface ISpellComponent extends INbtSerializable {
    boolean run(ISpell spell);
}
