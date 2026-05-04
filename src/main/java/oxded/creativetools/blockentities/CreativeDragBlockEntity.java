package oxded.creativetools.blockentities;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsBoard;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueSettingsFormatter;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import dev.ryanhcode.sable.physics.floating_block.FloatingBlockMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import oxded.creativetools.CreativeTools;
import oxded.creativetools.FloatingMaterialSupplier;
import oxded.creativetools.blocks.CreativeDragBlock;

import java.util.List;

public class CreativeDragBlockEntity extends FloatingBlockEntity implements FloatingMaterialSupplier {
	public ScrollValueBehaviour transition;

	public CreativeDragBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntities.CREATIVE_DRAG_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		behaviours.add(
				transition = new ScrollValueBehaviour(Component.translatable(CreativeTools.MODID+".creative_drag_block.transition"), this, new Transition()) {
					@Override
					public ValueSettingsBoard createBoard(Player player, BlockHitResult hitResult) {
						return new ValueSettingsBoard(label, max, 10, ImmutableList.of(Component.translatable(CreativeTools.MODID+".creative_drag_block.value")),
								new ValueSettingsFormatter(ValueSettings::format));
					}
				}
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
	public static class Transition extends CreativeLevititeBlockEntity.ForceSlot {
		@Override
		protected boolean isSideActive(BlockState state, Direction direction) {
			return direction.getAxis() == state.getValue(CreativeDragBlock.FACING).getAxis();
		}
	}
}
