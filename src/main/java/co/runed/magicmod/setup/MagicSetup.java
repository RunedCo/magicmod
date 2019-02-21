package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.registry.MagicRegistry;
import co.runed.magicmod.api.spell.*;
import co.runed.magicmod.spell.effects.BlockBreakSpellEffect;
import co.runed.magicmod.spell.effects.BlockDropsToInventoryEffect;
import co.runed.magicmod.spell.effects.ExtractSpellEffect;
import co.runed.magicmod.spell.effects.VeinSpellEffect;
import com.sun.istack.internal.Nullable;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.function.Supplier;

public class MagicSetup {
    public static void init() {
        MagicSetup.setupSpellProperties();
        MagicSetup.setupSpellEffects();
    }

    public static void setupSpellEffects() {
        SpellEffects.BREAK_BLOCK = registerSpellEffect("break_block", BlockBreakSpellEffect::new);
        SpellEffects.EXTRACT_BLOCK = registerSpellEffect("extract_block", ExtractSpellEffect::new);
        SpellEffects.VEIN = registerSpellEffect("vein", VeinSpellEffect::new);
        SpellEffects.ITEM_TO_INVENTORY = registerSpellEffect("item_to_inventory", BlockDropsToInventoryEffect::new);
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

    public static <T> SpellEffect registerSpellEffect(String id, Supplier<? extends SpellEffect> supplier) {
        Identifier identifier = MagicMod.REGISTRY_UTIL.createId(id);
        return MagicMod.REGISTRY_UTIL.register(MagicRegistry.SPELL_EFFECTS, id, SpellEffects.add(identifier, supplier));
    }
}
