package co.runed.magicmod.api.spell.components;

import co.runed.brace.LootUtil;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellComponent;
import co.runed.magicmod.api.spell.SpellProperty;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BlockBreakSpellComponent implements ISpellComponent {
    @Override
    public boolean create(ISpell spell) {
        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        World world = spell.getProperty(SpellProperty.WORLD);
        List<BlockPos> positions = spell.getProperty(SpellProperty.BLOCK_POSITIONS);
        PlayerEntity player = (PlayerEntity)spell.getProperty(SpellProperty.ENTITY_CASTER);

        for (BlockPos pos : positions) {
            boolean dropItems = true;

            if(!world.canPlayerModifyAt(player, pos)) return false;

            if(spell.getProperty(SpellProperty.ADD_DROPS_TO_INVENTORY)) {
                dropItems = false;

                List<ItemStack> items = LootUtil.getBlockDropsAt(world, pos, player);

                for (ItemStack item : items) {
                    if(!player.inventory.insertStack(item)) {
                        return false;
                    }
                }
            }

            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }

        return true;
    }
}
