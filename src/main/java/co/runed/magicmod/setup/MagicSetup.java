package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.registry.MagicRegistry;
import co.runed.magicmod.api.spell.ItemTarget;
import co.runed.magicmod.api.spell.SpellProperties;
import co.runed.magicmod.api.spell.SpellProperty;
import com.sun.istack.internal.Nullable;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class MagicSetup {
    public static void init() {
        MagicSetup.setupSpellProperties();
    }

    public static void setupSpellProperties() {
        SpellProperties.WORLD = registerSpellProperty("world");
        SpellProperties.SPEED = registerSpellProperty("speed", 1.0D);
        SpellProperties.CASTER = registerSpellProperty("entity_caster");
        SpellProperties.TARGETS = registerSpellProperty("entity_targets", new ArrayList<>());
        SpellProperties.RANGE = registerSpellProperty("range", 4.0D);
        SpellProperties.EXPLOSION_STRENGTH = registerSpellProperty("explosion_strength", 4.0f);
        SpellProperties.BLOCK_POSITIONS = registerSpellProperty("block_positions", new ArrayList<>());
        SpellProperties.START_POSITION = registerSpellProperty("initial_block_position");
        SpellProperties.ITEM_TARGET = registerSpellProperty("item_target", ItemTarget.NONE);
        SpellProperties.DROPS = registerSpellProperty("client", new ArrayList<>());
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
