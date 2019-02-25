package co.runed.magicmod.spell.effect;

import co.runed.brace.util.BlockUtil;
import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.effect.SpellEffect;
import co.runed.magicmod.api.spell.property.SpellProperties;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestSpellEffect extends SpellEffect {
    public float currentProgress = 0.0f;

    @Override
    public boolean build(Spell spell) {
        return true;
    }

    @Override
    public boolean run(Spell spell) {
        BlockPos position = spell.getProperty(SpellProperties.START_POSITION);
        World world = spell.getProperty(SpellProperties.WORLD);
        PlayerEntity player = (PlayerEntity) spell.getProperty(SpellProperties.CASTER);
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
    public double getManaCost(Spell spell) {
        return 0;
    }
}
