package co.runed.magicmod.api.spell.components;

import co.runed.brace.LootUtil;
import co.runed.brace.util.BlockUtil;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.client.network.packet.BlockBreakingProgressS2CPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<BlockPos> positions = spell.getProperty(SpellProperty.BLOCK_POSITIONS);
        PlayerEntity player = (PlayerEntity)spell.getProperty(SpellProperty.ENTITY_CASTER);

        ServerPlayerEntity spe = (ServerPlayerEntity)player;

        //TODO: get breaking progress to work with multiple blockpos
        for (BlockPos pos : positions) {
            boolean dropItems = true;

            if(!currentProgressMap.containsKey(pos)) continue;

            float currentBreakProgress = currentProgressMap.get(pos);

            currentBreakProgress += BlockUtil.calculateBlockBreakDelta(world, pos, ToolMaterials.DIAMOND);

            world.setBlockBreakingProgress(player.getEntityId(), pos, (int)(currentBreakProgress * 10.0f));
            spe.networkHandler.sendPacket(new BlockBreakingProgressS2CPacket(player.getEntityId(), pos, (int)(currentBreakProgress * 10.0f)));

            if(currentBreakProgress < 1f) { continue; }

            this.currentProgressMap.put(pos, currentBreakProgress);

            positions.remove(pos);
            spell.addProperty(SpellProperty.BLOCK_POSITIONS, positions);

            if(spell.getProperty(SpellProperty.ADD_DROPS_TO_INVENTORY)) {
                dropItems = false;

                List<ItemStack> items = LootUtil.getBlockDropsAt(world, pos, player);

                for (ItemStack item : items) {
                    if(!player.inventory.insertStack(item)) {
                        continue;
                    }
                }
            }

            world.breakBlock(pos, true);
        }

        spell.addProperty(SpellProperty.BLOCK_POSITIONS, new ArrayList<>());

        return true;
    }
}
