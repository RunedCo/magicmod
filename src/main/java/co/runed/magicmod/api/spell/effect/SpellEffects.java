package co.runed.magicmod.api.spell.effect;

import co.runed.magicmod.api.registry.MagicRegistry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class SpellEffects {
    public static SpellEffect VEIN_AREA;
    public static SpellEffect BREAK_BLOCK;
    public static SpellEffect ITEM_TO_INVENTORY;
    public static SpellEffect EXTRACT_BLOCK;
    public static SpellEffect THREE_AREA;
    public static SpellEffect TREEFELL;

    public static SpellEffect fromIdentifier(Identifier id) {
        return MagicRegistry.SPELL_EFFECTS.get(id);
    }

    public static SpellEffect add(Identifier id, Supplier<? extends SpellEffect> function) {
        SpellEffect effect = function.get();

        effect.identifier = id;
        effect.factory = function;

        return effect;
    }
}
