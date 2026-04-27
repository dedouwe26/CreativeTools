package oxded.creativelevitite;

import dev.eriksonn.aeronautics.Aeronautics;
import dev.eriksonn.aeronautics.content.components.Levitating;
import dev.eriksonn.aeronautics.index.AeroDataComponents;
import dev.ryanhcode.sable.platform.SableEventPlatform;
import dev.ryanhcode.sable.sublevel.system.SubLevelPhysicsSystem;
import dev.simulated_team.simulated.Simulated;
import dev.simulated_team.simulated.registrate.SimulatedRegistrate;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredItem;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@Mod(CreativeLevitite.MODID)
@EventBusSubscriber(modid = CreativeLevitite.MODID)
public class CreativeLevitite {
    public static final String MODID = "creative_levitite";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredBlock<Block> CREATIVE_LEVITITE_BLOCK = BLOCKS.registerBlock(
            "creative_levitite",
            CreativeLevititeBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE).forceSolidOn()
                    .strength(-1.0F, 3600000.0F)
                    .noLootTable()
                    .isValidSpawn(Blocks::never)
    );
    public static final DeferredItem<BlockItem> CREATIVE_LEVITITE_BLOCK_ITEM = ITEMS.registerItem(
            "creative_levitite",
            (p) -> new BlockItem(CREATIVE_LEVITITE_BLOCK.get(), p),
            new Item.Properties().rarity(Rarity.EPIC)
	);
    public static final Supplier<BlockEntityType<CreativeLevititeBlockEntity>> CREATIVE_LEVITITE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register(
            "creative_levitite",
            () -> BlockEntityType.Builder.of(CreativeLevititeBlockEntity::new, CREATIVE_LEVITITE_BLOCK.get()).build(null)
    );

    public CreativeLevitite(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        SimulatedRegistrate.TAB_ITEMS.add(CREATIVE_LEVITITE_BLOCK_ITEM::get);
        SimulatedRegistrate.ITEM_TO_SECTION.put(CREATIVE_LEVITITE_BLOCK_ITEM.getId(), Aeronautics.path("aeronautics"));
    }
}
