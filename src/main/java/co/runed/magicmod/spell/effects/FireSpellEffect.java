package co.runed.magicmod.spell.effects;

import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

//TODO: fix glitchy fire
public class FireSpellEffect extends SpellEffect {
    @Override
    public boolean build(Spell spell) {
        return true;
    }

    @Override
    public boolean run(Spell spell) {
        World world = spell.getProperty(SpellProperty.WORLD);
        List<BlockPos> positions = spell.getProperty(SpellProperty.BLOCK_POSITIONS);

        for (BlockPos pos : positions) {
            BlockPos aboveBlock = pos.offset(Direction.UP).add(0, 1, 0);

            BlockState fireState = ((FireBlock) Blocks.FIRE).getStateForPosition(world, aboveBlock);

            if(fireState.canPlaceAt(world, aboveBlock)) {
                world.setBlockState(aboveBlock, fireState, 11);
            }
        }

        return true;
    }

    @Override
    public double getManaCost(Spell spell) {
        return 0;
    }
}
