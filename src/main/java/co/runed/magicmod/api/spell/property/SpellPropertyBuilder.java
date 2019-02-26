package co.runed.magicmod.api.spell.property;

import co.runed.magicmod.api.registry.MagicRegistry;
import net.minecraft.util.Identifier;

public class SpellPropertyBuilder<T> {
    T value = null;
    boolean serializable = false;
    Identifier id = null;

    public static SpellPropertyBuilder identifier(Identifier identifier) {
        SpellPropertyBuilder builder = new SpellPropertyBuilder();
        builder.id = identifier;

        return builder;
    }

    public static SpellPropertyBuilder identifier(String identifier) {
        SpellPropertyBuilder builder = new SpellPropertyBuilder();
        builder.id = new Identifier(identifier);

        return builder;
    }

    public SpellPropertyBuilder initial(T value) {
        this.value = value;

        return this;
    }

    public SpellPropertyBuilder serializable() {
        this.serializable = true;

        return this;
    }

    public SpellProperty<T> build() {
        if(this.serializable) {
            return new SpellPropertySerializable<>(this.id, this.value);
        }

        return new SpellProperty<>(this.id, this.value);
    }

    public SpellProperty<T> register() {
        return MagicRegistry.SPELL_PROPERTIES.add(this.id, this.build());
    }
}
