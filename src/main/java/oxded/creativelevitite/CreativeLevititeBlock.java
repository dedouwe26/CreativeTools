package oxded.creativelevitite;

import com.simibubi.create.content.logistics.crate.CrateBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CreativeLevititeBlock extends CrateBlock implements IBE<CreativeLevititeBlockEntity> {

	public static final IntegerProperty FORCE = IntegerProperty.create("force", 0, 100);

	public CreativeLevititeBlock(Properties properties) {
		super(properties);
		registerDefaultState(
				super.getStateDefinition().any().setValue(FORCE, 10)
		);
	}

	@Override
	public Class<CreativeLevititeBlockEntity> getBlockEntityClass() {
		return CreativeLevititeBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends CreativeLevititeBlockEntity> getBlockEntityType() {
		return CreativeLevitite.CREATIVE_LEVITITE_BLOCK_ENTITY.get();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FORCE);
	}

//	@Override
//	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
//		if (level.isClientSide) return;
//		CreativeLevititeBlockEntity.BLOCK_MAP.get((ServerLevel) level).remove(getBlockEntity(level, pos));
//		if (CreativeLevititeBlockEntity.BLOCK_MAP.get(level).isEmpty()) {
//			CreativeLevititeBlockEntity.BLOCK_MAP.remove(level);
//		}
//	}
//
//	@Override
//	protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
//		if (level.isClientSide) return;
//		if (!CreativeLevititeBlockEntity.BLOCK_MAP.containsKey((ServerLevel) level)) {
//			CreativeLevititeBlockEntity.BLOCK_MAP.put((ServerLevel) level, new ArrayList<>(1));
//		}
//		CreativeLevititeBlockEntity.BLOCK_MAP.get(level).add(getBlockEntity(level, pos));
//
//	}
}
