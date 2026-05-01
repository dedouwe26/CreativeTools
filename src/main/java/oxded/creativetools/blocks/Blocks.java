package oxded.creativetools.blocks;

import dev.eriksonn.aeronautics.Aeronautics;
import dev.simulated_team.simulated.Simulated;
import dev.simulated_team.simulated.registrate.SimulatedRegistrate;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import oxded.creativetools.CreativeTools;

public final class Blocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreativeTools.MODID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreativeTools.MODID);

	public static final DeferredBlock<CreativeLevititeBlock> CREATIVE_LEVITITE_BLOCK = BLOCKS.registerBlock(
			"creative_levitite",
			CreativeLevititeBlock::new,
			BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_PURPLE).forceSolidOn()
					.strength(-1.0F, 3600000.0F)
					.noLootTable()
					.isValidSpawn(net.minecraft.world.level.block.Blocks::never)
	);
	public static final DeferredItem<BlockItem> CREATIVE_LEVITITE_BLOCK_ITEM = ITEMS.registerItem(
			"creative_levitite",
			(p) -> new BlockItem(CREATIVE_LEVITITE_BLOCK.get(), p),
			new Item.Properties().rarity(Rarity.EPIC)
	);

	public static final DeferredBlock<CreativeDragBlock> CREATIVE_DRAG_BLOCK = BLOCKS.registerBlock(
			"creative_drag_block",
			CreativeDragBlock::new,
			BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_PURPLE).forceSolidOn()
					.strength(-1.0F, 3600000.0F)
					.noLootTable()
					.isValidSpawn(net.minecraft.world.level.block.Blocks::never)
	);
	public static final DeferredItem<BlockItem> CREATIVE_DRAG_BLOCK_ITEM = ITEMS.registerItem(
			"creative_drag_block",
			(p) -> new BlockItem(CREATIVE_DRAG_BLOCK.get(), p),
			new Item.Properties().rarity(Rarity.EPIC)
	);


	public static void init(IEventBus modEventBus) {
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
	}

	public static void commonSetup() {
		SimulatedRegistrate.TAB_ITEMS.add(CREATIVE_LEVITITE_BLOCK_ITEM::get);
		SimulatedRegistrate.ITEM_TO_SECTION.put(CREATIVE_LEVITITE_BLOCK_ITEM.getId(), Aeronautics.path("aeronautics"));
		SimulatedRegistrate.TAB_ITEMS.add(CREATIVE_DRAG_BLOCK_ITEM::get);
		SimulatedRegistrate.ITEM_TO_SECTION.put(CREATIVE_DRAG_BLOCK_ITEM.getId(), Simulated.path("simulated"));
	}
}
