package co.runed.magicmod.api.spell.property;

import net.minecraft.util.Identifier;

public class SpellProperty<T> {
    private Identifier identifier;
    private T defaultValue;

    public SpellProperty(Identifier id, T defaultValue) {
        this.identifier = id;
        this.defaultValue = defaultValue;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public String toString() {
        return "<SpellProperty " + this.identifier + ">";
    }

    public SpellProperty<T> setDefault(T defaultValue) {
        this.defaultValue = defaultValue;

        return this;
    }

    public T getDefault() {
        return this.defaultValue;
    }

    public class Builder<V> {

    }
}
