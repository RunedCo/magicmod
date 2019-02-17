package co.runed.magicmod.api.registry;

import co.runed.magicmod.api.spell.ISpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.util.registry.DefaultMappedRegistry;

public class MagicRegistry {
    public static DefaultMappedRegistry<SpellProperty<?>> SPELL_PROPERTIES;
    //TODO: implement registry
    public static DefaultMappedRegistry<ISpellEffect> SPELL_COMPONENTS;
}
