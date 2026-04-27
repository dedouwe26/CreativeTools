package oxded.creativelevitite;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import dev.engine_room.flywheel.lib.transform.TransformStack;
import dev.ryanhcode.sable.Sable;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockController;
import dev.ryanhcode.sable.sublevel.ServerSubLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class CreativeLevititeBlockEntity extends SmartBlockEntity {
	public ScrollValueBehaviour force;
	public CreativeLevititeBlockEntity(BlockPos pos, BlockState blockState) {
		super(CreativeLevitite.CREATIVE_LEVITITE_BLOCK_ENTITY.get(), pos, blockState);
		force.setValue(blockState.getValue(CreativeLevititeBlock.FORCE));
		CreativeLevitite.LOGGER.info("1updated block state: {}", getBlockState().getValue(CreativeLevititeBlock.FORCE));
	}

	public void updateForce(int newForce) {
		BlockPos blockPos = getBlockPos();
		final ServerSubLevel subLevel = (ServerSubLevel) Sable.HELPER.getContaining(level, blockPos);
		if (subLevel == null) return;
		FloatingBlockController controller = subLevel.getFloatingBlockController();
		controller.queueRemoveFloatingBlock(getBlockState(), getBlockPos());
		CreativeLevitite.LOGGER.info("queued for removal");
		assert getLevel() != null;
		getLevel().setBlockAndUpdate(getBlockPos(), getBlockState().setValue(CreativeLevititeBlock.FORCE, newForce));
		CreativeLevitite.LOGGER.info("2updated block state: {}", getBlockState().getValue(CreativeLevititeBlock.FORCE));
		controller.queueAddFloatingBlock(getBlockState(), getBlockPos());
		CreativeLevitite.LOGGER.info("updated block state");
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		behaviours.add(
				force = new ScrollValueBehaviour(Component.translatable("creative_levitite.config_title"), this, new ForceSlot()).between(0, 100).withCallback(this::updateForce)
		);
	}

	public static class ForceSlot extends ValueBoxTransform {
		@Override
		public Vec3 getLocalOffset(LevelAccessor level, BlockPos pos, BlockState state) {
			return new Vec3(.5d, 13.5 / 16d, .5d);
		}
		@Override
		public void rotate(LevelAccessor level, BlockPos pos, BlockState state, PoseStack ms) {
			TransformStack.of(ms)
					.rotateXDegrees(90);
		}
		public float getScale() {
			return super.getScale();
		};
	}
}
