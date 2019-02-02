package co.runed.magicmod.api.spell.components;

import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.ISpellComponent;
import co.runed.magicmod.api.spell.SpellProperty;
import co.runed.magicmod.entity.TestEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class SpawnEntitySpellComponent implements ISpellComponent {
    @Override
    public boolean create(ISpell spell) {
        return true;
    }

    @Override
    public boolean run(ISpell spell) {
        World world = spell.getProperty(SpellProperty.WORLD);
        PlayerEntity player = (PlayerEntity) spell.getProperty(SpellProperty.ENTITY_CASTER);
        double range = spell.getProperty(SpellProperty.RANGE);

        TestEntity entity = new TestEntity(world);

        Vec3d vec = new Vec3d(player.x, player.y, player.z).add(0, player.getEyeHeight(), 0);
        Vec3d look = this.getVectorForRotation(player.pitch, player.yaw);
        Vec3d end = vec.add(look.normalize().multiply(range));

        //return world.rayTrace(new RayTraceContext(vec3d_1, vec3d_2, RayTraceContext.ShapeType.COLLIDER, RayTraceContext.FluidHandling.NONE, player)).getType() == HitResult.Type.NONE;

        HitResult result = world.rayTrace(new RayTraceContext(vec, end, RayTraceContext.ShapeType.OUTLINE, RayTraceContext.FluidHandling.NONE, player));

        entity.setPosition(result.getPos().x, result.getPos().y + 3, result.getPos().z);
        //entity.setStack(new ItemStack(Items.FIRE_CHARGE));

        //entity.setPickupDelayInfinite();
        //entity.setUnaffectedByGravity(true);
        world.spawnEntity(entity);

        return true;
    }


    protected final Vec3d getVectorForRotation(final float pitch, final float yaw) {
        final float f = MathHelper.cos(-yaw * 0.017453292f - 3.1415927f);
        final float f2 = MathHelper.sin(-yaw * 0.017453292f - 3.1415927f);
        final float f3 = -MathHelper.cos(-pitch * 0.017453292f);
        final float f4 = MathHelper.sin(-pitch * 0.017453292f);
        return new Vec3d(f2 * f3, f4, f * f3);
    }
}
