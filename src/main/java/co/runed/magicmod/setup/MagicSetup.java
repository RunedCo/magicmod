package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.SpellManager;
import co.runed.magicmod.api.registry.MagicRegistry;
import co.runed.magicmod.api.spell.ItemTarget;
import co.runed.magicmod.api.spell.effect.SpellEffect;
import co.runed.magicmod.api.spell.effect.SpellEffects;
import co.runed.magicmod.api.spell.property.SpellProperties;
import co.runed.magicmod.api.spell.property.SpellProperty;
import co.runed.magicmod.api.spell.property.SpellPropertySerializable;
import co.runed.magicmod.spell.effect.BlockBreakSpellEffect;
import co.runed.magicmod.spell.effect.BlockDropsToInventoryEffect;
import co.runed.magicmod.spell.effect.ExtractSpellEffect;
import co.runed.magicmod.spell.effect.VeinSpellEffect;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.function.Supplier;

public class MagicSetup {
    public static void init() {
        MagicSetup.setupSpellProperties();
        MagicSetup.setupSpellEffects();

        ServerStartCallback.EVENT.register(server -> SpellManager.loadLibraries());
    }

    public static void setupSpellEffects() {
        SpellEffects.BREAK_BLOCK = registerSpellEffect("break_block", BlockBreakSpellEffect::new);
        SpellEffects.EXTRACT_BLOCK = registerSpellEffect("extract_block", ExtractSpellEffect::new);
        SpellEffects.VEIN_AREA = registerSpellEffect("vein_area", VeinSpellEffect::new);
        SpellEffects.ITEM_TO_INVENTORY = registerSpellEffect("item_to_inventory", BlockDropsToInventoryEffect::new);
    }

    public static void setupSpellProperties() {
        SpellProperties.WORLD = registerSpellProperty("world");
        SpellProperties.SPEED = registerSerializableSpellProperty("speed", 1.0D, Double.class);
        SpellProperties.CASTER = registerSpellProperty("entity_caster");
        SpellProperties.TARGETS = registerSpellProperty("entity_targets", new ArrayList<>()); //TODO: serialize?
        SpellProperties.RANGE = registerSerializableSpellProperty("range", 4.0D, Double.class);
        SpellProperties.EXPLOSION_STRENGTH = registerSerializableSpellProperty("explosion_strength", 4.0f, Float.class);
        SpellProperties.BLOCK_POSITIONS = registerSpellProperty("block_positions", new ArrayList<>());
        SpellProperties.START_POSITION = registerSpellProperty("initial_block_position");
        SpellProperties.ITEM_TARGET = registerSerializableSpellProperty("item_target", ItemTarget.NONE, ItemTarget.class);
        SpellProperties.DROPS = registerSpellProperty("item_drops", new ArrayList<>());
    }

    public static <T> SpellProperty<T> registerSpellProperty(String id) {
        return registerSpellProperty(id, null);
    }

    public static <T> SpellProperty<T> registerSpellProperty(String id, T defaultValue) {
        Identifier identifier = MagicMod.REGISTRY_UTIL.createId(id);
        return MagicMod.REGISTRY_UTIL.register(MagicRegistry.SPELL_PROPERTIES, id, new SpellProperty<>(identifier, defaultValue));
    }

    public static <T> SpellProperty<T> registerSerializableSpellProperty(String id, T defaultValue, Class<T> clazz) {
        Identifier identifier = MagicMod.REGISTRY_UTIL.createId(id);
        return MagicMod.REGISTRY_UTIL.register(MagicRegistry.SPELL_PROPERTIES, id, new SpellPropertySerializable<>(identifier, defaultValue, clazz));
    }

    public static <T> SpellEffect registerSpellEffect(String id, Supplier<? extends SpellEffect> supplier) {
        Identifier identifier = MagicMod.REGISTRY_UTIL.createId(id);
        return MagicMod.REGISTRY_UTIL.register(MagicRegistry.SPELL_EFFECTS, id, SpellEffects.add(identifier, supplier));
    }
}
