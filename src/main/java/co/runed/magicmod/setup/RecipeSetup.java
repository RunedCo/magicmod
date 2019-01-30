package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.recipe.MagicRecipeSerializer;
import co.runed.magicmod.api.recipe.MagicRecipeType;
import co.runed.magicmod.recipe.extraction.ExtractionRecipeSerializer;

public class RecipeSetup {
    public static void init() {
        MagicRecipeSerializer.EXTRACTION = MagicMod.REGISTRY_UTIL.registerRecipeSerializer("extraction", new ExtractionRecipeSerializer());
        MagicRecipeType.EXTRACTION = MagicMod.REGISTRY_UTIL.registerRecipeType("extraction");
    }
}
