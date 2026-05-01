package oxded.creativetools.mixin;

import dev.ryanhcode.sable.physics.floating_block.FloatingBlockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FloatingBlockData.class)
public interface BlockCountAccessor {
	@Accessor
	int getBlockCount();
}
