package co.runed.magicmod.network.packet;

import co.runed.magicmod.entity.TestEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.parts.EntityPart;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SymbolEntitySpawnClientPacket implements Packet<ClientPlayPacketListener> {
    private int id;
    private UUID uuid;
    private double x;
    private double y;
    private double z;
    private int velocityX;
    private int velocityY;
    private int velocityZ;
    private int pitch;
    private int yaw;
    private int entityTypeId;
    private int entityData;
    private Color color;

    private DataTracker dataTracker;
    private List<DataTracker.Entry<?>> trackedValues;

    public SymbolEntitySpawnClientPacket(Entity entity) {
        this.id = entity.getEntityId();
        this.uuid = entity.getUuid();
        this.entityTypeId = Registry.ENTITY_TYPE.getRawId(entity.getType());;
        this.x = entity.x;
        this.y = entity.y;
        this.z = entity.z;
        this.pitch = MathHelper.floor(entity.pitch * 256.0F / 360.0F);
        this.yaw = MathHelper.floor(entity.yaw * 256.0F / 360.0F);
        double double_1 = 3.9D;
        this.velocityX = (int)(MathHelper.clamp(entity.velocityX, -3.9D, 3.9D) * 8000.0D);
        this.velocityY = (int)(MathHelper.clamp(entity.velocityY, -3.9D, 3.9D) * 8000.0D);
        this.velocityZ = (int)(MathHelper.clamp(entity.velocityZ, -3.9D, 3.9D) * 8000.0D);
        this.color = Color.BLUE;
    }

    public SymbolEntitySpawnClientPacket(Entity entity, BlockPos blockPos_1) {
        this(entity);
        this.x = (double)blockPos_1.getX();
        this.y = (double)blockPos_1.getY();
        this.z = (double)blockPos_1.getZ();
    }

    public void read(PacketByteBuf byteBuf) throws IOException {
        this.id = byteBuf.readVarInt();
        this.uuid = byteBuf.readUuid();
        this.entityTypeId = byteBuf.readByte();
        this.x = byteBuf.readDouble();
        this.y = byteBuf.readDouble();
        this.z = byteBuf.readDouble();
        this.pitch = byteBuf.readByte();
        this.yaw = byteBuf.readByte();
        this.entityData = byteBuf.readInt();
        this.velocityX = byteBuf.readShort();
        this.velocityY = byteBuf.readShort();
        this.velocityZ = byteBuf.readShort();
        this.color = new Color(byteBuf.readInt());

        this.trackedValues = DataTracker.deserializePacket(byteBuf);
    }

    public void write(PacketByteBuf byteBuf) throws IOException {
        byteBuf.writeVarInt(this.id);
        byteBuf.writeUuid(this.uuid);
        byteBuf.writeByte(this.entityTypeId);
        byteBuf.writeDouble(this.x);
        byteBuf.writeDouble(this.y);
        byteBuf.writeDouble(this.z);
        byteBuf.writeByte(this.pitch);
        byteBuf.writeByte(this.yaw);
        byteBuf.writeInt(this.entityData);
        byteBuf.writeShort(this.velocityX);
        byteBuf.writeShort(this.velocityY);
        byteBuf.writeShort(this.velocityZ);
        byteBuf.writeInt(this.color.getRGB());

        this.dataTracker.serializePacket(byteBuf);
    }

    @Override
    public boolean isErrorFatal() {
        return false;
    }

    @Environment(EnvType.CLIENT)
    public int getId() {
        return this.id;
    }

    @Environment(EnvType.CLIENT)
    public UUID getUuid() {
        return this.uuid;
    }

    @Environment(EnvType.CLIENT)
    public double getX() {
        return this.x;
    }

    @Environment(EnvType.CLIENT)
    public double getY() {
        return this.y;
    }

    @Environment(EnvType.CLIENT)
    public double getZ() {
        return this.z;
    }

    @Environment(EnvType.CLIENT)
    public int getVelocityX() {
        return this.velocityX;
    }

    @Environment(EnvType.CLIENT)
    public int getVelocityY() {
        return this.velocityY;
    }

    @Environment(EnvType.CLIENT)
    public int getVelocityz() {
        return this.velocityZ;
    }

    @Environment(EnvType.CLIENT)
    public int getPitch() {
        return this.pitch;
    }

    @Environment(EnvType.CLIENT)
    public int getYaw() {
        return this.yaw;
    }

    @Environment(EnvType.CLIENT)
    public int getEntityTypeId() {
        return this.entityTypeId;
    }

    @Environment(EnvType.CLIENT)
    public int getEntityData() {
        return this.entityData;
    }

    public void setVelocityX(int int_1) {
        this.velocityX = int_1;
    }

    public void setVelocityY(int int_1) {
        this.velocityY = int_1;
    }

    public void setVelocityZ(int int_1) {
        this.velocityZ = int_1;
    }

    @Environment(EnvType.CLIENT)
    public void setEntityData(int int_1) {
        this.entityData = int_1;
    }

    @Override
    public void apply(ClientPlayPacketListener clientPlayPacketListener_1) {
        ClientWorld world = MinecraftClient.getInstance().world;

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();

        TestEntity entity = new TestEntity(world, x, y, z);

        entity.method_18003(x, y, z);
        entity.pitch = (float)(this.getPitch() * 360) / 256.0F;
        entity.yaw = (float)(this.getYaw() * 360) / 256.0F;

        entity.setEntityId(this.getId());
        entity.setUuid(this.getUuid());
        world.method_2942(this.getId(), (Entity)entity);
    }
}
