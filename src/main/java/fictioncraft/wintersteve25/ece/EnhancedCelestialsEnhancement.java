package fictioncraft.wintersteve25.ece;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import fictioncraft.wintersteve25.ece.common.commands.ReloadCommand;
import fictioncraft.wintersteve25.ece.common.config.Config;
import fictioncraft.wintersteve25.ece.common.config.blockdrop.JsonBlockConfig;
import fictioncraft.wintersteve25.ece.common.config.crops.JsonCropConfig;
import fictioncraft.wintersteve25.ece.common.config.entity.JsonConfig;
import fictioncraft.wintersteve25.ece.common.events.ECEEventsHandler;
import fictioncraft.wintersteve25.ece.common.events.ErrorHandler;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
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
        MinecraftForge.EVENT_BUS.addListener(ECEEventsHandler::cropGrowthEvent);
        MinecraftForge.EVENT_BUS.addListener(ECEEventsHandler::boneMealEvent);
        MinecraftForge.EVENT_BUS.addListener(ECEEventsHandler::blockBreakEvent);
        MinecraftForge.EVENT_BUS.addListener(ECEEventsHandler::serverInit);
        MinecraftForge.EVENT_BUS.addListener(ECEEventsHandler::mobExperienceDropEvent);
        MinecraftForge.EVENT_BUS.addListener(ErrorHandler::onPlayerLogIn);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);

        JsonCropConfig.createJsonConfig();
        JsonConfig.createJsonConfig();
        JsonBlockConfig.createJsonConfig();

        if (Config.PRINT_EXAMPLE.get()) {
            JsonConfig.printExample();
            JsonCropConfig.printExample();
            JsonBlockConfig.printExample();
        }
    }

    public static void registerCommands(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> requires = Commands.literal("ece").requires((commandSource) -> commandSource.hasPermissionLevel(3));
        LiteralCommandNode<CommandSource> source = dispatcher.register(requires.then(ReloadCommand.register(dispatcher)));
        dispatcher.register(Commands.literal("ece").redirect(source));
        LOGGER.info("Registered ECE Commands!");
    }
}
