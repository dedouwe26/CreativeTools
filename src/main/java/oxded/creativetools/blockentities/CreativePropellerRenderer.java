package oxded.creativetools.blockentities;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.eriksonn.aeronautics.content.blocks.propeller.small.SimplePropellerRenderer;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import oxded.creativetools.CreativeTools;

import static dev.eriksonn.aeronautics.content.blocks.propeller.small.BasePropellerBlock.REVERSED;

public class CreativePropellerRenderer extends SimplePropellerRenderer<CreativePropellerBlockEntity> {
    public CreativePropellerRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public PartialModel getCurrentModel(final CreativePropellerBlockEntity be) {
        return CreativePropellerBlockEntity.getPropellerModel(be.getBlockState().getValue(REVERSED));
    }

    @Override
    public float getAngle(final float partialTicks, final Direction dir, final CreativePropellerBlockEntity be) {
        return super.getAngle(partialTicks, dir, be) + getRotationOffsetForPosition(be, be.getBlockPos(), dir.getAxis());
    }

    @Override
    protected SuperByteBuffer getRotatedModel(CreativePropellerBlockEntity be, BlockState state) {
        return null;
    }
}
