package co.runed.magicmod.api.spell.components;

import co.runed.brace.Vein;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellComponent;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VeinSpellComponent implements ISpellComponent {
    private Vein vein;

    @Override
    public boolean create(ISpell spell) {
        BlockPos startPosition = spell.getProperty(SpellProperty.START_POSITION);
        World world = spell.getProperty(SpellProperty.WORLD);

        System.out.println("BUILD " + startPosition);

        this.vein = new Vein(world, startPosition);

        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        BlockPos position = spell.getProperty(SpellProperty.START_POSITION);
        World world = spell.getProperty(SpellProperty.WORLD);
        BlockState blockState = world.getBlockState(position);
        Block block = blockState.getBlock();

        BlockPos currentPosition = vein.getNext();

        /* if(block != this.vein.getBlockType() || currentPosition == null || !this.vein.getStartPosition().equals(position)) {
            this.vein = new Vein(world, position, 3);
            currentPosition = vein.getNext();
        } */

        spell.addProperty(SpellProperty.BLOCK_POSITIONS, new BlockPos[]{ currentPosition });

        this.vein.generateVein(currentPosition);

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
