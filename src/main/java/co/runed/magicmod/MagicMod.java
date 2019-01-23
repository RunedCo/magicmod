package co.runed.magicmod;

import co.runed.magicmod.recipes.MagicRecipeType;
import co.runed.magicmod.blocks.MagicBlocks;
import co.runed.magicmod.items.MagicItems;

import co.runed.magicmod.recipes.MagicRecipes;
import net.fabricmc.api.ModInitializer;

import co.runed.brace.registry.RegistryUtil;
import net.minecraft.recipe.RecipeType;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	@Override
	public void onInitialize() {
		RegistryUtil.setIdentifier(MagicMod.ID);

		MagicBlocks.registerAll();
		MagicItems.registerAll();
		MagicRecipes.registerAll();
	}
}
