package co.runed.magicmod.api.spell;

//TODO: add tiers
public interface ISpellEffect {

    boolean create(ISpell spell);

    boolean run(ISpell spell);
}
