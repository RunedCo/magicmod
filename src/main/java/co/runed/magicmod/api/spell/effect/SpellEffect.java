package co.runed.magicmod.api.spell.effect;

import co.runed.brace.INbtSerializable;
import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.property.SpellProperty;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SpellEffect implements INbtSerializable {
    private int tier = 1;

    public Supplier<? extends SpellEffect> factory;
    public Identifier identifier;

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

    public <T extends SpellEffect> T create() {
        T effect = (T) this.factory.get();
        effect.identifier = this.identifier;
        effect.factory = this.factory;
        effect.setTier(this.getTier());

        return effect;
    }
}
