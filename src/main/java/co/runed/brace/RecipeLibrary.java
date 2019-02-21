package co.runed.brace;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class RecipeLibrary {
    public final HashMap<RecipeType<?>, List<Recipe<?>>> RECIPES = new HashMap<>();
    private final HashMap<Identifier, RecipeType<?>> RECIPE_TYPES = new HashMap<>();
    private RecipeManager recipeManager;
    private World world;

    public void init(World worldIn) {
        clear();

        world = worldIn;
        recipeManager = world.getRecipeManager();

        Collection<Recipe<?>> recipes = recipeManager.values();

        for (Recipe<?> recipe : recipes) {
            RecipeType<?> recipeType = recipe.getType();

            if (!RECIPES.containsKey(recipeType)) {
                RECIPES.put(recipeType, new ArrayList<>());
            }

            RECIPES.get(recipeType).add(recipe);
        }
    }

    public void clear() {
        world = null;
        recipeManager = null;

        RECIPES.clear();
        RECIPE_TYPES.clear();
    }

    public List<Recipe<?>> getAllOfType(Identifier type) {
        RecipeType<?> recipeType = Registry.RECIPE_TYPE.get(type);

        if (recipeType == null) {
            return new ArrayList<>();
        }

        return getAllOfType(recipeType);
    }

    public List<Recipe<?>> getAllOfType(RecipeType<?> type) {
        if (!RECIPES.containsKey(type) || !isSetup()) return new ArrayList<>();

        return RECIPES.get(type);
    }

    public boolean isSetup() {
        return recipeManager == null || world == null;
    }
}
