package co.runed.magicmod.api.spell.effects;

import co.runed.magicmod.api.spell.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.List;

public class BlockDropsToInventoryEffect extends SpellEffect {
    @Override
    public boolean build(ISpell spell) {
        spell.setProperty(SpellProperty.ITEM_DROP_TARGET, ItemTarget.CASTER_INVENTORY);

        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        List<ItemStack> items = spell.getProperty(SpellProperty.DROPS);
        PlayerEntity player = (PlayerEntity)spell.getProperty(SpellProperty.ENTITY_CASTER);

        for (ItemStack item : items) {
            if (!player.inventory.insertStack(item)) {
                ItemEntity itemEntity = new ItemEntity(player.world);

                itemEntity.setStack(item);
                itemEntity.setPosition(player.getPos().getX(), player.getPos().getY(), player.getPos().getZ());
                itemEntity.setOwner(player.getUuid());

                player.getEntityWorld().spawnEntity(itemEntity);
            }
        }
        return true;
    }
}
