package co.runed.magicmod.api.recipes.extraction;

import co.runed.magicmod.api.recipes.MagicRecipeSerializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ExtractionRecipe implements Recipe<Inventory> {
    protected final RecipeType<?> type;
    protected final Identifier id;
    protected final String group;
    protected final Ingredient input;
    protected final ItemStack output;
    protected final int manaCost;

    public ExtractionRecipe(RecipeType<?> recipeType_1, Identifier identifier_1, String string_1, Ingredient ingredient_1, ItemStack itemStack_1, float float_1, int int_1) {
        this.type = recipeType_1;
        this.id = identifier_1;
        this.group = string_1;
        this.input = ingredient_1;
        this.output = itemStack_1;
        this.manaCost = int_1;
    }

    @Override
    public boolean matches(Inventory inventory_1, World world_1) {
        return this.input.matches(inventory_1.getInvStack(0));
    }

    @Override
    public ItemStack craft(Inventory inventory_1) {
        return this.output.copy();
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int int_1, int int_2) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public ItemStack getRecipeKindIcon() {
        return null;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public String getGroup() {
        return this.group;
    }

    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MagicRecipeSerializer.EXTRACTION;
    }

    public RecipeType<?> getType() {
        return this.type;
    }
}
