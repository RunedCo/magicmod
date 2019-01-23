package co.runed.magicmod.recipes;

import co.runed.brace.registry.RegistryUtil;
import co.runed.magicmod.recipes.extraction.ExtractionRecipe;
import co.runed.magicmod.recipes.extraction.ExtractionRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;

public class MagicRecipes {
    public static void registerAll() {
        MagicRecipeSerializer.EXTRACTION = RecipeSerializer.register("magicmod:extraction", new ExtractionRecipeSerializer());
        MagicRecipeType.EXTRACTION = RecipeType.register("magicmod:extraction");
    }
}
