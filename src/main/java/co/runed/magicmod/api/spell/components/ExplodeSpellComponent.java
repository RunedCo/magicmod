package co.runed.magicmod.api.spell.components;

import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellComponent;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExplodeSpellComponent implements ISpellComponent {
    @Override
    public boolean create(ISpell spell) {
        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        World world = spell.getProperty(SpellProperty.WORLD);
        BlockPos[] positions = spell.getProperty(SpellProperty.BLOCK_POSITIONS);

        for (BlockPos pos : positions) {
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 5, true);
        }

        return true;
    }

    @Override
    public CompoundTag toTag() {
        return null;
    }

    @Override
    public void fromTag(CompoundTag tag) {

    }
}
