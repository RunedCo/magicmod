package co.runed.magicmod.api.spell.effects;

import co.runed.brace.util.BlockUtil;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.TieredSpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestSpellEffect extends TieredSpellEffect {
    public float currentProgress = 0.0f;

    @Override
    public boolean build(ISpell spell) {
        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        BlockPos position = spell.getProperty(SpellProperty.START_POSITION);
        World world = spell.getProperty(SpellProperty.WORLD);
        PlayerEntity player = (PlayerEntity) spell.getProperty(SpellProperty.CASTER);
        BlockState state = world.getBlockState(position);

        System.out.println(ToolMaterials.DIAMOND.getBlockBreakingSpeed());

        this.currentProgress += BlockUtil.calculateBlockBreakDelta(world, position, ToolMaterials.DIAMOND);

        world.setBlockBreakingProgress(player.getEntityId(), position, (int)(this.currentProgress * 10.f));

        if(this.currentProgress >= 1.0f) {
            this.currentProgress = 0;
        }

        return true;
    }

    @Override
    public double getManaCost(ISpell spell) {
        return 0;
    }
}
