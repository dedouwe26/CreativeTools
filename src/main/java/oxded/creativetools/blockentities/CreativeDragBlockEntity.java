package oxded.creativetools.blockentities;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import oxded.creativetools.CreativeTools;
import oxded.creativetools.FloatingMaterialSupplier;

import java.util.List;
import java.util.function.Consumer;

public class CreativeDragBlockEntity extends FloatingBlockEntity implements FloatingMaterialSupplier {
	public ScrollValueBehaviour transition;

	public CreativeDragBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntities.CREATIVE_DRAG_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		behaviours.add(
				transition = new ScrollValueBehaviour(Component.translatable(CreativeTools.MODID+".creative_drag_block.transition"), this, new Transition())
						.between(0, 64)
						.withCallback((v) -> reloadFloatingBlock())
		);
		transition.setValue(3);
	}
	public static FloatingBlockMaterial getMaterial(int horizontal, int vertical, int transition) {
		return new FloatingBlockMaterial(
				true, false, true, 0,
				transition,
				1.5,
				0.1,
				1,
				0.05

		);
	}
	@Override
	public FloatingBlockMaterial getMaterial() {
		return getMaterial(transition.getValue(), transition.getValue(), transition.getValue());
	}
	public static class Transition extends ValueBoxTransform.Sided {
		@Override
		protected Vec3 getSouthLocation() {
			return new Vec3(.5d, .5d, 14 / 16d);
		}

		@Override
		protected boolean isSideActive(BlockState state, Direction direction) {
			return direction.getAxis() == Direction.Axis.Z;
		}
	}
}
