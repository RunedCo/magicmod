package co.runed.magicmod.api.spell.effects;

import co.runed.brace.LootUtil;
import co.runed.brace.util.BlockUtil;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellEffect;
import co.runed.magicmod.api.spell.ItemTarget;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.client.network.packet.BlockBreakingProgressS2CPacket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

public class BlockBreakSpellEffect implements ISpellEffect {
    private Map<BlockPos, Float> currentProgress = new HashMap<>();

    @Override
    public boolean build(ISpell spell) {
        this.currentProgress = new HashMap<>();

        return true;
    }

    //TODO: split functionality
    @Override
    public boolean run(ISpell spell) {
        World world = spell.getProperty(SpellProperty.WORLD);
        List<BlockPos> positions = new ArrayList<>(spell.getProperty(SpellProperty.BLOCK_POSITIONS));
        ServerPlayerEntity player = (ServerPlayerEntity) spell.getProperty(SpellProperty.ENTITY_CASTER);

        List<ItemStack> items = new ArrayList<>();

        for (BlockPos pos : spell.getProperty(SpellProperty.BLOCK_POSITIONS)) {
            if (!this.currentProgress.containsKey(pos)) this.currentProgress.put(pos, 0.0f);

            float progress = this.currentProgress.get(pos);
            int index = positions.indexOf(pos);

            progress += BlockUtil.calculateBlockBreakDelta(world, pos, ToolMaterials.IRON);

            world.setBlockBreakingProgress(player.getEntityId(), pos, (int) (progress * 10.0f));
            player.networkHandler.sendPacket(new BlockBreakingProgressS2CPacket(-player.getEntityId() + index, pos, (int) (progress * 10.0f)));

            this.currentProgress.put(pos, progress);

            if (progress < 1f) {
                continue;
            }

            positions.remove(pos);

            ItemTarget dropTarget = spell.getProperty(SpellProperty.ITEM_DROP_TARGET);

            items.addAll(LootUtil.getBlockDropsAt(world, pos, player));

            world.breakBlock(pos, dropTarget == ItemTarget.NONE);
        }

        spell.setProperty(SpellProperty.DROPS, items);
        spell.setProperty(SpellProperty.BLOCK_POSITIONS, positions);

        return true;
    }
}
