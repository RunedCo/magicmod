package co.runed.magicmod.api.recipes;

import co.runed.magicmod.api.recipes.extraction.ExtractionRecipe;
import co.runed.magicmod.api.recipes.extraction.ExtractionRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;

public class MagicRecipeSerializer {
    public static final ExtractionRecipeSerializer<ExtractionRecipe> EXTRACTION = RecipeSerializer.register("magicmod:extraction", new ExtractionRecipeSerializer(ExtractionRecipe::new));
}
