package co.runed.magicmod.network.packet.s2c;

import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.PacketByteBuf;

import java.io.IOException;

public class SyncSpellS2CPacket implements Packet<ClientPlayPacketListener> {
    private Spell spell;

    public SyncSpellS2CPacket(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void read(PacketByteBuf byteBuf) throws IOException {
        CompoundTag tag = byteBuf.readCompoundTag();

        this.spell = Spell.fromTag(tag);
    }

    @Override
    public void write(PacketByteBuf byteBuf) throws IOException {
        CompoundTag tag = this.spell.toTag();
        byteBuf.writeCompoundTag(tag);
    }

    @Override
    public void apply(ClientPlayPacketListener var1) {

        //TODO: add actual handling
        SpellManager.addSpell(MinecraftClient.getInstance().player, this.spell);
    }

    @Override
    public boolean isErrorFatal() {
        return false;
    }
}
