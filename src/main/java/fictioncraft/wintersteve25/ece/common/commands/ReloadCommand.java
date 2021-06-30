package fictioncraft.wintersteve25.ece.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import fictioncraft.wintersteve25.ece.common.config.crops.JsonCropConfig;
import fictioncraft.wintersteve25.ece.common.config.entity.JsonConfig;
import fictioncraft.wintersteve25.ece.common.events.ErrorHandler;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class ReloadCommand {
    public ReloadCommand() {
    }

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("reload").executes((cs) -> {
            return reload((CommandSource)cs.getSource());
        });
    }

    public static int reload(CommandSource source) {

        JsonConfig.read();
        JsonCropConfig.read();

        if (ErrorHandler.handle(null, true, source) && ErrorHandler.handleCrop(null, true, source)) {
            source.sendFeedback(new TranslationTextComponent("ece.reload.success"), true);
            return 1;
        }

        return 0;
    }
}
