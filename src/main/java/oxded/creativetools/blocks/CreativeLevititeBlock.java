package oxded.creativetools.blocks;

import com.simibubi.create.foundation.block.IBE;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import oxded.creativetools.blockentities.BlockEntities;
import oxded.creativetools.blockentities.CreativeLevititeBlockEntity;

public class CreativeLevititeBlock extends Block implements IBE<CreativeLevititeBlockEntity> {
	public CreativeLevititeBlock(Properties properties) {
		super(properties);
	}

	@Override
	public Class<CreativeLevititeBlockEntity> getBlockEntityClass() {
		return CreativeLevititeBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends CreativeLevititeBlockEntity> getBlockEntityType() {
		return BlockEntities.CREATIVE_LEVITITE_BLOCK_ENTITY.get();
	}
}
