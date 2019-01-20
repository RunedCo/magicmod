package co.runed.brace;

import net.minecraft.util.registry.Registry;

public class RegistryUtil {
    private static String identifier = "minecraft";

    public static void setIdentifier(String id) {
        identifier = id;
    }

    public static <T> T register(Registry<? super T> registry, IRegisterable registerable) {
        return Registry.register(registry, identifier + ":" + registerable.getRegistryName(), (T)registerable);
    }
}
