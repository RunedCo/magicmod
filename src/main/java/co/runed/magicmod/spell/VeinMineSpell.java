package co.runed.magicmod.spell;

import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellProperty;
import co.runed.magicmod.spell.effects.BlockBreakSpellEffect;
import co.runed.magicmod.spell.effects.VeinSpellEffect;

public class VeinMineSpell extends Spell {
    public VeinMineSpell(double range) {
        this.setProperty(SpellProperties.RANGE, range)
                .add(new VeinSpellEffect())
                .add(new BlockBreakSpellEffect());
    }
}
