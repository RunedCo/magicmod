package co.runed.brace;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloadListener;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class RecipeLibrary implements ResourceReloadListener {
    public final HashMap<RecipeType<?>, List<Recipe<?>>> RECIPES = new HashMap<>();
    private final HashMap<Identifier, RecipeType<?>> RECIPE_TYPES = new HashMap<>();
    private RecipeManager recipeManager;
    private World world;

    public void setup(World world) {
        this.clear();

        this.world = world;
        this.recipeManager = world.getRecipeManager();

        Collection<Recipe<?>> recipes = this.recipeManager.values();

        for (Recipe<?> recipe : recipes) {
            RecipeType<?> recipeType = recipe.getType();

            if(!this.RECIPES.containsKey(recipeType)) {
                this.RECIPES.put(recipeType, new ArrayList<>());
            }

            this.RECIPES.get(recipeType).add(recipe);
        }
    }

    public void clear() {
        this.world = null;
        this.recipeManager = null;

        this.RECIPES.clear();
        this.RECIPE_TYPES.clear();
    }

    public List<Recipe<?>> getAllOfType(Identifier type) {
        RecipeType<?> recipeType = Registry.RECIPE_TYPE.get(type);

        if(recipeType == null) {
            return new ArrayList<>();
        }

        return this.getAllOfType(recipeType);
    }

    public List<Recipe<?>> getAllOfType(RecipeType<?> type) {
        if(!this.RECIPES.containsKey(type) || !this.isSetup()) return new ArrayList<>();

        return this.RECIPES.get(type);
    }

    public boolean isSetup() {
        return this.recipeManager == null || this.world == null;
    }

    @Override
    public void onResourceReload(ResourceManager var1) {
        this.setup(this.world);
    }
}
