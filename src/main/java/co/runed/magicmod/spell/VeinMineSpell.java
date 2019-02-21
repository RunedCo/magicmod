package co.runed.magicmod.spell;

import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellProperties;
import co.runed.magicmod.spell.effects.BlockBreakSpellEffect;
import co.runed.magicmod.spell.effects.VeinSpellEffect;

public class VeinMineSpell extends Spell {
    public VeinMineSpell(double range) {
        this.putProperty(SpellProperties.RANGE, range)
                .putEffect(new VeinSpellEffect())
                .putEffect(new BlockBreakSpellEffect());
    }
}
