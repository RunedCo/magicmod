package co.runed.brace;

import net.minecraft.nbt.CompoundTag;

public interface INbtSerializable {
    CompoundTag toTag();

    void fromTag(CompoundTag tag);
}
