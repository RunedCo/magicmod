package co.runed.magicmod.network.packet;

import co.runed.magicmod.entity.TestEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.packet.EntitySpawnClientPacket;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.server.network.EntityTracker;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.io.IOException;
import java.util.UUID;

public class MagicEntitySpawnClientPacket implements Packet<ClientPlayPacketListener> {
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

    public MagicEntitySpawnClientPacket() {
    }

    public MagicEntitySpawnClientPacket(Entity entity_1, int int_1) {
        this(entity_1, int_1, 0);
    }

    public MagicEntitySpawnClientPacket(Entity entity_1, int int_1, int int_2) {
        this.id = entity_1.getEntityId();
        this.uuid = entity_1.getUuid();
        this.x = entity_1.x;
        this.y = entity_1.y;
        this.z = entity_1.z;
        this.pitch = MathHelper.floor(entity_1.pitch * 256.0F / 360.0F);
        this.yaw = MathHelper.floor(entity_1.yaw * 256.0F / 360.0F);
        this.entityTypeId = int_1;
        this.entityData = int_2;
        double double_1 = 3.9D;
        this.velocityX = (int)(MathHelper.clamp(entity_1.velocityX, -3.9D, 3.9D) * 8000.0D);
        this.velocityY = (int)(MathHelper.clamp(entity_1.velocityY, -3.9D, 3.9D) * 8000.0D);
        this.velocityZ = (int)(MathHelper.clamp(entity_1.velocityZ, -3.9D, 3.9D) * 8000.0D);
    }

    public MagicEntitySpawnClientPacket(Entity entity_1, int int_1, int int_2, BlockPos blockPos_1) {
        this(entity_1, int_1, int_2);
        this.x = (double)blockPos_1.getX();
        this.y = (double)blockPos_1.getY();
        this.z = (double)blockPos_1.getZ();
    }

    public void read(PacketByteBuf packetByteBuf_1) throws IOException {
        this.id = packetByteBuf_1.readVarInt();
        this.uuid = packetByteBuf_1.readUuid();
        this.entityTypeId = packetByteBuf_1.readByte();
        this.x = packetByteBuf_1.readDouble();
        this.y = packetByteBuf_1.readDouble();
        this.z = packetByteBuf_1.readDouble();
        this.pitch = packetByteBuf_1.readByte();
        this.yaw = packetByteBuf_1.readByte();
        this.entityData = packetByteBuf_1.readInt();
        this.velocityX = packetByteBuf_1.readShort();
        this.velocityY = packetByteBuf_1.readShort();
        this.velocityZ = packetByteBuf_1.readShort();
    }

    public void write(PacketByteBuf packetByteBuf_1) throws IOException {
        packetByteBuf_1.writeVarInt(this.id);
        packetByteBuf_1.writeUuid(this.uuid);
        packetByteBuf_1.writeByte(this.entityTypeId);
        packetByteBuf_1.writeDouble(this.x);
        packetByteBuf_1.writeDouble(this.y);
        packetByteBuf_1.writeDouble(this.z);
        packetByteBuf_1.writeByte(this.pitch);
        packetByteBuf_1.writeByte(this.yaw);
        packetByteBuf_1.writeInt(this.entityData);
        packetByteBuf_1.writeShort(this.velocityX);
        packetByteBuf_1.writeShort(this.velocityY);
        packetByteBuf_1.writeShort(this.velocityZ);
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

        double double_1 = this.getX();
        double double_2 = this.getY();
        double double_3 = this.getZ();

        TestEntity entity = new TestEntity(world);

        if (entity != null) {
            EntityTracker.method_14070((Entity)entity, double_1, double_2, double_3);
            ((Entity)entity).pitch = (float)(this.getPitch() * 360) / 256.0F;
            ((Entity)entity).yaw = (float)(this.getYaw() * 360) / 256.0F;
            Entity[] entitys_1 = ((Entity)entity).getParts();
            if (entitys_1 != null) {
                int int_1 = this.getId() - ((Entity)entity).getEntityId();
                Entity[] var11 = entitys_1;
                int var12 = entitys_1.length;

                for(int var13 = 0; var13 < var12; ++var13) {
                    Entity entity_3 = var11[var13];
                    entity_3.setEntityId(entity_3.getEntityId() + int_1);
                }
            }

            ((Entity)entity).setEntityId(this.getId());
            ((Entity)entity).setUuid(this.getUuid());
            world.method_2942(this.getId(), (Entity)entity);
        }
    }
}
