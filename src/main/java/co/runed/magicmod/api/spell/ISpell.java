package co.runed.magicmod.api.spell;

import co.runed.brace.INbtSerializable;

public interface ISpell extends INbtSerializable {
    ISpell build();

    boolean isBuilt();

    boolean run();

    ISpell add(ISpellEffect component);

    <T> T getProperty(SpellProperty<T> property);

    <T> Spell setProperty(SpellProperty<T> property, T value);

    boolean hasProperty(SpellProperty property);

    int getTier();

    ISpell setTier(int tier);
}
