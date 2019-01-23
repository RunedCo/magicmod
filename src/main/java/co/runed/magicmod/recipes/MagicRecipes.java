package co.runed.magicmod.recipes;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.recipes.MagicRecipeSerializer;
import co.runed.magicmod.api.recipes.MagicRecipeType;
import co.runed.magicmod.api.recipes.extraction.ExtractionRecipeSerializer;

public class MagicRecipes {
    public static void registerAll() {
        MagicRecipeSerializer.EXTRACTION = MagicMod.REGISTRY_UTIL.registerRecipeSerializer("magicmod:extraction", new ExtractionRecipeSerializer<>());
        MagicRecipeType.EXTRACTION = MagicMod.REGISTRY_UTIL.registerRecipeType("magicmod:extraction");
    }
}
