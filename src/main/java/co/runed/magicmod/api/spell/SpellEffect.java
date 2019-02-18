package co.runed.magicmod.api.spell;

public abstract class SpellEffect implements ISpellEffect {
    private int tier;

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    public ISpellEffect setTier(int tier) {
        this.tier = tier;

        return this;
    }
}
