package oxded.creativetools.mixin;

import dev.ryanhcode.sable.physics.floating_block.FloatingBlockController;
import dev.ryanhcode.sable.physics.floating_block.FloatingClusterContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FloatingBlockController.class)
public interface ContainerAccessor {
	@Accessor
	FloatingClusterContainer getSublevelContainer();
}
