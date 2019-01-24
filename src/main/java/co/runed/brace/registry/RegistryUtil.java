package co.runed.brace.registry;

import co.runed.magicmod.blocks.BaseBlock;
import co.runed.magicmod.items.BaseItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.block.BlockItem;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;

public class RegistryUtil {
    private String namespace;

    public RegistryUtil(String namespace) {
        this.namespace = namespace;
    }

    public void setNamespace(String id) {
        this.namespace = id;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public <T> T register(Registry<? super T> registry, IRegisterable registerable) {
        return this.register(registry, this.namespace, registerable.getRegistryName(), (T)registerable);
    }

    public <T> T register(Registry<? super T> registry, String id, T obj) {
        return Registry.register(registry, this.namespace + ":" + id, obj);
    }

    public <T> T register(Registry<? super T> registry, String namespace, String id, T obj) {
        return Registry.register(registry, namespace + ":" + id, obj);
    }

    public <T extends Item> T registerItem(IRegisterable baseItem) {
        return this.register(Registry.ITEM, baseItem);
    }

    public <T extends Block> T registerBlock(IRegisterable baseBlock) {
        return this.register(Registry.BLOCK, baseBlock);
    }

    public BlockItem registerBlockItem(IRegisterable block) {
        BlockItem blockItem = new BlockItem((Block)block, new Item.Settings());
        return this.register(Registry.ITEM, block.getRegistryName(), blockItem);
    }

    public <S extends RecipeSerializer<Recipe<?>>> S registerRecipeSerializer(String id, S recipeSerializer_1) {
        return RecipeSerializer.register(this.namespace + ":" + id, recipeSerializer_1);
    }

    public <T extends Recipe<?>> RecipeType<T> registerRecipeType(String id) {
        return RecipeType.register(this.namespace + ":" + id);
    }

}
