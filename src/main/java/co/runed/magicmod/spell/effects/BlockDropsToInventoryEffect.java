package co.runed.magicmod.spell.effects;

import co.runed.magicmod.api.spell.ItemTarget;
import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellEffect;
import co.runed.magicmod.api.spell.SpellProperties;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.List;

public class BlockDropsToInventoryEffect extends SpellEffect {
    @Override
    public boolean build(Spell spell) {
        spell.putProperty(SpellProperties.ITEM_TARGET, ItemTarget.CASTER_INVENTORY);

        return true;
    }

    @Override
    public boolean run(Spell spell) {
        List<ItemStack> items = spell.getProperty(SpellProperties.DROPS);
        PlayerEntity player = (PlayerEntity)spell.getProperty(SpellProperties.CASTER);

        for (ItemStack item : items) {
            if (!player.inventory.insertStack(item)) {
                ItemEntity itemEntity = new ItemEntity(player.world, player.getPos().getX(), player.getPos().getY(), player.getPos().getZ());

                itemEntity.setStack(item);
                itemEntity.setOwner(player.getUuid());

                player.getEntityWorld().spawnEntity(itemEntity);
            }
        }
        return true;
    }

    @Override
    public double getManaCost(Spell spell) {
        return 10;
    }
}
