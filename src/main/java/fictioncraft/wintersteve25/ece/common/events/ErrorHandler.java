package fictioncraft.wintersteve25.ece.common.events;

import fictioncraft.wintersteve25.ece.EnhancedCelestialsEnhancement;
import fictioncraft.wintersteve25.ece.common.config.crops.JsonCropBuilder;
import fictioncraft.wintersteve25.ece.common.config.crops.JsonCropConfig;
import fictioncraft.wintersteve25.ece.common.config.entity.JsonBuilder;
import fictioncraft.wintersteve25.ece.common.config.entity.JsonConfig;
import fictioncraft.wintersteve25.ece.common.helper.MiscHelper;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.File;

public class ErrorHandler {

    public static void onPlayerLogIn(PlayerEvent.PlayerLoggedInEvent event) {
        handle(event.getPlayer(), false, null);
        handleCrop(event.getPlayer(), false, null);
    }

    public static boolean handle(PlayerEntity playerEntity, boolean isCommand, CommandSource source) {

        File cfg = new File("ece_config.json");
        JsonBuilder blood = JsonConfig.blood;
        JsonBuilder harvest = JsonConfig.harvest;

        if (!cfg.exists()) {
            sendFileNotFoundError(playerEntity, isCommand, source);
            JsonConfig.createJsonConfig();
            return false;
        }

        if (blood != null) {
            if (!blood.getBlood().isEmpty()) {
                for (JsonBuilder.JsonEntityProperty targetEntity : blood.getBlood()) {
                    EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(targetEntity.getName()));
                    if (targetEntity.getName().startsWith("*")) {
                        String modid = MiscHelper.removeChaAt(targetEntity.getName(), 0);
                        if (!ModList.get().isLoaded(modid)) {
                            sendModNotFoundError(playerEntity, isCommand, source);
                            return false;
                        }
                    }
                    if (entityType == null) {
                        sendEntityNotValidError(playerEntity, isCommand, source);
                        return false;
                    }

                    if (targetEntity.getDrop() == null) {
                        sendDropsAreNullError(playerEntity, isCommand, source);
                        return false;
                    }

                    for (JsonBuilder.JsonItemStackProperty targetItem : targetEntity.getDrop()) {
                        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(targetItem.getName()));
                        if (item == null) {
                            sendItemStackNotValidError(playerEntity, isCommand, source);
                            return false;
                        }
                    }
                }
            }
        }

        if (harvest != null) {
            if (!harvest.getHarvest().isEmpty()) {
                for (JsonBuilder.JsonEntityProperty targetEntity : harvest.getHarvest()) {
                    EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(targetEntity.getName()));
                    if (targetEntity.getName().startsWith("*")) {
                        String modid = MiscHelper.removeChaAt(targetEntity.getName(), 0);
                        if (!ModList.get().isLoaded(modid)) {
                            sendModNotFoundError(playerEntity, isCommand, source);
                            return false;
                        }
                    }
                    if (entityType == null) {
                        sendEntityNotValidError(playerEntity, isCommand, source);
                        return false;
                    }

                    if (targetEntity.getDrop() == null) {
                        sendDropsAreNullError(playerEntity, isCommand, source);
                        return false;
                    }

                    for (JsonBuilder.JsonItemStackProperty targetItem : targetEntity.getDrop()) {
                        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(targetItem.getName()));
                        if (item == null) {
                            sendItemStackNotValidError(playerEntity, isCommand, source);
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static boolean handleCrop(PlayerEntity playerEntity, boolean isCommand, CommandSource source) {

        File cfg = new File("ece_crop_config.json");
        JsonCropBuilder blood = JsonCropConfig.blood;
        JsonCropBuilder harvest = JsonCropConfig.harvest;

        if (!cfg.exists()) {
            sendFileNotFoundError(playerEntity, isCommand, source);
            JsonConfig.createJsonConfig();
            return false;
        }

        if (blood != null) {
            if (!blood.getBlood().isEmpty()) {
                for (JsonCropBuilder.JsonCropProperties crops : blood.getBlood()) {
                    if (crops == null) {
                        sendFieldEmptyError(playerEntity, isCommand, source);
                        EnhancedCelestialsEnhancement.LOGGER.info("This is the crop giving error");
                        return false;
                    }

                    if (crops.getName().isEmpty()) {
                        sendCropNotFoundError(playerEntity, isCommand, source);
                        EnhancedCelestialsEnhancement.LOGGER.info("This is the crop giving error");
                        return false;
                    }
                }
            }
        }

        if (harvest != null) {
            if (!harvest.getHarvest().isEmpty()) {
                for (JsonCropBuilder.JsonCropProperties crops : harvest.getHarvest()) {
                    if (crops == null) {
                        sendFieldEmptyError(playerEntity, isCommand, source);
                        EnhancedCelestialsEnhancement.LOGGER.info("This is the crop giving error");
                        return false;
                    }

                    if (crops.getName().isEmpty()) {
                        sendCropNotFoundError(playerEntity, isCommand, source);
                        EnhancedCelestialsEnhancement.LOGGER.info("This is the crop giving error");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void sendFileNotFoundError(PlayerEntity playerEntity, boolean isCommand, CommandSource source) {
        if (isCommand) {
            source.sendFeedback(new TranslationTextComponent("ece.reload.failed.fileNotFound"), true);
            return;
        }
        playerEntity.sendMessage(new TranslationTextComponent("ece.reload.failed.fileNotFound"), playerEntity.getUniqueID());
    }

    public static void sendFieldEmptyError(PlayerEntity playerEntity, boolean isCommand, CommandSource source) {
        if (isCommand) {
            source.sendFeedback(new TranslationTextComponent("ece.reload.failed.fieldIsEmpty"), true);
            return;
        }
        playerEntity.sendMessage(new TranslationTextComponent("ece.reload.failed.fieldIsEmpty"), playerEntity.getUniqueID());
    }

    public static void sendItemStackNotValidError(PlayerEntity playerEntity, boolean isCommand, CommandSource source) {
        if (isCommand) {
            source.sendFeedback(new TranslationTextComponent("ece.reload.failed.itemStackNotValid"), true);
            return;
        }
        playerEntity.sendMessage(new TranslationTextComponent("ece.reload.failed.itemStackNotValid"), playerEntity.getUniqueID());
    }

    public static void sendDropsAreNullError (PlayerEntity playerEntity, boolean isCommand, CommandSource source) {
        if (isCommand) {
            source.sendFeedback(new TranslationTextComponent("ece.reload.failed.dropsAreNull"), true);
            return;
        }
        playerEntity.sendMessage(new TranslationTextComponent("ece.reload.failed.dropsAreNull"), playerEntity.getUniqueID());
    }

    public static void sendEntityNotValidError(PlayerEntity playerEntity, boolean isCommand, CommandSource source) {
        if (isCommand) {
            source.sendFeedback(new TranslationTextComponent("ece.reload.failed.entityNotValid"), true);
            return;
        }
        playerEntity.sendMessage(new TranslationTextComponent("ece.reload.failed.entityNotValid"), playerEntity.getUniqueID());
    }

    public static void sendModNotFoundError(PlayerEntity playerEntity, boolean isCommand, CommandSource source) {
        if (isCommand) {
            source.sendFeedback(new TranslationTextComponent("ece.reload.failed.modNotFound"), true);
            return;
        }
        playerEntity.sendMessage(new TranslationTextComponent("ece.reload.failed.modNotFound"), playerEntity.getUniqueID());
    }

    public static void sendCropNotFoundError(PlayerEntity playerEntity, boolean isCommand, CommandSource source) {
        if (isCommand) {
            source.sendFeedback(new TranslationTextComponent("ece.reload.failed.cantFindCrop"), true);
            return;
        }
        playerEntity.sendMessage(new TranslationTextComponent("ece.reload.failed.cantFindCrop"), playerEntity.getUniqueID());
    }
}
