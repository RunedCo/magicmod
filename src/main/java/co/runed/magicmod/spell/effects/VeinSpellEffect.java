package co.runed.magicmod.spell.effects;

import co.runed.brace.INbtSerializable;
import co.runed.brace.Vein;
import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellEffect;
import co.runed.magicmod.api.spell.SpellProperties;
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
    public boolean build(Spell spell) {
        BlockPos startPosition = spell.getProperty(SpellProperties.START_POSITION);
        World world = spell.getProperty(SpellProperties.WORLD);
        Double range = spell.getProperty(SpellProperties.RANGE);
        this.currentPositions = new ArrayList<>();

        this.vein = new Vein(world, startPosition, range);

        //new VeinSpellEffect().clone();

        return true;
    }

    @Override
    public boolean run(Spell spell) {
        int concurrentVeins = 1;

        BlockPos position = spell.getProperty(SpellProperties.START_POSITION);
        World world = spell.getProperty(SpellProperties.WORLD);
        BlockState blockState = world.getBlockState(position);
        Block block = blockState.getBlock();
        List<BlockPos> posList = spell.getProperty(SpellProperties.BLOCK_POSITIONS);
        Double range = spell.getProperty(SpellProperties.RANGE);

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

        spell.setProperty(SpellProperties.BLOCK_POSITIONS, currentPositions);

        return true;
    }

    @Override
    public double getManaCost(Spell spell) {
        return 0;
    }
}
