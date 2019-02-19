package co.runed.magicmod.api.spell;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spell implements ISpell {
    private List<ISpellEffect> components = new ArrayList<>();
    private Map<SpellProperty, Object> properties = new HashMap<>();

    private boolean built = false;
    private int tier = 1;
    private double manaCost = 0;

    @Override
    public Spell build() {
        this.manaCost = 0;

        for (ISpellEffect component : components) {
            boolean success = component.build(this);

            if(!success) return this;

            this.manaCost += component.getManaCost(this);
        }

        this.built = true;

        return this;
    }

    @Override
    public boolean isBuilt() {
        return this.built;
    }

    @Override
    public boolean run() {
        for (ISpellEffect component : components) {
            boolean success = component.run(this);

            if(!success) return false;
        }

        return true;
    }

    @Override
    public Spell add(ISpellEffect effect) {
        this.components.add(effect);

        return this;
    }

    @Override
    public <T> Spell setProperty(SpellProperty<T> property, T value) {
        this.properties.put(property, value);

        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getProperty(SpellProperty<T> property) {
        if(!this.hasProperty(property)) return property.getDefault();

        Object value = this.properties.get(property);

        return (T) value;
    }

    public <T> T getProperty(SpellProperty<T> property, T defaultValue) {
        if(!this.hasProperty(property)) return defaultValue;

        return this.getProperty(property);
    }

    @Override
    public boolean hasProperty(SpellProperty property) {
        return this.properties.containsKey(property);
    }

    @Override
    public double getManaCost() {
        return this.manaCost;
    }

    @Override
    public CompoundTag toTag() {
        return new CompoundTag();
    }

    @Override
    public void fromTag(CompoundTag tag) {
        ListTag componentsTag = tag.getList("effects", 10);
    }
}
