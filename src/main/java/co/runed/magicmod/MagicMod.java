package co.runed.magicmod;

import co.runed.brace.RecipeLibrary;
import co.runed.brace.registry.RegistryUtil;
import co.runed.magicmod.setup.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	private static final Logger LOGGER = LogManager.getLogger();

	public final static RegistryUtil REGISTRY_UTIL = new RegistryUtil(ID);
	public final static RecipeLibrary RECIPE_LIBRARY = new RecipeLibrary();

	public final static Path CONFIG_DIRECTORY = Paths.get(FabricLoader.getInstance().getConfigDirectory().getPath(), "magicmod");

	//TODO: setup recipe library on connect
	//TODO: setup

	@Override
	public void onInitialize() {
		RegistrySetup.init();

		BlockSetup.init();
		ItemSetup.init();
		RecipeSetup.init();
		MagicSetup.init();
	}
}
