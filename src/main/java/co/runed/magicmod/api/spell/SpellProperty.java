package co.runed.magicmod.api.spell;

import com.sun.istack.internal.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpellProperty<T> {
    public static SpellProperty<World> WORLD;
    public static SpellProperty<Double> SPEED;
    public static SpellProperty<Entity> ENTITY_CASTER;
    public static SpellProperty<Entity> ENTITY_TARGET;
    public static SpellProperty<Double> RANGE;
    public static SpellProperty<Float> EXPLOSION_STRENGTH;
    public static SpellProperty<BlockPos[]> BLOCK_POSITIONS;
    public static SpellProperty<BlockPos> START_POSITION;

    private Identifier identifier;
    private T defaultValue;

    public SpellProperty(Identifier id) {
        this(id, null);
    }

    public SpellProperty(Identifier id, @Nullable T defaultValue) {
        this.identifier = id;
        this.defaultValue = defaultValue;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public String toString() {
        return "<spellproperty " + this.identifier + ">";
    }

    public SpellProperty<T> setDefault(T defaultValue) {
        this.defaultValue = defaultValue;

        return this;
    }

    public T getDefault() {
        return this.defaultValue;
    }
}
