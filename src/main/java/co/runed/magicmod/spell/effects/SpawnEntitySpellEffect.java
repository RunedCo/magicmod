package co.runed.magicmod.spell.effects;

import co.runed.magicmod.api.spell.Spell;
import co.runed.magicmod.api.spell.SpellEffect;
import co.runed.magicmod.api.spell.SpellProperties;
import co.runed.magicmod.entity.TestEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class SpawnEntitySpellEffect extends SpellEffect {
    @Override
    public boolean build(Spell spell) {
        return true;
    }

    @Override
    public boolean run(Spell spell) {
        World world = spell.getProperty(SpellProperties.WORLD);
        PlayerEntity player = (PlayerEntity) spell.getProperty(SpellProperties.CASTER);
        double range = spell.getProperty(SpellProperties.RANGE);

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

    @Override
    public double getManaCost(Spell spell) {
        return 0;
    }


    protected final Vec3d getVectorForRotation(final float pitch, final float yaw) {
        final float f = MathHelper.cos(-yaw * 0.017453292f - 3.1415927f);
        final float f2 = MathHelper.sin(-yaw * 0.017453292f - 3.1415927f);
        final float f3 = -MathHelper.cos(-pitch * 0.017453292f);
        final float f4 = MathHelper.sin(-pitch * 0.017453292f);
        return new Vec3d(f2 * f3, f4, f * f3);
    }
}
