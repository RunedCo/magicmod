package co.runed.brace;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class RecipeLibrary implements ResourceReloadListener {
    public static final HashMap<RecipeType<?>, List<Recipe<?>>> RECIPES = new HashMap<>();
    private static final HashMap<Identifier, RecipeType<?>> RECIPE_TYPES = new HashMap<>();
    private static RecipeManager recipeManager;
    private static World world;

    public static void setup(World world) {
        clear();

        world = world;
        recipeManager = world.getRecipeManager();

        Collection<Recipe<?>> recipes = recipeManager.values();

        for (Recipe<?> recipe : recipes) {
            RecipeType<?> recipeType = recipe.getType();

            if(!RECIPES.containsKey(recipeType)) {
                RECIPES.put(recipeType, new ArrayList<>());
            }

            RECIPES.get(recipeType).add(recipe);
        }
    }

    public static void clear() {
        world = null;
        recipeManager = null;

        RECIPES.clear();
        RECIPE_TYPES.clear();
    }

    public static List<Recipe<?>> getAllOfType(Identifier type) {
        RecipeType<?> recipeType = Registry.RECIPE_TYPE.get(type);

        if(recipeType == null) {
            return new ArrayList<>();
        }

        return getAllOfType(recipeType);
    }

    public static List<Recipe<?>> getAllOfType(RecipeType<?> type) {
        if(!RECIPES.containsKey(type) || !isSetup()) return new ArrayList<>();

        return RECIPES.get(type);
    }

    public static boolean isSetup() {
        return recipeManager == null || world == null;
    }

    @Override
    public CompletableFuture<Void> apply(Helper helper, ResourceManager resourceManager, Profiler profiler, Profiler profiler1, Executor executor, Executor executor1) {
        return null;
    }
}
