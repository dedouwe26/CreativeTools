package oxded.creativetools;

import net.neoforged.fml.common.EventBusSubscriber;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import oxded.creativetools.blockentities.BlockEntities;
import oxded.creativetools.blocks.Blocks;

@Mod(CreativeTools.MODID)
@EventBusSubscriber(modid = CreativeTools.MODID)
public class CreativeTools {
    public static final String MODID = "creative_tools";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreativeTools(IEventBus modEventBus, ModContainer modContainer) {
        Blocks.init(modEventBus);
        BlockEntities.init(modEventBus);
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        Blocks.commonSetup();
    }
}
