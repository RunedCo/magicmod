package co.runed.magicmod.fluid;

import co.runed.brace.registry.IRegisterable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;

public class FluidMana extends BaseFluid implements IRegisterable {

    public Fluid getFlowing() {
        return Fluids.FLOWING_WATER;
    }

    public Fluid getStill() {
        return Fluids.WATER;
    }

    @Override
    protected boolean method_15737() {
        return false;
    }

    @Override
    protected void method_15730(IWorld iWorld, BlockPos blockPos, BlockState blockState) {

    }

    @Override
    protected int method_15733(ViewableWorld viewableWorld) {
        return 0;
    }

    @Override
    protected int method_15739(ViewableWorld viewableWorld) {
        return 0;
    }

    @Environment(EnvType.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public Item getBucketItem() {
        return null;
    }

    @Override
    protected boolean method_15777(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    public int method_15789(ViewableWorld viewableWorld) {
        return 0;
    }

    @Override
    protected float getBlastResistance() {
        return 0;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return null;
    }

    @Override
    public boolean isStill(FluidState fluidState) {
        return false;
    }

    @Override
    public int method_15779(FluidState fluidState) {
        return 0;
    }

    @Override
    public String getRegistryName() {
        return "mana_fluid";
    }

    @Override
    public IRegisterable setRegistryName(String name) {
        return null;
    }
}
