package co.runed.magicmod.network.packet;

import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.PacketByteBuf;

import java.io.IOException;

public class SyncSpellLibraryS2CPacket implements Packet<ClientPlayPacketListener> {
    @Override
    public void read(PacketByteBuf var1) throws IOException {

    }

    @Override
    public void write(PacketByteBuf var1) throws IOException {

    }

    @Override
    public void apply(ClientPlayPacketListener var1) {

    }

    @Override
    public boolean isErrorFatal() {
        return false;
    }
}
