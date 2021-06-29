package fictioncraft.wintersteve25.ece;

import fictioncraft.wintersteve25.ece.common.config.Config;
import fictioncraft.wintersteve25.ece.common.config.JsonConfig;
import fictioncraft.wintersteve25.ece.common.events.ECEEventsHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EnhancedCelestialsEnhancement.MODID)
public class EnhancedCelestialsEnhancement {
    public static final String MODID = "ece";
    public static Logger LOGGER = LogManager.getLogger(MODID);

    public EnhancedCelestialsEnhancement() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(ECEEventsHandler::mobSpawnEvent);
        MinecraftForge.EVENT_BUS.addListener(ECEEventsHandler::mobDropsEvent);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        if (Config.PRINT_EXAMPLE.get()) {
            JsonConfig.printExample();
        }

        JsonConfig.createJsonConfig();
        JsonConfig.read();
    }
}
