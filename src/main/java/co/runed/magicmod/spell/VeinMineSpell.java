package co.runed.magicmod.spell;

import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellProperty;
import co.runed.magicmod.api.spell.components.BlockBreakSpellEffect;
import co.runed.magicmod.api.spell.components.VeinSpellEffect;

public class VeinMineSpell extends Spell {
    public VeinMineSpell(double range) {
        this.setProperty(SpellProperty.RANGE, range)
                .add(new VeinSpellEffect())
                .add(new BlockBreakSpellEffect());
    }
}
