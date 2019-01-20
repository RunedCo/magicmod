package co.runed.magicmod.items;

import co.runed.brace.IRegisterable;
import co.runed.magicmod.MagicMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class ItemBase extends Item implements IRegisterable {
    private String registryName;

    public ItemBase(Settings itemSettings) {
        super(itemSettings);

        this.setRegistryName("test_item");
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient) {
            World world = context.getWorld();
            BlockState state = world.getBlockState(context.getPos());
            PlayerEntity player = context.getPlayer();

            context.getPlayer().addChatMessage(new TranslatableTextComponent(state.getBlock().getTranslationKey()), true);
            //player.inventory.setInvStack(0, MagicMod.MANA_BUCKET.getDefaultStack());

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public String getRegistryName() {
        return this.registryName;
    }

    @Override
    public IRegisterable setRegistryName(String name) {
        this.registryName = name;

        return this;
    }
}
