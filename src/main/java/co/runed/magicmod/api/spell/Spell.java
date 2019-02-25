package co.runed.magicmod.api.spell;

import co.runed.magicmod.api.registry.MagicRegistry;
import co.runed.magicmod.api.spell.property.SpellProperty;
import co.runed.magicmod.api.spell.property.SpellPropertySerializable;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.*;

public class Spell {
    private final List<SpellEffect> effects = new ArrayList<>();
    private final Map<SpellProperty, Object> properties = new HashMap<>();

    private String displayName;

    private boolean built = false;
    private double manaCost = 0;
    private CastType castType = CastType.NONE;

    public Spell setCastType(CastType type) {
        this.castType = type;

        return this;
    }

    public CastType getCastType() {
        return this.castType;
    }

    public Spell build() {
        this.manaCost = 0;

        for (SpellEffect effect : effects) {
            boolean success = effect.build(this);

            if(!success) return this;

            this.manaCost += effect.getManaCost(this);
        }

        this.built = true;

        return this;
    }

    public boolean isBuilt() {
        return this.built;
    }

    public boolean run() {
        for (SpellEffect effect : effects) {
            boolean success = effect.run(this);

            if(!success) return false;
        }

        return true;
    }

    public Spell putEffect(SpellEffect effect) {
        this.effects.add(effect.create());

        return this;
    }

    public List<SpellEffect> getEffects() {
        return this.effects;
    }

    public Map<SpellProperty, Object> getProperties() {
        return this.properties;
    }

    public <T> Spell putProperty(SpellProperty<T> property, T value) {
        if(this.hasProperty(property) && !this.built) return this;

        this.properties.put(property, value);

        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProperty(SpellProperty<T> property) {
        if(!this.hasProperty(property)) return property.getDefault();

        Object value = this.properties.get(property);

        return (T) value;
    }

    public boolean hasProperty(SpellProperty property) {
        return this.properties.containsKey(property);
    }

    public double getManaCost() {
        return this.manaCost;
    }

    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();

        tag.putString("cast_type", this.castType.name());

        ListTag effectsTag = new ListTag();
        for (SpellEffect effect : this.effects) {
            CompoundTag effectTag = effect.toTag();
            effectTag.putString("identifier", effect.identifier.toString());

            effectsTag.add(effectTag);
        }

        tag.put("effects", effectsTag);

        ListTag propertiesTag = new ListTag();
        for (Map.Entry<SpellProperty, Object> entry : this.properties.entrySet()) {
            SpellProperty key = entry.getKey();
            Object value = entry.getValue();

            if(key instanceof SpellPropertySerializable) {
                SpellPropertySerializable serializable = (SpellPropertySerializable)key;

                CompoundTag propertyTag = new CompoundTag();
                propertyTag.putString("identifier", key.getIdentifier().toString());
                propertyTag.putString("value", serializable.toJson(value));

                propertiesTag.add(propertyTag);
            }
        }

        tag.put("properties", propertiesTag);

        return tag;
    }

    public static Spell fromTag(CompoundTag tag) {
        Spell spell = new Spell();

        if(tag == null) return spell;

        spell.castType = CastType.valueOf(tag.getString("cast_type"));

        ListTag effects = tag.getList("effects", NbtType.COMPOUND);
        for (Tag effectTag : effects) {
            if(effectTag instanceof CompoundTag) {
                CompoundTag effectCompound = (CompoundTag)effectTag;
                Identifier id = new Identifier(effectCompound.getString("identifier"));

                SpellEffect effect = MagicRegistry.SPELL_EFFECTS.get(id);

                effect.fromTag(effectCompound);

                spell.putEffect(effect);
            }
        }

        ListTag properties = tag.getList("properties", NbtType.COMPOUND);
        for (Tag propertyTag : properties) {
            if(propertyTag instanceof CompoundTag) {
                CompoundTag propertyCompound = (CompoundTag)propertyTag;
                Identifier id = new Identifier(propertyCompound.getString("identifier"));

                SpellPropertySerializable property = (SpellPropertySerializable)MagicRegistry.SPELL_PROPERTIES.get(id);

                if(property == null) continue;

                Object value = property.fromJson(propertyCompound.getString("value"));

                spell.putProperty(property, value);
            }
        }

        return spell;
    }

    public enum CastType {
        NONE,
        USE_BLOCK,
        USE,
        ATTACK,
        RITUAL,
        PASSIVE
    }
}
