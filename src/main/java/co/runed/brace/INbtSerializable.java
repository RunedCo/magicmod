package co.runed.brace;

import net.minecraft.nbt.CompoundTag;

public interface INbtSerializable {
    default CompoundTag toTag() {
        return new CompoundTag();
    }

    default void fromTag(CompoundTag tag) {

    }
}
