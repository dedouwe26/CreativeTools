package oxded.creativetools.blockentities;

import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import oxded.creativetools.CreativeTools;
import oxded.creativetools.blocks.Blocks;

import java.util.function.Supplier;

public class BlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
			DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, CreativeTools.MODID);


	public static final Supplier<BlockEntityType<CreativeLevititeBlockEntity>> CREATIVE_LEVITITE = BLOCK_ENTITY_TYPES.register(
			"creative_levitite",
			() -> BlockEntityType.Builder.of(CreativeLevititeBlockEntity::new, Blocks.CREATIVE_LEVITITE.get()).build(null)
	);

	public static final Supplier<BlockEntityType<CreativeDragBlockEntity>> CREATIVE_DRAG_BLOCK = BLOCK_ENTITY_TYPES.register(
			"creative_drag_block",
			() -> BlockEntityType.Builder.of(CreativeDragBlockEntity::new, Blocks.CREATIVE_DRAG_BLOCK.get()).build(null)
	);

	public static final Supplier<BlockEntityType<CreativePropellerBlockEntity>> CREATIVE_PROPELLER = BLOCK_ENTITY_TYPES.register(
			"creative_propeller",
			() -> BlockEntityType.Builder.of(CreativePropellerBlockEntity::new, Blocks.CREATIVE_PROPELLER.get()).build(null)
	);

	public static void init(IEventBus bus) {
		BLOCK_ENTITY_TYPES.register(bus);
	}

	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(CREATIVE_PROPELLER.get(), CreativePropellerRenderer::new);
	}

	public static void clientSetup() {
		SimpleBlockEntityVisualizer.builder(CREATIVE_PROPELLER.get()).factory(CreativePropellerVisual::new).skipVanillaRender((be) -> true).apply();
	}
}
