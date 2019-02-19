package co.runed.magicmod.api.spell.effects;

import co.runed.brace.RecipeLibrary;
import co.runed.magicmod.api.recipe.MagicRecipeType;
import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellEffect;
import co.runed.magicmod.api.spell.SpellEffect;
import co.runed.magicmod.api.spell.SpellProperty;
import co.runed.magicmod.recipe.extraction.ExtractionRecipe;
import net.minecraft.block.Block;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ExtractSpellEffect extends SpellEffect {
    @Override
    public boolean build(ISpell spell) {
        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        World world = spell.getProperty(SpellProperty.WORLD);
        List<BlockPos> positions = spell.getProperty(SpellProperty.BLOCK_POSITIONS);

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

            world.breakBlock(pos, false);
        }

        return true;
    }

    @Override
    public double getManaCost(ISpell spell) {
        return 0;
    }
}
