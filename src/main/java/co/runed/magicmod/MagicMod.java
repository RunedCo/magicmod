package co.runed.magicmod;

import co.runed.magicmod.items.ItemBase;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import co.runed.brace.RegistryUtil;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	@Override
	public void onInitialize() {
		RegistryUtil.setIdentifier(MagicMod.ID);

		RegistryUtil.register(Registry.ITEM, new ItemBase(new Item.Settings()));
	}
}
