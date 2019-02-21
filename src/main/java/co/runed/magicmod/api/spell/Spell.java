package co.runed.magicmod.api.spell;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spell {
    private List<SpellEffect> effects = new ArrayList<>();
    private Map<SpellProperty, Object> properties = new HashMap<>();

    private boolean built = false;
    private double manaCost = 0;

    public Spell build() {
        this.manaCost = 0;

        for (SpellEffect effect : effects) {
            boolean success = effect.build(this);

            if(!success) return this;

            this.manaCost += effect.getManaCost(this);
        }

        this.built = true;

        return this;
    }

    public boolean isBuilt() {
        return this.built;
    }

    public boolean run() {
        for (SpellEffect effect : effects) {
            boolean success = effect.run(this);

            if(!success) return false;
        }

        return true;
    }

    public Spell add(SpellEffect effect) {
        this.effects.add(effect);

        return this;
    }

    public <T> Spell setProperty(SpellProperty<T> property, T value) {
        this.properties.put(property, value);

        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProperty(SpellProperty<T> property) {
        if(!this.hasProperty(property)) return property.getDefault();

        Object value = this.properties.get(property);

        return (T) value;
    }

    public <T> T getProperty(SpellProperty<T> property, T defaultValue) {
        if(!this.hasProperty(property)) return defaultValue;

        return this.getProperty(property);
    }

    public boolean hasProperty(SpellProperty property) {
        return this.properties.containsKey(property);
    }

    public double getManaCost() {
        return this.manaCost;
    }

    public CompoundTag toTag() {
        return new CompoundTag();
    }

    public void fromTag(CompoundTag tag) {
        ListTag effectsTag = tag.getList("effects", 10);
    }
}
