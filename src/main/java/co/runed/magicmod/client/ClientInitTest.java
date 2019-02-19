package co.runed.magicmod.client;

import co.runed.magicmod.client.gui.TestSpellScreen;
import co.runed.magicmod.setup.ClientSetup;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class ClientInitTest implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientSetup.init();
    }
}
