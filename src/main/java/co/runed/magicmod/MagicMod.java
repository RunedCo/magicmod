package co.runed.magicmod;

import co.runed.magicmod.blocks.MagicBlocks;
import co.runed.magicmod.items.MagicItems;

import co.runed.magicmod.recipes.MagicRecipes;
import net.fabricmc.api.ModInitializer;

import co.runed.brace.registry.RegistryUtil;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	public final static RegistryUtil REGISTRY_UTIL = new RegistryUtil(ID);

	@Override
	public void onInitialize() {
		MagicBlocks.registerAll();
		MagicItems.registerAll();
		MagicRecipes.registerAll();
	}
}
