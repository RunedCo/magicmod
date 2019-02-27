package co.runed.magicmod.client;

import co.runed.magicmod.api.item.MagicItems;
import co.runed.magicmod.client.gui.TestSpellScreen;
import co.runed.magicmod.setup.ClientSetup;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;

public class ClientInitTest implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientSetup.init();
        
    }
}
