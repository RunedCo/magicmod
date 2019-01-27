package co.runed.magicmod.api.spell;

import co.runed.brace.INbtSerializable;

public interface ISpellComponent extends INbtSerializable {
    boolean create(ISpell spell);

    boolean run(ISpell spell);
}
