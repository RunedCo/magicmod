package co.runed.magicmod.api.spell.components;

import co.runed.brace.INbtSerializable;
import co.runed.brace.Vein;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class VeinSpellEffect implements ISpellEffect, INbtSerializable {
    private Vein vein;
    private BlockPos currentPosition;

    @Override
    public boolean create(ISpell spell) {
        BlockPos startPosition = spell.getProperty(SpellProperty.INITIAL_BLOCK_POSITION);
        World world = spell.getProperty(SpellProperty.WORLD);
        Double range = spell.getProperty(SpellProperty.RANGE);

        this.vein = new Vein(world, startPosition, range);

        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        BlockPos position = spell.getProperty(SpellProperty.INITIAL_BLOCK_POSITION);
        World world = spell.getProperty(SpellProperty.WORLD);
        PlayerEntity player = (PlayerEntity) spell.getProperty(SpellProperty.ENTITY_CASTER);
        BlockState blockState = world.getBlockState(position);
        Block block = blockState.getBlock();
        List<BlockPos> posList = spell.getProperty(SpellProperty.BLOCK_POSITIONS);

        if(!posList.isEmpty() && posList.contains(this.currentPosition)) {
            return true;
        }

        currentPosition = vein.getNext();

        if(block != this.vein.getBlockType() || currentPosition == null || !this.vein.getStartPosition().equals(position)) {
            this.vein = new Vein(world, position, 3);
            currentPosition = vein.getNext();
        }

        player.addChatMessage(new StringTextComponent("" + vein.generateFullVein(position).size()), true);

        spell.addProperty(SpellProperty.BLOCK_POSITIONS, Arrays.asList(currentPosition));

        this.vein.generateVeinAndAdd(currentPosition);

        return true;
    }
}
