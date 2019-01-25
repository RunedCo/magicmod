package co.runed.magicmod.api.spell;

import co.runed.brace.INbtSerializable;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;

public interface ISpell extends INbtSerializable {
    boolean run();

    Spell add(ISpellComponent component);

    <T> Spell addProperty(SpellProperty<T> property, T value);

    <T> T getProperty(SpellProperty<T> property);

    boolean hasProperty(SpellProperty property);
}
