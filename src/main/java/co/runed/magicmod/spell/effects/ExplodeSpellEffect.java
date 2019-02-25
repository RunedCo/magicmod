package co.runed.magicmod.spell.effects;

import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.effect.SpellEffect;
import co.runed.magicmod.api.spell.property.SpellProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ExplodeSpellEffect extends SpellEffect {
    @Override
    public boolean build(Spell spell) {
        return true;
    }

    @Override
    public boolean run(Spell spell) {
        World world = spell.getProperty(SpellProperties.WORLD);
        List<BlockPos> positions = spell.getProperty(SpellProperties.BLOCK_POSITIONS);
        float explosionStrength = spell.getProperty(SpellProperties.EXPLOSION_STRENGTH);

        for (BlockPos pos : positions) {
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), explosionStrength, true);
        }

        return true;
    }

    @Override
    public double getManaCost(Spell spell) {
        return 0;
    }
}
