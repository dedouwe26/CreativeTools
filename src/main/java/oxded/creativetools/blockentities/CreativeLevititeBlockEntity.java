package oxded.creativetools.blockentities;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import oxded.creativetools.CreativeTools;
import oxded.creativetools.FloatingMaterialSupplier;

import java.util.List;

public class CreativeLevititeBlockEntity extends FloatingBlockEntity implements FloatingMaterialSupplier {
	public ScrollValueBehaviour force;

	public CreativeLevititeBlockEntity(BlockPos pos, BlockState blockState) {
		super(BlockEntities.CREATIVE_LEVITITE_BLOCK_ENTITY.get(), pos, blockState);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		behaviours.add(
				force = new ScrollValueBehaviour(Component.translatable(CreativeTools.MODID+".creative_levitite.config_title"), this, new ForceSlot())
						.between(0, 256)
						.withCallback((newForce) -> reloadFloatingBlock())
		);
		force.setValue(10);
	}
	public static FloatingBlockMaterial getMaterial(int force) {
		return new FloatingBlockMaterial(
				true, false, true,
				force,
				1,
				1,
				0.05,
				0.75,
				0.025
		);
	}
	@Override
	public FloatingBlockMaterial getMaterial() {
		return getMaterial(force.getValue());
	}

	public static class ForceSlot extends ValueBoxTransform.Sided {
		@Override
		public Vec3 getLocalOffset(LevelAccessor level, BlockPos pos, BlockState state) {
			return new Vec3(.5d, direction == Direction.UP ? 13.5 / 16d : 1 - 14 / 16d, .5d);
		}

		@Override
		protected Vec3 getSouthLocation() {
			return new Vec3(.5d, .5d, 13.5 / 16d);
		}

		public float getScale() {
			return super.getScale();
		};

		@Override
		protected boolean isSideActive(BlockState state, Direction direction) {
			return direction.getAxis() == Direction.Axis.Y;
		}
	}
}
