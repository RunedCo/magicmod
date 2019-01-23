package co.runed.magicmod.api.spell;

import net.minecraft.nbt.CompoundTag;

public interface ISpellComponent {
    CompoundTag toTag();

    void fromTag(CompoundTag tag);
}
