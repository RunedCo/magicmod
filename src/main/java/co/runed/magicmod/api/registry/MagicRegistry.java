package co.runed.magicmod.api.registry;

import co.runed.magicmod.api.spell.effect.SpellEffect;
import co.runed.magicmod.api.spell.property.SpellProperty;
import net.minecraft.util.registry.SimpleRegistry;

public class MagicRegistry {
    public static SimpleRegistry<SpellProperty<?>> SPELL_PROPERTIES;
    public static SimpleRegistry<SpellEffect> SPELL_EFFECTS;
}
