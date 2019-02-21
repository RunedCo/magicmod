package co.runed.magicmod.api.spell;

import java.util.ArrayList;
import java.util.List;

public class SpellEffect {
    private int tier = 1;

    public boolean build(Spell spell) {
        return true;
    }

    public boolean run(Spell spell) {
        return true;
    }
    
    public int getTier() {
        return this.tier;
    }

    public SpellEffect setTier(int tier) {
        this.tier = tier;

        return this;
    }

    
    public double getManaCost(Spell spell) {
        return this.getTier() * 10;
    }

    public List<SpellProperty> getGraphProperties() {
        return new ArrayList<>();
    }

    public SpellEffect create() {
        return new SpellEffect();
    }
}
