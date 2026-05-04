package oxded.creativetools.blockentities;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import oxded.creativetools.CreativeTools;
import oxded.creativetools.blocks.Blocks;

import java.util.function.Supplier;

public class BlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
			DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreativeTools.MODID);


	public static final Supplier<BlockEntityType<CreativeLevititeBlockEntity>> CREATIVE_LEVITITE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
			"creative_levitite",
			() -> BlockEntityType.Builder.of(CreativeLevititeBlockEntity::new, Blocks.CREATIVE_LEVITITE_BLOCK.get()).build(null)
	);

	public static final Supplier<BlockEntityType<CreativeDragBlockEntity>> CREATIVE_DRAG_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
			"creative_drag_block",
			() -> BlockEntityType.Builder.of(CreativeDragBlockEntity::new, Blocks.CREATIVE_DRAG_BLOCK.get()).build(null)
	);

	public static final Supplier<BlockEntityType<CreativeBalloonBlockEntity>> CREATIVE_BALLOON_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
			"creative_balloon",
			() -> BlockEntityType.Builder.of(CreativeBalloonBlockEntity::new, Blocks.CREATIVE_BALLOON_BLOCK.get()).build(null)
	);

	public static void init(IEventBus bus) {
		BLOCK_ENTITY_TYPES.register(bus);
	}
}
