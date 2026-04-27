package oxded.creativelevitite.mixin;

import dev.ryanhcode.sable.physics.config.block_properties.PhysicsBlockPropertyHelper;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockMaterial;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oxded.creativelevitite.CreativeLevitite;
import oxded.creativelevitite.CreativeLevititeBlock;

@Mixin(PhysicsBlockPropertyHelper.class)
public class PropertyHelperMixin {
	@Inject(method = "getFloatingMaterial", at = @At("HEAD"), cancellable = true)
	private static void getFloatingMaterial(BlockState state, CallbackInfoReturnable<FloatingBlockMaterial> cir) {
		if (state.is(CreativeLevitite.CREATIVE_LEVITITE_BLOCK.get())) {
			CreativeLevitite.LOGGER.info("got requested");
			cir.setReturnValue(new FloatingBlockMaterial(
					true,
					false,
					true,
					state.<Integer>getValue(CreativeLevititeBlock.FORCE),
					3,
					0,
					0,
					0,
					0

			));
			cir.cancel();
		}
	}
}
