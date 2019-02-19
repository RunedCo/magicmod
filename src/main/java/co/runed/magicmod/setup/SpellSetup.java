package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.registry.MagicRegistry;
import co.runed.magicmod.api.spell.ItemTarget;
import co.runed.magicmod.api.spell.SpellProperty;
import com.sun.istack.internal.Nullable;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class SpellSetup {
    public static void init() {
        SpellSetup.setupSpellProperties();
    }

    public static void setupSpellProperties() {
        SpellProperty.WORLD = registerSpellProperty("world");
        SpellProperty.SPEED = registerSpellProperty("speed", 1.0D);
        SpellProperty.CASTER = registerSpellProperty("entity_caster");
        SpellProperty.TARGETS = registerSpellProperty("entity_targets", new ArrayList<>());
        SpellProperty.RANGE = registerSpellProperty("range", 4.0D);
        SpellProperty.EXPLOSION_STRENGTH = registerSpellProperty("explosion_strength", 4.0f);
        SpellProperty.BLOCK_POSITIONS = registerSpellProperty("block_positions", new ArrayList<>());
        SpellProperty.START_POSITION = registerSpellProperty("initial_block_position");
        SpellProperty.DROP_TARGET = registerSpellProperty("item_drop_target", ItemTarget.NONE);
        SpellProperty.DROPS = registerSpellProperty("client", new ArrayList<>());
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
