package co.runed.magicmod.api.spell;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class SpellProperties {
    public static SpellProperty<World> WORLD;
    public static SpellProperty<Double> SPEED;
    public static SpellProperty<Entity> CASTER;
    public static SpellProperty<List<Entity>> TARGETS;
    public static SpellProperty<Double> RANGE;
    public static SpellProperty<Float> EXPLOSION_STRENGTH;
    public static SpellProperty<List<BlockPos>> BLOCK_POSITIONS;
    public static SpellProperty<BlockPos> START_POSITION;
    public static SpellProperty<ItemTarget> ITEM_TARGET;
    public static SpellProperty<List<ItemStack>> DROPS;
}
