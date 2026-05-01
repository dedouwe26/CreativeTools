package oxded.creativetools.blocks;

import com.simibubi.create.AllShapes;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import oxded.creativetools.blockentities.BlockEntities;
import oxded.creativetools.blockentities.CreativeDragBlockEntity;

public class CreativeDragBlock extends Block implements IBE<CreativeDragBlockEntity> {
	public static final VoxelShape SHAPE = new AllShapes.Builder(Block.box(2, 2, 2, 14, 14, 14))
			.add(0, 0, 0, 16, 16, 2)
			.add(0, 0, 14, 16, 16, 16)
			.build();

	public CreativeDragBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return SHAPE;
	}

	@Override
	public Class<CreativeDragBlockEntity> getBlockEntityClass() {
		return CreativeDragBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends CreativeDragBlockEntity> getBlockEntityType() {
		return BlockEntities.CREATIVE_DRAG_BLOCK_ENTITY.get();
	}
}
