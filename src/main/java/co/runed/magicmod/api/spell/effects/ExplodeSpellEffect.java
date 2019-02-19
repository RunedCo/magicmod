package co.runed.magicmod.api.spell.effects;

import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellEffect;
import co.runed.magicmod.api.spell.SpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ExplodeSpellEffect extends SpellEffect {
    @Override
    public boolean build(ISpell spell) {
        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        World world = spell.getProperty(SpellProperty.WORLD);
        List<BlockPos> positions = spell.getProperty(SpellProperty.BLOCK_POSITIONS);
        float explosionStrength = spell.getProperty(SpellProperty.EXPLOSION_STRENGTH);

        for (BlockPos pos : positions) {
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), explosionStrength, true);
        }

        return true;
    }

    @Override
    public double getManaCost(ISpell spell) {
        return 0;
    }
}
