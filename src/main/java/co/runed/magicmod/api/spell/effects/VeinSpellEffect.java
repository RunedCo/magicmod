package co.runed.magicmod.api.spell.effects;

import co.runed.brace.INbtSerializable;
import co.runed.brace.Vein;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellEffect;
import co.runed.magicmod.api.spell.SpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VeinSpellEffect extends SpellEffect implements INbtSerializable {
    private Vein vein;
    private List<BlockPos> currentPositions;

    @Override
    public boolean build(ISpell spell) {
        BlockPos startPosition = spell.getProperty(SpellProperty.INITIAL_BLOCK_POSITION);
        World world = spell.getProperty(SpellProperty.WORLD);
        Double range = spell.getProperty(SpellProperty.RANGE);
        this.currentPositions = new ArrayList<>();

        this.vein = new Vein(world, startPosition, range);

        //new VeinSpellEffect().clone();

        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        int concurrentVeins = 1;

        BlockPos position = spell.getProperty(SpellProperty.INITIAL_BLOCK_POSITION);
        World world = spell.getProperty(SpellProperty.WORLD);
        BlockState blockState = world.getBlockState(position);
        Block block = blockState.getBlock();
        List<BlockPos> posList = spell.getProperty(SpellProperty.BLOCK_POSITIONS);
        Double range = spell.getProperty(SpellProperty.RANGE);

        if(!posList.isEmpty() && !Collections.disjoint(posList, currentPositions)) {
            return true;
        }

        currentPositions.clear();

        for (int i = 0; i < concurrentVeins; i++) {
            BlockPos pos = vein.getNext();

            this.vein.generateVeinAndAdd(pos);

            currentPositions.add(pos);
        }

        if(block != this.vein.getBlockType() || currentPositions.isEmpty() || !this.vein.getStartPosition().equals(position)) {
            this.vein = new Vein(world, position, range);

            this.run(spell);
        }

        spell.setProperty(SpellProperty.BLOCK_POSITIONS, currentPositions);

        return true;
    }

    @Override
    public double getManaCost(ISpell spell) {
        return 0;
    }
}
