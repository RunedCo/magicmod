package co.runed.magicmod.mixin;

import co.runed.magicmod.entity.TestEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(ClientPlayNetworkHandler.class)
public class ExampleMixin {
	@ModifyConstant(method = "onEntitySpawn", constant = @Constant(nullValue = true))
	private Entity onEntitySpawn(Entity entity) {
		System.out.println(entity);

		if(entity == null) {
			return new TestEntity(MinecraftClient.getInstance().world);
		}

		return entity;
	}
}
