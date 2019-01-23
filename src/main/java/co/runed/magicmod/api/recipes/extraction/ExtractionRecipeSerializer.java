package co.runed.magicmod.api.recipes.extraction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

public class ExtractionRecipeSerializer<T extends ExtractionRecipe> implements RecipeSerializer<T> {
    private int manaCost = 100;
    private final ExtractionRecipeSerializer.RecipeFactory<T> recipeFactory;

    public ExtractionRecipeSerializer(ExtractionRecipeSerializer.RecipeFactory<T> recipeFactory) {
        this.recipeFactory = recipeFactory;
    }

    @Override
    public T read(Identifier identifier, JsonObject jsonObject) {
        String group = JsonHelper.getString(jsonObject, "group", "");
        JsonElement jsonElement_1 = JsonHelper.hasArray(jsonObject, "ingredient") ? JsonHelper.getArray(jsonObject, "ingredient") : JsonHelper.getObject(jsonObject, "ingredient");
        Ingredient ingredient = Ingredient.fromJson((JsonElement)jsonElement_1);
        Identifier resultId = new Identifier(JsonHelper.getString(jsonObject, "result"));
        if (!Registry.ITEM.contains(resultId)) {
            throw new IllegalStateException("Item: " + resultId.toString() + " does not exist");
        } else {
            ItemStack result = new ItemStack((ItemProvider)Registry.ITEM.get(resultId));
            int manaCost = JsonHelper.getInt(jsonObject, "mana_cost", this.manaCost);
            return this.recipeFactory.create(identifier, group, ingredient, result, manaCost);
        }
    }

    @Override
    public T read(Identifier identifier, PacketByteBuf byteBuf) {
        String group = byteBuf.readString(32767);
        Ingredient ingredient = Ingredient.fromPacket(byteBuf);
        ItemStack output = byteBuf.readItemStack();
        int manaCost = byteBuf.readVarInt();
        return this.recipeFactory.create(identifier, group, ingredient, output, manaCost);
    }

    @Override
    public void write(PacketByteBuf byteBuf, T extractionRecipe) {
        byteBuf.writeString(extractionRecipe.group);
        extractionRecipe.input.write(byteBuf);
        byteBuf.writeItemStack(extractionRecipe.output);
        byteBuf.writeVarInt(extractionRecipe.manaCost);
    }

    public interface RecipeFactory<T extends ExtractionRecipe> {
        T create(Identifier id, String group, Ingredient ingredient, ItemStack result, int manaCost);
    }
}
