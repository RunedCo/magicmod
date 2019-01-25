package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.registry.MagicRegistry;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SpellSetup {
    public static void init() {
        SpellSetup.setupSpellProperties();
    }

    public static void setupSpellProperties() {
        SpellProperty.WORLD = registerSpellProperty("world");
        SpellProperty.SPEED = registerSpellProperty("speed");
        SpellProperty.ENTITY_CASTER = registerSpellProperty("entity_caster");
        SpellProperty.ENTITY_TARGET = registerSpellProperty("entity_target");
        SpellProperty.RANGE = registerSpellProperty("range");
    }

    @SuppressWarnings("unchecked")
    public static <T> SpellProperty<T> registerSpellProperty(String id) {
        Identifier identifier = MagicMod.REGISTRY_UTIL.createId(id);
        return (SpellProperty<T>)MagicMod.REGISTRY_UTIL.register(MagicRegistry.SPELL_PROPERTIES, id, new SpellProperty<World>(identifier));
    }
}