package co.runed.magicmod.api.spell.property;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.util.Identifier;

public class SpellPropertySerializable<T> extends SpellProperty<T> {
    public SpellPropertySerializable(Identifier id, T defaultValue) {
        super(id, defaultValue);
    }

    /* private Class<T> type;

    public SpellPropertySerializable(Identifier id, T initial, Class<T> type) {
        super(id, initial);

        this.type = type;
    } */

    public String toJson(T value) {
        Gson gson = new Gson();

        return gson.toJson(value);
    }

    public T fromJson(String object) {
        Gson gson = new Gson();

        TypeToken<T> token = new TypeToken<T>(){};

        return (T)gson.fromJson(object, token.getRawType());
    }
}
