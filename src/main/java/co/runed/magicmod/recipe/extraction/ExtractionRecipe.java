package co.runed.magicmod.recipe.extraction;

import co.runed.brace.util.ItemStackUtil;
import co.runed.magicmod.api.recipe.MagicRecipeSerializer;
import co.runed.magicmod.api.recipe.MagicRecipeType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

//TODO: Recipe<Block>? Recipe<World>?
public class ExtractionRecipe implements Recipe<Inventory> {
    protected final Identifier id;
    protected final String group;
    protected final Ingredient input;
    protected final Block output;
    protected final ItemStack drops;
    protected final String lootTable;
    protected final int manaMultiplier;

    public ExtractionRecipe(Identifier identifier_1, String string_1, Ingredient ingredient_1, Block outputBlock, ItemStack drops, String loot, int int_1) {
        this.id = identifier_1;
        this.group = string_1;
        this.input = ingredient_1;
        this.output = outputBlock;
        this.drops = drops;
        this.manaMultiplier = int_1;
        this.lootTable = loot;
    }

    @Override
    public boolean matches(Inventory inventory_1, World world_1) {
        return this.input.matches(inventory_1.getInvStack(0));
    }

    public boolean matches(Block block) {
        return this.input.matches(ItemStackUtil.getDefaultStack(block.getItem()));
    }

    @Override
    public ItemStack craft(Inventory inventory_1) {
        return ItemStackUtil.getDefaultStack(this.output.getItem());
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int int_1, int int_2) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return ItemStackUtil.getDefaultStack(this.output.getItem());
    }

    public boolean shouldUseLootDrops() {
        return !this.lootTable.isEmpty();
    }

    public ItemStack getDrops() {
        return this.drops.copy();
    }

    public String getLootTable() {
        return this.lootTable;
    }

    public Block getOutputBlock() {
        return this.output;
    }

    @Override
    public ItemStack getRecipeKindIcon() {
        return null;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public String getGroup() {
        return this.group;
    }

    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MagicRecipeSerializer.EXTRACTION;
    }

    public RecipeType<?> getType() {
        return MagicRecipeType.EXTRACTION;
    }
}
