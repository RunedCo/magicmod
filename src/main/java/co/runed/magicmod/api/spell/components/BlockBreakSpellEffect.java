package co.runed.magicmod.api.spell.components;

import co.runed.brace.LootUtil;
import co.runed.brace.util.BlockUtil;
import co.runed.magicmod.api.item.MagicToolMaterials;
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
    private Map<BlockPos, Float> currentProgressMap = new HashMap<>();

    @Override
    public boolean create(ISpell spell) {
        this.currentProgressMap = new HashMap<>();

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
            if (!currentProgressMap.containsKey(pos)) currentProgressMap.put(pos, 0.0f);

            float currentBreakProgress = currentProgressMap.get(pos);

            currentBreakProgress += BlockUtil.calculateBlockBreakDelta(world, pos, ToolMaterials.IRON);

            world.setBlockBreakingProgress(player.getEntityId(), pos, (int) (currentBreakProgress * 10.0f));
            player.networkHandler.sendPacket(new BlockBreakingProgressS2CPacket(player.getEntityId() + 100 + positions.indexOf(pos), pos, (int) (currentBreakProgress * 10.0f)));

            this.currentProgressMap.put(pos, currentBreakProgress);

            if (currentBreakProgress < 1f) {
                continue;
            }

            positions.remove(pos);

            ItemTarget dropTarget = spell.getProperty(SpellProperty.ITEM_DROP_TARGET);

            items.addAll(LootUtil.getBlockDropsAt(world, pos, player));

            world.breakBlock(pos, dropTarget == ItemTarget.NONE);
        }

        spell.addProperty(SpellProperty.DROPS, items);
        spell.addProperty(SpellProperty.BLOCK_POSITIONS, positions);

        return true;
    }
}
