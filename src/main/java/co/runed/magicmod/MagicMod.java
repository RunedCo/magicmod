package co.runed.magicmod;

import co.runed.brace.RecipeLibrary;
import co.runed.brace.registry.RegistryUtil;
import co.runed.magicmod.container.TestSpellContainer;
import co.runed.magicmod.setup.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.container.ContainerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	private static final Logger LOGGER = LogManager.getLogger();

	public final static RegistryUtil REGISTRY_UTIL = new RegistryUtil(ID);
	public final static RecipeLibrary RECIPE_LIBRARY = new RecipeLibrary();

	public final static Path CONFIG_DIRECTORY = Paths.get(FabricLoader.getInstance().getConfigDirectory().getPath(), "magicmod");

	public static Identifier SPELL_CONTAINER = new Identifier("magicmod:spell_container");

	//TODO: setup recipe library on connect
	//TODO: setup

	@Override
	public void onInitialize() {
		RegistrySetup.init();

		BlockSetup.init();
		ItemSetup.init();
		RecipeSetup.init();
		MagicSetup.init();



		ContainerProviderRegistry.INSTANCE.registerFactory(SPELL_CONTAINER, (syncId, identifier, player, buf) ->
				new TestSpellContainer(syncId, player.inventory));
	}
}
