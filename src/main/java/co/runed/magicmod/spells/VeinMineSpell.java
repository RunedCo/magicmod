package co.runed.magicmod.spells;

import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellProperty;
import co.runed.magicmod.api.spell.components.BlockBreakSpellComponent;
import co.runed.magicmod.api.spell.components.VeinSpellComponent;

public class VeinMineSpell extends Spell {
    public VeinMineSpell(double range) {
        this.addProperty(SpellProperty.RANGE, range)
                .add(new VeinSpellComponent())
                .add(new BlockBreakSpellComponent());
    }
}
