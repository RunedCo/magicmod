package co.runed.magicmod.api.spell.components;

import co.runed.brace.RecipeLibrary;
import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.recipes.MagicRecipeType;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellComponent;
import co.runed.magicmod.api.spell.SpellProperty;
import co.runed.magicmod.recipes.extraction.ExtractionRecipe;
import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExtractSpellComponent implements ISpellComponent {
    @Override
    public boolean create(ISpell spell) {
        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        World world = spell.getProperty(SpellProperty.WORLD);
        BlockPos[] positions = spell.getProperty(SpellProperty.BLOCK_POSITIONS);

        for (BlockPos pos : positions) {
            Block block = world.getBlockState(pos).getBlock();

            ExtractionRecipe recipe = null;

            for (Recipe<?> r : RecipeLibrary.getAllOfType(MagicRecipeType.EXTRACTION)) {
                ExtractionRecipe extractionRecipe = (ExtractionRecipe) r;
                if (extractionRecipe.matches(block)) {
                    recipe = extractionRecipe;

                    break;
                }
            }

            if(recipe == null) continue;

            world.breakBlock(pos, true);
        }

        return true;
    }

    @Override
    public CompoundTag toTag() {
        return null;
    }

    @Override
    public void fromTag(CompoundTag tag) {

    }
}
