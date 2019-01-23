package co.runed.magicmod;

import co.runed.brace.registry.RegistryUtil;
import co.runed.magicmod.setup.BlockSetup;
import co.runed.magicmod.setup.ItemSetup;
import co.runed.magicmod.setup.RecipeSetup;
import net.fabricmc.api.ModInitializer;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	public final static RegistryUtil REGISTRY_UTIL = new RegistryUtil(ID);

	@Override
	public void onInitialize() {
		BlockSetup.init();
		ItemSetup.init();
		RecipeSetup.init();
	}
}
