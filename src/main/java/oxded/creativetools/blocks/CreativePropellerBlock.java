package oxded.creativetools.blocks;

import com.simibubi.create.foundation.block.IBE;
import dev.eriksonn.aeronautics.content.blocks.propeller.small.BasePropellerBlock;
import dev.eriksonn.aeronautics.content.blocks.propeller.small.BasePropellerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import oxded.creativetools.blockentities.BlockEntities;
import oxded.creativetools.blockentities.CreativePropellerBlockEntity;

public class CreativePropellerBlock extends BasePropellerBlock {
    public CreativePropellerBlock(final BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends BasePropellerBlockEntity> getBlockEntityType() {
        return BlockEntities.CREATIVE_PROPELLER.get();
    }
}
