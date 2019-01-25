package co.runed.magicmod.api.spell;

import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SpellProperty<T> {
    public static SpellProperty<World> WORLD;
    public static SpellProperty<Double> SPEED;
    public static SpellProperty<Entity> ENTITY_CASTER;
    public static SpellProperty<Entity> ENTITY_TARGET;
    public static SpellProperty<Double> RANGE;

    private Identifier identifier;

    public SpellProperty(Identifier id) {
        this.identifier = id;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public String toString() {
        return "<spellproperty " + this.identifier + ">";
    }
}
