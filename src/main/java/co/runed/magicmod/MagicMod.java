package co.runed.magicmod;

import co.runed.brace.registry.RegistryUtil;
import co.runed.magicmod.setup.*;
import net.fabricmc.api.ModInitializer;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	public final static RegistryUtil REGISTRY_UTIL = new RegistryUtil(ID);
	//TODO: setup recipe library on connect
	//TODO: setup

	@Override
	public void onInitialize() {
		RegistrySetup.init();

		BlockSetup.init();
		ItemSetup.init();
		RecipeSetup.init();
		SpellSetup.init();
		EntitySetup.init();
	}
}
