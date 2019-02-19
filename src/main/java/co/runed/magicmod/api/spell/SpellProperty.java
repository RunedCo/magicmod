package co.runed.magicmod.api.spell;

import com.sun.istack.internal.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class SpellProperty<T> {
    public static SpellProperty<World> WORLD;
    public static SpellProperty<Double> SPEED;
    public static SpellProperty<Entity> CASTER;
    public static SpellProperty<List<Entity>> TARGETS;
    public static SpellProperty<Double> RANGE;
    public static SpellProperty<Float> EXPLOSION_STRENGTH;
    public static SpellProperty<List<BlockPos>> BLOCK_POSITIONS;
    public static SpellProperty<BlockPos> START_POSITION;
    public static SpellProperty<ItemTarget> DROP_TARGET;
    public static SpellProperty<List<ItemStack>> DROPS;

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
