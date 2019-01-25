package co.runed.magicmod.api.spell;

import co.runed.brace.INbtSerializable;

public interface ISpell extends INbtSerializable {
    boolean run();

    Spell add(ISpellComponent component);

    <T> Spell addProperty(SpellProperty<T> property, T value);

    <T> T getProperty(SpellProperty<T> property);

    boolean hasProperty(SpellProperty property);
}
