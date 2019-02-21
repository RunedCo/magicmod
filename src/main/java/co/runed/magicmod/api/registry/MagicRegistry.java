package co.runed.magicmod.api.registry;

import co.runed.magicmod.api.spell.SpellEffect;
import co.runed.magicmod.api.spell.SpellProperties;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.util.registry.SimpleRegistry;

public class MagicRegistry {
    public static SimpleRegistry<SpellProperty<?>> SPELL_PROPERTIES;
    public static SimpleRegistry<SpellEffect> SPELL_EFFECTS;
}
