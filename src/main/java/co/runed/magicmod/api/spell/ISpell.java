package co.runed.magicmod.api.spell;

import co.runed.brace.INbtSerializable;

public interface ISpell extends INbtSerializable {
    ISpell build();

    boolean run();

    ISpell add(ISpellComponent component);

    <T> ISpell addProperty(SpellProperty<T> property, T value);

    <T> T getProperty(SpellProperty<T> property);

    boolean hasProperty(SpellProperty property);
}
