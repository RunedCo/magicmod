package co.runed.magicmod.api.spell;

import co.runed.brace.INbtSerializable;

//TODO: add tiers
public interface ISpellComponent {

    boolean create(ISpell spell);

    boolean run(ISpell spell);
}
