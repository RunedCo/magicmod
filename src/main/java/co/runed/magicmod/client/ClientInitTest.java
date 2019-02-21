package co.runed.magicmod.client;

import co.runed.magicmod.setup.ClientSetup;
import net.fabricmc.api.ClientModInitializer;

public class ClientInitTest implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientSetup.init();
    }
}
