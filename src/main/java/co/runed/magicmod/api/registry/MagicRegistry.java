package co.runed.magicmod.api.registry;

import co.runed.magicmod.api.spell.ISpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.util.registry.SimpleRegistry;

public class MagicRegistry {
    public static SimpleRegistry<SpellProperty<?>> SPELL_PROPERTIES;
    //TODO: implement registry
    public static SimpleRegistry<ISpellEffect> SPELL_COMPONENTS;
}
