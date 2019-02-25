package co.runed.magicmod.api.spell.property;

import com.google.gson.Gson;
import net.minecraft.util.Identifier;

public class SpellPropertySerializable<T> extends SpellProperty<T> {

    private Class<T> type;

    public SpellPropertySerializable(Identifier id, T defaultValue, Class<T> type) {
        super(id, defaultValue);

        this.type = type;
    }

    public String toJson(T value) {
        Gson gson = new Gson();

        return gson.toJson(value);
    }

    public T fromJson(String object) {
        Gson gson = new Gson();

        return gson.fromJson(object, type);
    }
}
