package oxded.creativetools.blockentities;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.eriksonn.aeronautics.content.blocks.propeller.small.BasePropellerBlockEntity;
import dev.eriksonn.aeronautics.index.AeroPartialModels;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import oxded.creativetools.CreativeTools;

public class CreativePropellerBlockEntity extends BasePropellerBlockEntity {
    public static PartialModel PROPELLER = AeroPartialModels.ANDESITE_PROPELLER;

    public static PartialModel PROPELLER_REVERSED = AeroPartialModels.ANDESITE_PROPELLER_REVERSED;

    public static PartialModel getPropellerModel(boolean reversed) {
        return reversed ? PROPELLER_REVERSED : PROPELLER;
    }

    public CreativePropellerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntities.CREATIVE_PROPELLER.get(), pos, state);
    }
    @Override
    public double getConfigThrust() {
        return 1;
    }

    @Override
    public double getConfigAirflow() {
        return 1;
    }

    @Override
    public float getRadius() {
        return 1;
    }

    @Override
    public float getOffset() {
        return 3 / 16f;
    }
}
