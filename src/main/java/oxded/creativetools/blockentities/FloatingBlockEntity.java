package oxded.creativetools.blockentities;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import oxded.creativetools.FloatingMaterialSupplier;

public abstract class FloatingBlockEntity extends SmartBlockEntity implements FloatingMaterialSupplier {
	@Nullable
	public FloatingMaterialSupplier previous;
	public FloatingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	public boolean isLoaded() {
		return previous != null;
	}
	public void removeFloatingBlock() {
		if (!isLoaded() || level == null) return;
		FloatingMaterialSupplier.remove(level, getBlockPos(), previous);
		previous = null;
	}

	@Override
	public void remove() {
		removeFloatingBlock();
		super.remove();
	}

	public void addFloatingBlock() {
		if (isLoaded() || level == null) return;
		FloatingBlockMaterial m = getMaterial();
		FloatingBlockMaterial copy = new FloatingBlockMaterial(
				m.preventSelfLift(), m.scaleWithPressure(), m.scaleWithGravity(),
				m.liftStrength(), m.transitionSpeed(),
				m.slowVerticalFriction(), m.fastVerticalFriction(),
				m.slowHorizontalFriction(), m.fastHorizontalFriction()
		);
		double scale = getScale();
		previous = new FloatingMaterialSupplier() {
			@Override
			public FloatingBlockMaterial getMaterial() {
				return copy;
			}
			@Override
			public double getScale() {
				return scale;
			}
		};

		FloatingMaterialSupplier.add(level, getBlockPos(), this);
	}
	public void reloadFloatingBlock() {
		removeFloatingBlock();
		addFloatingBlock();
	}

	@Override
	public void setLevel(@NotNull Level level) {
		super.setLevel(level);
		addFloatingBlock();
	}
}
