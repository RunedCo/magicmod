package co.runed.magicmod.network.packet;

import co.runed.magicmod.entity.TestEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.packet.EntitySpawnClientPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.server.network.EntityTracker;
import net.minecraft.world.World;

public class MagicEntitySpawnClientPacket extends EntitySpawnClientPacket {
    /* @Override
    public void method_11178(ClientPlayPacketListener clientPlayPacketListener_1) {
        World world = MinecraftClient.getInstance().world;

        TestEntity entity = new TestEntity(world);

        if (entity_1 != null) {
            EntityTracker.method_14070((Entity)entity_1, double_1, double_2, double_3);
            ((Entity)entity_1).pitch = (float)(entitySpawnClientPacket_1.getPitch() * 360) / 256.0F;
            ((Entity)entity_1).yaw = (float)(entitySpawnClientPacket_1.getYaw() * 360) / 256.0F;
            Entity[] entitys_1 = ((Entity)entity_1).getParts();
            if (entitys_1 != null) {
                int int_1 = entitySpawnClientPacket_1.getId() - ((Entity)entity_1).getEntityId();
                Entity[] var11 = entitys_1;
                int var12 = entitys_1.length;

                for(int var13 = 0; var13 < var12; ++var13) {
                    Entity entity_3 = var11[var13];
                    entity_3.setEntityId(entity_3.getEntityId() + int_1);
                }
            }

            ((Entity)entity_1).setEntityId(entitySpawnClientPacket_1.getId());
            ((Entity)entity_1).setUuid(entitySpawnClientPacket_1.getUuid());
            this.world.method_2942(entitySpawnClientPacket_1.getId(), (Entity)entity_1);
            if (entitySpawnClientPacket_1.getEntityData() > 0) {
                if (entitySpawnClientPacket_1.getEntityTypeId() == 60 || entitySpawnClientPacket_1.getEntityTypeId() == 91 || entitySpawnClientPacket_1.getEntityTypeId() == 94) {
                    Entity entity_4 = this.world.getEntityById(entitySpawnClientPacket_1.getEntityData() - 1);
                    if (entity_4 instanceof LivingEntity && entity_1 instanceof ProjectileEntity) {
                        ProjectileEntity projectileEntity_1 = (ProjectileEntity)entity_1;
                        projectileEntity_1.setOwner(entity_4);
                        if (entity_4 instanceof PlayerEntity) {
                            projectileEntity_1.pickupType = ProjectileEntity.PickupType.PICKUP;
                            if (((PlayerEntity)entity_4).abilities.creativeMode) {
                                projectileEntity_1.pickupType = ProjectileEntity.PickupType.CREATIVE_PICKUP;
                            }
                        }
                    }
                }

                ((Entity)entity_1).setVelocityClient((double)entitySpawnClientPacket_1.getVelocityX() / 8000.0D, (double)entitySpawnClientPacket_1.getVelocityY() / 8000.0D, (double)entitySpawnClientPacket_1.getVelocityz() / 8000.0D);
            }
        }
    } */
}
