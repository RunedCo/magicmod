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

    //TODO: split into separate functions
    @Override
    public T read(Identifier identifier, JsonObject jsonObject) {
        String group = JsonHelper.getString(jsonObject, "group", "");
        JsonElement jsonElement_1 = JsonHelper.hasArray(jsonObject, "input") ? JsonHelper.getArray(jsonObject, "input") : JsonHelper.getObject(jsonObject, "input");
        Ingredient input = Ingredient.fromJson((JsonElement)jsonElement_1);
        Identifier output = new Identifier(JsonHelper.getString(jsonObject, "output"));
        if (!Registry.BLOCK.contains(output)) {
            throw new IllegalStateException("Block: " + output.toString() + " does not exist");
        } else {
            Block outputBlock = (Block)Registry.BLOCK.get(output);

            JsonObject drops = JsonHelper.getObject(jsonObject, "drops", new JsonObject());
            Identifier item = new Identifier(JsonHelper.getString(drops, "item", "null:null"));

            int count = JsonHelper.getInt(drops, "count", 1);
            String loot = JsonHelper.getString(drops, "loot", null);

            ItemStack dropsItem = ItemStack.EMPTY;
            if(Registry.ITEM.contains(item)) dropsItem = new ItemStack((ItemProvider)Registry.ITEM.get(item), count);

            int manaMultiplier = JsonHelper.getInt(jsonObject, "mana_multiplier", 1);
            return (T)new ExtractionRecipe(identifier, group, input, outputBlock, dropsItem, loot, manaMultiplier);
        }
    }

    @Override
    public T read(Identifier identifier, PacketByteBuf byteBuf) {
        String group = byteBuf.readString(32767);
        Ingredient ingredient = Ingredient.fromPacket(byteBuf);
        BlockItem outputItem = (BlockItem)byteBuf.readItemStack().getItem();
        Block output = outputItem.getBlock();
        ItemStack drops = byteBuf.readItemStack();
        String lootTable = byteBuf.readString(32767);
        int manaMultiplier = byteBuf.readVarInt();
        return (T)new ExtractionRecipe(identifier, group, ingredient, output, drops, lootTable, manaMultiplier);
    }

    @Override
    public void write(PacketByteBuf byteBuf, ExtractionRecipe extractionRecipe) {
        byteBuf.writeString(extractionRecipe.group);
        extractionRecipe.input.write(byteBuf);
        byteBuf.writeItemStack(extractionRecipe.output.getItem().getDefaultStack());
        byteBuf.writeItemStack(extractionRecipe.drops);
        byteBuf.writeString(extractionRecipe.lootTable);
        byteBuf.writeVarInt(extractionRecipe.manaMultiplier);
    }
}
