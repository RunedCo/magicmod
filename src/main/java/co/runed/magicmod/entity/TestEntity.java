package co.runed.magicmod.entity;

import co.runed.magicmod.api.entity.MagicEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

import java.awt.Color;

public class TestEntity extends Entity {
    private Color color = Color.BLACK;

    public TestEntity(World world) {
        super(MagicEntityType.TEST, world);
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
}
