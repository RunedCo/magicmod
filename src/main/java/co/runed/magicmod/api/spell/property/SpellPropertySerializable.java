package co.runed.magicmod.api.spell.property;

import co.runed.brace.INbtSerializable;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class SpellPropertySerializable<T> extends SpellProperty<T> {

    private Class<T> type;

    public SpellPropertySerializable(Identifier id, Class<T> type) {
        this(id, null, type);
    }

    public SpellPropertySerializable(Identifier id, T defaultValue, Class<T> type) {
        super(id, defaultValue);

        this.type = type;
    }

    public JsonObject toJson(T value) {
        Gson gson = new Gson();

        return (JsonObject)gson.toJsonTree(value);
    }

    public T fromJson(JsonObject object) {
        Gson gson = new Gson();

        return gson.fromJson(object, type);
    }
}
