package oxded.creativetools.blocks;

import com.simibubi.create.foundation.block.IBE;
import dev.ryanhcode.sable.api.block.BlockSubLevelLiftProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import oxded.creativetools.blockentities.BlockEntities;
import oxded.creativetools.blockentities.CreativeBalloonBlockEntity;

public class CreativeBalloonBlock extends Block implements IBE<CreativeBalloonBlockEntity>, BlockSubLevelLiftProvider {
	public CreativeBalloonBlock(Properties properties) {
		super(properties);
	}

	@Override
	public Class<CreativeBalloonBlockEntity> getBlockEntityClass() {
		return CreativeBalloonBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends CreativeBalloonBlockEntity> getBlockEntityType() {
		return BlockEntities.CREATIVE_BALLOON_BLOCK_ENTITY.get();
	}

	@Override
	public @NotNull Direction sable$getNormal(BlockState state) {
		return Direction.UP;
	}

	@Override
	public float sable$getLiftScalar() {
		return BlockSubLevelLiftProvider.super.sable$getLiftScalar();
	}
}
