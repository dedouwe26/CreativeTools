package oxded.creativetools.mixinterfaces;

import dev.ryanhcode.sable.physics.floating_block.FloatingBlockMaterial;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import oxded.creativetools.FloatingMaterialSupplier;

public interface FloatingBlockAdder {
	void creativeLevitite$addFloatingBlock(@NotNull FloatingBlockMaterial material, double scale, Vector3d pos);
	void creativeLevitite$removeFloatingBlock(@NotNull FloatingBlockMaterial material, double scale, Vector3d pos);
	void creativeLevitite$queueAddFloatingBlock(@NotNull final FloatingMaterialSupplier supplier, final BlockPos pos);
	void creativeLevitite$queueRemoveFloatingBlock(@NotNull final FloatingMaterialSupplier supplier, final BlockPos pos);
}
