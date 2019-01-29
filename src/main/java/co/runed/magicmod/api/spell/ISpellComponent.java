package co.runed.magicmod.api.spell;

import co.runed.brace.INbtSerializable;

//TODO: add tiers
public interface ISpellComponent extends INbtSerializable {

    boolean create(ISpell spell);

    boolean run(ISpell spell);
}
