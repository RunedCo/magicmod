package co.runed.magicmod.api.spell;

import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class SpellEffects {
    public static SpellEffect VEIN;
    public static SpellEffect BREAK_BLOCK;
    public static SpellEffect ITEM_TO_INVENTORY;
    public static SpellEffect EXTRACT_BLOCK;

    public static SpellEffect add(Identifier id, Supplier<? extends SpellEffect> function) {
        SpellEffect effect = function.get();

        effect.identifier = id;
        effect.factory = function;

        return effect;
    }
}
