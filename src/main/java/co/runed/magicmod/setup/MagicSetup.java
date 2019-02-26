package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.spell.SpellManager;
import co.runed.magicmod.api.registry.MagicRegistry;
import co.runed.magicmod.api.spell.ItemTarget;
import co.runed.magicmod.api.spell.effect.SpellEffect;
import co.runed.magicmod.api.spell.effect.SpellEffects;
import co.runed.magicmod.api.spell.property.SpellProperties;
import co.runed.magicmod.api.spell.property.SpellProperty;
import co.runed.magicmod.api.spell.property.SpellPropertyBuilder;
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
        SpellProperties.WORLD = SpellPropertyBuilder.identifier("magicmod:world").register();
        SpellProperties.SPEED = SpellPropertyBuilder.identifier("magicmod:speed").initial(1.0D).serializable().register();
        SpellProperties.CASTER = SpellPropertyBuilder.identifier("magicmod:entity_caster").register();
        SpellProperties.TARGETS = SpellPropertyBuilder.identifier("magicmod:entity_targets").initial(new ArrayList<>()).register(); //TODO: serialize?
        SpellProperties.RANGE = SpellPropertyBuilder.identifier("magicmod:range").initial(4.0D).serializable().register();
        SpellProperties.EXPLOSION_STRENGTH = SpellPropertyBuilder.identifier("magicmod:explosion_strength").initial(4.0f).serializable().register();
        SpellProperties.BLOCK_POSITIONS = SpellPropertyBuilder.identifier("magicmod:block_positions").initial(new ArrayList<>()).register();
        SpellProperties.START_POSITION = SpellPropertyBuilder.identifier("magicmod:initial_block_position").register();
        SpellProperties.ITEM_TARGET = SpellPropertyBuilder.identifier("magicmod:item_target").initial(ItemTarget.NONE).serializable().register();
        SpellProperties.DROPS = SpellPropertyBuilder.identifier("magicmod:item_drops").initial(new ArrayList<>()).register();
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
        return MagicMod.REGISTRY_UTIL.register(MagicRegistry.SPELL_PROPERTIES, id, new SpellPropertySerializable<>(identifier, defaultValue));
    }

    public static <T> SpellEffect registerSpellEffect(String id, Supplier<? extends SpellEffect> supplier) {
        Identifier identifier = MagicMod.REGISTRY_UTIL.createId(id);
        return MagicMod.REGISTRY_UTIL.register(MagicRegistry.SPELL_EFFECTS, id, SpellEffects.add(identifier, supplier));
    }
}
