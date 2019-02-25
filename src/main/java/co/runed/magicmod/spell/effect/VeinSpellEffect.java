package co.runed.magicmod.spell.effect;

import co.runed.brace.INbtSerializable;
import co.runed.brace.world.VeinBlockArea;
import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.effect.SpellEffect;
import co.runed.magicmod.api.spell.property.SpellProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VeinSpellEffect extends SpellEffect implements INbtSerializable {
    private VeinBlockArea veinBlockArea;
    private List<BlockPos> currentPositions;

    @Override
    public boolean build(Spell spell) {
        BlockPos startPosition = spell.getProperty(SpellProperties.START_POSITION);
        World world = spell.getProperty(SpellProperties.WORLD);
        Double range = spell.getProperty(SpellProperties.RANGE);
        this.currentPositions = new ArrayList<>();

        this.veinBlockArea = new VeinBlockArea(world, startPosition, range);

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
            BlockPos pos = veinBlockArea.next();

            this.veinBlockArea.generateAndAdd(pos);

            currentPositions.add(pos);
        }

        if(block != this.veinBlockArea.getBlockType() || currentPositions.isEmpty() || !this.veinBlockArea.getStartPosition().equals(position)) {
            this.veinBlockArea = new VeinBlockArea(world, position, range);

            this.run(spell);
        }

        spell.putProperty(SpellProperties.BLOCK_POSITIONS, currentPositions);

        return true;
    }

    @Override
    public double getManaCost(Spell spell) {
        return 0;
    }
}
