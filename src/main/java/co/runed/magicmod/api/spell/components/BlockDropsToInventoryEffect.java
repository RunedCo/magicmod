package co.runed.magicmod.api.spell.components;

import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;

public class BlockDropsToInventoryEffect implements ISpellEffect {
    @Override
    public boolean create(ISpell spell) {
        spell.addProperty(SpellProperty.ADD_DROPS_TO_INVENTORY, true);

        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        return true;
    }
}
