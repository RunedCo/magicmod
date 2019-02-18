package co.runed.magicmod.api.spell;

//TODO: add tiers
public interface ISpellEffect {

    boolean build(ISpell spell);

    boolean run(ISpell spell);

    int getTier();

    ISpellEffect setTier(int tier);
}
