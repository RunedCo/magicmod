package co.runed.magicmod.api.spell.components;

import co.runed.brace.Vein;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellComponent;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VeinSpellComponent implements ISpellComponent {
    private Vein vein;

    @Override
    public boolean create(ISpell spell) {
        BlockPos startPosition = spell.getProperty(SpellProperty.INITIAL_BLOCK_POSITION);
        World world = spell.getProperty(SpellProperty.WORLD);
        Double range = spell.getProperty(SpellProperty.RANGE);

        System.out.println("BUILD " + startPosition);

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

        BlockPos currentPosition = vein.getNext();

        /* if(block != this.vein.getBlockType() || currentPosition == null || !this.vein.getStartPosition().equals(position)) {
            this.vein = new Vein(world, position, 3);
            currentPosition = vein.getNext();
        } */

        player.addChatMessage(new StringTextComponent("" + vein.generateFullVein(position).size()), true);

        spell.addProperty(SpellProperty.BLOCK_POSITIONS, new BlockPos[]{ currentPosition });

        this.vein.generateVeinAndAdd(currentPosition);

        return true;
    }
}
