package co.runed.magicmod.api;

import net.minecraft.nbt.CompoundTag;

public interface ISpellComponent {
    CompoundTag toTag();

    void fromTag(CompoundTag tag);
}
