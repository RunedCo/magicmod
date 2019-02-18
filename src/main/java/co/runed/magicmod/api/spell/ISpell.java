package co.runed.magicmod.api.spell;

import co.runed.brace.INbtSerializable;

public interface ISpell extends INbtSerializable {
    ISpell build();

    boolean run();

    ISpell add(ISpellEffect component);

    <T> ISpell addProperty(SpellProperty<T> property, T value);

    <T> T getProperty(SpellProperty<T> property);

    <T> Spell setProperty(SpellProperty<T> property, T value);

    boolean isBuilt();

    boolean hasProperty(SpellProperty property);
}
