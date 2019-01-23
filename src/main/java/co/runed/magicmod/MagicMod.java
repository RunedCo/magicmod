package co.runed.magicmod;

import co.runed.magicmod.blocks.MagicBlocks;
import co.runed.magicmod.items.MagicItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

import co.runed.brace.registry.RegistryUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Iterator;
import java.util.stream.Stream;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	@Override
	public void onInitialize() {
		RegistryUtil.setIdentifier(MagicMod.ID);

		MagicBlocks.registerAll();
		MagicItems.registerAll();

		RecipeType.register("magicmod:extraction");
	}
}
