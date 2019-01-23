package co.runed.magicmod.recipes;

import co.runed.magicmod.api.recipes.MagicRecipeSerializer;
import co.runed.magicmod.api.recipes.MagicRecipeType;
import co.runed.magicmod.api.recipes.extraction.ExtractionRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;

public class MagicRecipes {
    public static void registerAll() {
        MagicRecipeSerializer.EXTRACTION = RecipeSerializer.register("magicmod:extraction", new ExtractionRecipeSerializer());
        MagicRecipeType.EXTRACTION = RecipeType.register("magicmod:extraction");
    }
}
