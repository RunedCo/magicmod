package co.runed.magicmod.api.spell.components;

import co.runed.brace.LootUtil;
import co.runed.brace.util.BlockUtil;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellComponent;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.packet.BlockBreakingProgressClientPacket;
import net.minecraft.client.network.packet.BlockUpdateClientPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakSpellComponent implements ISpellComponent {
    public float currentBreakProgress = 0.0f;
    public BlockPos breakingPos;

    @Override
    public boolean create(ISpell spell) {
        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        World world = spell.getProperty(SpellProperty.WORLD);
        List<BlockPos> positions = spell.getProperty(SpellProperty.BLOCK_POSITIONS);
        PlayerEntity player = (PlayerEntity)spell.getProperty(SpellProperty.ENTITY_CASTER);

        ServerPlayerEntity spe = (ServerPlayerEntity)player;

        for (BlockPos pos : positions) {
            boolean dropItems = true;

            //if(!world.canPlayerModifyAt(player, pos)) return false;

            if(breakingPos != null && !breakingPos.equals(pos)) continue;

            this.currentBreakProgress += BlockUtil.calculateBlockBreakDelta(world, pos, ToolMaterials.DIAMOND);

            world.setBlockBreakingProgress(player.getEntityId(), pos, (int)(this.currentBreakProgress * 10.0f));
            spe.networkHandler.sendPacket(new BlockBreakingProgressClientPacket(player.getEntityId(), pos, (int)(this.currentBreakProgress * 10.0f)));

            if(this.currentBreakProgress < 1f) { continue; }

            this.currentBreakProgress = 0.0f;

            if(spell.getProperty(SpellProperty.ADD_DROPS_TO_INVENTORY)) {
                dropItems = false;

                List<ItemStack> items = LootUtil.getBlockDropsAt(world, pos, player);

                for (ItemStack item : items) {
                    if(!player.inventory.insertStack(item)) {
                        return false;
                    }
                }
            }

            world.breakBlock(pos, true);
        }

        spell.addProperty(SpellProperty.BLOCK_POSITIONS, new ArrayList<>());

        return true;
    }
}
