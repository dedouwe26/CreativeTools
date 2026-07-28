package oxded.creativetools.blockentities;

import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.eriksonn.aeronautics.content.blocks.propeller.small.SimplePropellerVisual;
import dev.eriksonn.aeronautics.content.blocks.propeller.small.andesite.AndesitePropellerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import static dev.eriksonn.aeronautics.content.blocks.propeller.small.BasePropellerBlock.REVERSED;

public class CreativePropellerVisual extends SimplePropellerVisual<CreativePropellerBlockEntity> {
    public CreativePropellerVisual(final VisualizationContext context, final CreativePropellerBlockEntity blockEntity, final float partialTick) {
        super(context, blockEntity, partialTick);
    }

    @Override
    public PartialModel getModel(final BlockState state) {
        return CreativePropellerBlockEntity.getPropellerModel(state.getValue(REVERSED));
    }

    @Override
    public float getAngle(final float partialTicks) {
        final BlockState state = this.blockEntity.getBlockState();
        final BlockPos pos = this.blockEntity.getBlockPos();
        return super.getAngle(partialTicks) + rotationOffset(state, state.getValue(AndesitePropellerBlock.FACING).getAxis(), pos);
    }
}
