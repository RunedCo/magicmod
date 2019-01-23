package co.runed.magicmod.recipes.extraction;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.ItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.block.BlockItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

public class ExtractionRecipeSerializer<T extends ExtractionRecipe> implements RecipeSerializer<T> {
    @Override
    public T read(Identifier identifier, JsonObject jsonObject) {
        String group = JsonHelper.getString(jsonObject, "group", "");
        JsonElement jsonElement_1 = JsonHelper.hasArray(jsonObject, "input") ? JsonHelper.getArray(jsonObject, "input") : JsonHelper.getObject(jsonObject, "input");
        Ingredient input = Ingredient.fromJson((JsonElement)jsonElement_1);
        Identifier output = new Identifier(JsonHelper.getString(jsonObject, "output"));
        if (!Registry.ITEM.contains(output)) {
            throw new IllegalStateException("Item: " + output.toString() + " does not exist");
        } else {
            BlockItem outputBlockItem = (BlockItem)Registry.ITEM.get(output);

            JsonObject drops = JsonHelper.getObject(jsonObject, "drops");

            int count = JsonHelper.getInt(drops, "count", 1);
            Identifier item = new Identifier(JsonHelper.getString(drops, "item", "null:null"));
            boolean loot_table = JsonHelper.getBoolean(drops, "loot_table", false);

            ItemStack dropsItem = ItemStack.EMPTY;
            if(Registry.ITEM.contains(item)) dropsItem = new ItemStack((ItemProvider)Registry.ITEM.get(item), count);

            int manaMultiplier = JsonHelper.getInt(jsonObject, "mana_multiplier", 1);
            return (T)new ExtractionRecipe(identifier, group, input, outputBlockItem.getBlock(), dropsItem, loot_table, manaMultiplier);
        }
    }

    @Override
    public T read(Identifier identifier, PacketByteBuf byteBuf) {
        String group = byteBuf.readString(32767);
        Ingredient ingredient = Ingredient.fromPacket(byteBuf);
        BlockItem outputItem = (BlockItem)byteBuf.readItemStack().getItem();
        Block output = outputItem.getBlock();
        ItemStack drops = byteBuf.readItemStack();
        boolean lootDrops = byteBuf.readBoolean();
        int manaMultiplier = byteBuf.readVarInt();
        return (T)new ExtractionRecipe(identifier, group, ingredient, output, drops, lootDrops, manaMultiplier);
    }

    @Override
    public void write(PacketByteBuf byteBuf, ExtractionRecipe extractionRecipe) {
        byteBuf.writeString(extractionRecipe.group);
        extractionRecipe.input.write(byteBuf);
        byteBuf.writeItemStack(extractionRecipe.output.getItem().getDefaultStack());
        byteBuf.writeItemStack(extractionRecipe.drops);
        byteBuf.writeBoolean(extractionRecipe.lootDrops);
        byteBuf.writeVarInt(extractionRecipe.manaMultiplier);
    }
}
