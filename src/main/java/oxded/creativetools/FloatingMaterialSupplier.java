package oxded.creativetools;

import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockController;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockMaterial;
import dev.ryanhcode.sable.sublevel.ServerSubLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import oxded.creativetools.mixin.ContainerAccessor;
import oxded.creativetools.mixinterfaces.FloatingBlockAdder;

public interface FloatingMaterialSupplier {
	static void remove(Level level, BlockPos pos, FloatingMaterialSupplier oldMaterial) {
		if (!(level instanceof ServerLevel serverLevel)) return;
		ServerSubLevel subLevel = (ServerSubLevel) Sable.HELPER.getContaining(serverLevel, pos);
		if (subLevel == null) return;
		FloatingBlockController controller = subLevel.getFloatingBlockController();
		((FloatingBlockAdder) ((ContainerAccessor)controller).getSublevelContainer()).creativeLevitite$queueRemoveFloatingBlock(oldMaterial, pos);
	}
	static void add(Level level, BlockPos pos, @NotNull FloatingMaterialSupplier newMaterial) {
		if (!(level instanceof ServerLevel serverLevel)) return;
		ServerSubLevel subLevel = (ServerSubLevel) Sable.HELPER.getContaining(serverLevel, pos);
		if (subLevel == null) return;
		FloatingBlockController controller = subLevel.getFloatingBlockController();
		((FloatingBlockAdder) ((ContainerAccessor)controller).getSublevelContainer()).creativeLevitite$queueAddFloatingBlock(newMaterial, pos);
	}

	FloatingBlockMaterial getMaterial();
	default double getScale() {
		return 1;
	}
}
