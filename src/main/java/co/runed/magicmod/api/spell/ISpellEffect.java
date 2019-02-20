package co.runed.magicmod.api.spell;

import java.util.ArrayList;
import java.util.List;

//TODO: add tiers
public interface ISpellEffect {

    boolean build(ISpell spell);

    boolean run(ISpell spell);

    default int getTier() {
        return 1;
    };

    ISpellEffect setTier(int tier);

    default double getManaCost(ISpell spell) {
        return this.getTier() * 10;
    };

    default List<SpellProperty> getGraphProperties() {
        return new ArrayList<>();
    };
}
