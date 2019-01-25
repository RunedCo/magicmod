package co.runed.magicmod.api.spell;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SpellProperty<T> {
    public static final SpellProperty<World> WORLD = new SpellProperty<>();
    public static final SpellProperty<Double> SPEED = new SpellProperty<>();
    public static final SpellProperty<Entity> ENTITY_CASTER = new SpellProperty<>();
    public static final SpellProperty<Entity> ENTITY_TARGET = new SpellProperty<>();
    public static final SpellProperty<Double> RANGE = new SpellProperty<>();
}
