package oxded.creativetools.blockentities;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsBoard;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsFormatter;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import dev.ryanhcode.sable.api.block.BlockEntitySubLevelActor;
import dev.ryanhcode.sable.api.physics.force.ForceGroups;
import dev.ryanhcode.sable.api.physics.force.ForceTotal;
import dev.ryanhcode.sable.api.physics.force.QueuedForceGroup;
import dev.ryanhcode.sable.api.physics.handle.RigidBodyHandle;
import dev.ryanhcode.sable.companion.math.JOMLConversion;
import dev.ryanhcode.sable.companion.math.Pose3d;
import dev.ryanhcode.sable.physics.config.dimension_physics.DimensionPhysicsData;
import dev.ryanhcode.sable.sublevel.ServerSubLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.joml.Vector3d;
import oxded.creativetools.CreativeTools;

import java.util.List;

public class CreativeBalloonBlockEntity extends SmartBlockEntity implements BlockEntitySubLevelActor {
	public ScrollValueBehaviour height;

	public CreativeBalloonBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntities.CREATIVE_BALLOON_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		behaviours.add(
				this.height = new ScrollValueBehaviour(Component.translatable(CreativeTools.MODID + ".creative_balloon.config_title"), this, new CreativeLevititeBlockEntity.ForceSlot()) {
					@Override
					public ValueSettingsBoard createBoard(Player player, BlockHitResult hitResult) {
						return new ValueSettingsBoard(label, max, 32, ImmutableList.of(Component.translatable(CreativeTools.MODID + ".creative_balloon.value")),
								new ValueSettingsFormatter(ValueSettings::format));
					}
				}
						.between(-64, 320)
		);
		height.setValue(64);
	}

	private final Vector3d blockCenter = JOMLConversion.atCenterOf(getBlockPos());
	private final Vector3d gravity = new Vector3d();
	private final Vector3d force = new Vector3d();
	private final Vector3d worldBlockCenter = new Vector3d();
	private double dy = 0;
	private double currentHeight = 0;

	@Override
	public void sable$physicsTick(ServerSubLevel subLevel, RigidBodyHandle handle, double timeStep) {
		QueuedForceGroup group = subLevel.getOrCreateQueuedForceGroup(ForceGroups.BALLOON_LIFT.get());
		Pose3d pose = subLevel.logicalPose();

		worldBlockCenter.set(blockCenter);
		pose.transformPosition(worldBlockCenter);

		int wantedHeight = height.getValue();
		currentHeight = worldBlockCenter.y;

		dy = currentHeight - wantedHeight;
		CreativeTools.LOGGER.info("dy {}", dy);
		dy = Mth.clamp(dy, -10, 10) / -10;
		CreativeTools.LOGGER.info("gradient {}", dy);

		force.set(dy*timeStep);

		gravity.set(DimensionPhysicsData.getGravity(level));
		pose.transformNormalInverse(gravity);
		force.add(gravity.negate());
		CreativeTools.LOGGER.info("gravity {}: {}", gravity, gravity.length());

		group.recordPointForce(blockCenter, force);
	}
}
