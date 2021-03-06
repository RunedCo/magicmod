package co.runed.magicmod.spell.effect;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.recipe.MagicRecipeType;
import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.effect.SpellEffect;
import co.runed.magicmod.api.spell.property.SpellProperties;
import co.runed.magicmod.recipe.extraction.ExtractionRecipe;
import net.minecraft.block.Block;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ExtractSpellEffect extends SpellEffect {
    @Override
    public boolean build(Spell spell) {
        return true;
    }

    @Override
    public boolean run(Spell spell) {
        World world = spell.getProperty(SpellProperties.WORLD);
        List<BlockPos> positions = spell.getProperty(SpellProperties.BLOCK_POSITIONS);

        for (BlockPos pos : positions) {
            Block block = world.getBlockState(pos).getBlock();

            ExtractionRecipe recipe = null;

            for (Recipe<?> r : MagicMod.RECIPE_LIBRARY.getAllOfType(MagicRecipeType.EXTRACTION)) {
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
    public double getManaCost(Spell spell) {
        return 0;
    }
}
