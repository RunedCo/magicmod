package co.runed.magicmod.api.spell;

import com.sun.istack.internal.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class SpellProperty<T> {
    private Identifier identifier;
    private T defaultValue;

    public SpellProperty(Identifier id) {
        this(id, null);
    }

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
}
