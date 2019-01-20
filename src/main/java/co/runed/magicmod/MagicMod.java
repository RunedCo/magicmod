package co.runed.magicmod;

import co.runed.magicmod.fluid.FluidMana;
import co.runed.magicmod.items.ItemBase;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import co.runed.brace.RegistryUtil;

public class MagicMod implements ModInitializer {
	public final static String ID = "magicmod";
	public final static String VERSION = "";

	public static FluidMana FLUID_MANA = new FluidMana();
	public static BucketItem MANA_BUCKET = new BucketItem(FLUID_MANA, new Item.Settings());

	@Override
	public void onInitialize() {
		RegistryUtil.setIdentifier(MagicMod.ID);

		//RegistryUtil.register(Registry.FLUID, FLUID_MANA);

		RegistryUtil.register(Registry.ITEM, new ItemBase(new Item.Settings()));
		//Registry.register(Registry.ITEM, "magicmod:bucket_mana", MANA_BUCKET);
	}
}
