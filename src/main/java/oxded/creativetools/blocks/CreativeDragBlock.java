package oxded.creativetools.blocks;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.AllShapes;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import oxded.creativetools.blockentities.BlockEntities;
import oxded.creativetools.blockentities.CreativeDragBlockEntity;

public class CreativeDragBlock extends HorizontalDirectionalBlock implements IBE<CreativeDragBlockEntity> {
	public static final VoxelShape SHAPE_BASE = Block.box(2, 2, 2, 14, 14, 14);
	public static final VoxelShape SHAPE_Z = new AllShapes.Builder(SHAPE_BASE)
			.add(0, 0, 0, 16, 16, 2)
			.add(0, 0, 14, 16, 16, 16)
			.build();
	public static final VoxelShape SHAPE_X = new AllShapes.Builder(SHAPE_BASE)
			.add(0, 0, 0, 2, 16, 16)
			.add(14, 0, 0, 16, 16, 16)
			.build();

	public static final MapCodec<CreativeDragBlock> CODEC = simpleCodec(CreativeDragBlock::new);

	public CreativeDragBlock(Properties properties) {
		super(properties);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		final Direction dir = state.getValue(FACING);
		return dir.getAxis() == Direction.Axis.X ? SHAPE_X : SHAPE_Z;
	}

	@Override
	public Class<CreativeDragBlockEntity> getBlockEntityClass() {
		return CreativeDragBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends CreativeDragBlockEntity> getBlockEntityType() {
		return BlockEntities.CREATIVE_DRAG_BLOCK_ENTITY.get();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder.add(FACING));
	}
}
