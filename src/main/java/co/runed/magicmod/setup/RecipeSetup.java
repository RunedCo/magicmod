package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.recipes.MagicRecipeSerializer;
import co.runed.magicmod.api.recipes.MagicRecipeType;
import co.runed.magicmod.recipes.extraction.ExtractionRecipeSerializer;

public class RecipeSetup {
    public static void init() {
        MagicRecipeSerializer.EXTRACTION = MagicMod.REGISTRY_UTIL.registerRecipeSerializer("extraction", new ExtractionRecipeSerializer());
        MagicRecipeType.EXTRACTION = MagicMod.REGISTRY_UTIL.registerRecipeType("extraction");
    }
}
