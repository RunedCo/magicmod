package co.runed.magicmod.api.spell.components;

import co.runed.brace.util.BlockUtil;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellComponent;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestSpellComponent implements ISpellComponent {
    public float currentProgress = 0.0f;

    @Override
    public boolean create(ISpell spell) {
        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        BlockPos position = spell.getProperty(SpellProperty.INITIAL_BLOCK_POSITION);
        World world = spell.getProperty(SpellProperty.WORLD);
        PlayerEntity player = (PlayerEntity) spell.getProperty(SpellProperty.ENTITY_CASTER);
        BlockState state = world.getBlockState(position);

        System.out.println(ToolMaterials.DIAMOND.getBlockBreakingSpeed());

        this.currentProgress += BlockUtil.calculateBlockBreakDelta(world, position, ToolMaterials.DIAMOND);

        world.setBlockBreakingProgress(player.getEntityId(), position, (int)(this.currentProgress * 10.f));

        if(this.currentProgress >= 1.0f) {
            this.currentProgress = 0;
        }

        return true;
    }
}