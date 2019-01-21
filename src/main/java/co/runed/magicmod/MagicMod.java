package co.runed.magicmod;

import co.runed.magicmod.items.MagicItems;
import net.fabricmc.api.ModInitializer;

import co.runed.brace.RegistryUtil;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	@Override
	public void onInitialize() {
		RegistryUtil.setIdentifier(MagicMod.ID);

		MagicItems.registerAll();
	}
}
