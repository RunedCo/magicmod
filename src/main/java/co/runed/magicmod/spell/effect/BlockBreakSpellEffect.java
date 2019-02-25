package co.runed.magicmod.spell.effect;

import co.runed.brace.LootUtil;
import co.runed.brace.util.BlockUtil;
import co.runed.magicmod.api.item.MagicToolMaterials;
import co.runed.magicmod.api.spell.ItemTarget;
import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.effect.SpellEffect;
import co.runed.magicmod.api.spell.property.SpellProperties;
import net.minecraft.client.network.packet.BlockBreakingProgressS2CPacket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockBreakSpellEffect extends SpellEffect {
    private Map<BlockPos, Float> currentProgress = new HashMap<>();

    ToolMaterial material;

    @Override
    public boolean build(Spell spell) {
        this.currentProgress = new HashMap<>();

        this.material = this.tierToMaterial();

        return true;
    }

    private ToolMaterial tierToMaterial() {
        switch (this.getTier()) {
            default:
            case 1:
                return ToolMaterials.DIAMOND;
            case 2:
                return ToolMaterials.GOLD;
            case 3:
                return MagicToolMaterials.INSTANT;
        }
    }

    //TODO: split functionality
    @Override
    public boolean run(Spell spell) {
        World world = spell.getProperty(SpellProperties.WORLD);
        List<BlockPos> positions = new ArrayList<>(spell.getProperty(SpellProperties.BLOCK_POSITIONS));
        ServerPlayerEntity player = (ServerPlayerEntity) spell.getProperty(SpellProperties.CASTER);

        List<ItemStack> items = new ArrayList<>();

        for (BlockPos pos : spell.getProperty(SpellProperties.BLOCK_POSITIONS)) {
            if (!this.currentProgress.containsKey(pos)) this.currentProgress.put(pos, 0.0f);

            float progress = this.currentProgress.get(pos);
            int index = positions.indexOf(pos);

            progress += BlockUtil.calculateBlockBreakDelta(world, pos, this.material);

            world.setBlockBreakingProgress(player.getEntityId(), pos, (int) (progress * 10.0f));
            player.networkHandler.sendPacket(new BlockBreakingProgressS2CPacket(-player.getEntityId() + index, pos, (int) (progress * 10.0f)));

            this.currentProgress.put(pos, progress);

            if (progress < 1f) {
                continue;
            }

            positions.remove(pos);

            ItemTarget dropTarget = spell.getProperty(SpellProperties.ITEM_TARGET);

            items.addAll(LootUtil.getBlockDropsAt(world, pos, player));

            world.breakBlock(pos, dropTarget == ItemTarget.NONE);
        }

        spell.putProperty(SpellProperties.DROPS, items);
        spell.putProperty(SpellProperties.BLOCK_POSITIONS, positions);

        return true;
    }

    @Override
    public double getManaCost(Spell spell) {
        double tierCost = super.getManaCost(spell);
        double speedCost = spell.getProperty(SpellProperties.SPEED) * 10;

        return tierCost + speedCost;
    }
}
