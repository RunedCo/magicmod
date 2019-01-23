package co.runed.magicmod.recipes;

import co.runed.brace.registry.RegistryUtil;
import co.runed.magicmod.recipes.extraction.ExtractionRecipe;
import co.runed.magicmod.recipes.extraction.ExtractionRecipeSerializer;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;

public class MagicRecipeSerializer {
    public static ExtractionRecipeSerializer<ExtractionRecipe> EXTRACTION;
}
