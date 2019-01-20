package co.runed.magicmod.items;

import co.runed.brace.IRegisterable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
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

    @Environment(EnvType.SERVER)
    public ActionResult useOnBlock(ItemUsageContext context) {
        //if(!context.isPlayerSneaking()) {
            World world = context.getWorld();
            BlockState state = world.getBlockState(context.getPos());

            context.getPlayer().addChatMessage(new TranslatableTextComponent(state.getBlock().getTranslationKey()), true);

            return ActionResult.SUCCESS;
        //}

        //return ActionResult.PASS;
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
