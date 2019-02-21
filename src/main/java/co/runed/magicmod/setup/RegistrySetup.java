package co.runed.magicmod.setup;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.registry.MagicRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;

public class RegistrySetup {
    public static void init() {
        MagicRegistry.SPELL_PROPERTIES = Registry.REGISTRIES.add(MagicMod.REGISTRY_UTIL.createId("spell_properties"), new SimpleRegistry<>());
    }
}
