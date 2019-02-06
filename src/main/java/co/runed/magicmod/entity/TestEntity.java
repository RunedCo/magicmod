package co.runed.magicmod.entity;

import co.runed.magicmod.api.entity.MagicEntityType;
import co.runed.magicmod.network.packet.SymbolEntitySpawnClientPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.awt.Color;

public class TestEntity extends Entity {
    private Color color = Color.BLACK;

    public TestEntity(World world) {
        super(MagicEntityType.TEST, world);
    }

    public TestEntity(World world, double x, double y, double z) {
        this(world);

        this.setPosition(x, y, z);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public boolean isUnaffectedByGravity() {
        return true;
    }

    @Override
    protected void readCustomDataFromTag(CompoundTag var1) {

    }

    @Override
    protected void writeCustomDataToTag(CompoundTag var1) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new SymbolEntitySpawnClientPacket(this);
    }
}
