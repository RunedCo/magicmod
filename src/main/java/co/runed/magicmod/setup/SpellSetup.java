package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.registry.MagicRegistry;
import co.runed.magicmod.api.spell.SpellProperty;
import com.sun.istack.internal.Nullable;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SpellSetup {
    public static void init() {
        SpellSetup.setupSpellProperties();
    }

    public static void setupSpellProperties() {
        SpellProperty.WORLD = registerSpellProperty("world");
        SpellProperty.SPEED = registerSpellProperty("speed", 1.0D);
        SpellProperty.ENTITY_CASTER = registerSpellProperty("entity_caster");
        SpellProperty.ENTITY_TARGET = registerSpellProperty("entity_target");
        SpellProperty.RANGE = registerSpellProperty("range", 4.0D);
        SpellProperty.EXPLOSION_STRENGTH = registerSpellProperty("explosion_strength", 4.0f);
        SpellProperty.BLOCK_POSITIONS = registerSpellProperty("block_positions");
        SpellProperty.START_POSITION = registerSpellProperty("start_position");
    }

    //@SuppressWarnings("unchecked")
    public static <T> SpellProperty<T> registerSpellProperty(String id) {
        return registerSpellProperty(id, null);
    }

    public static <T> SpellProperty<T> registerSpellProperty(String id, @Nullable T defaultValue) {
        Identifier identifier = MagicMod.REGISTRY_UTIL.createId(id);
        return MagicMod.REGISTRY_UTIL.register(MagicRegistry.SPELL_PROPERTIES, id, new SpellProperty<>(identifier, defaultValue));
    }
}
