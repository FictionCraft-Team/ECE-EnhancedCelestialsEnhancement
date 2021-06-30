package fictioncraft.wintersteve25.ece.common.events;

import corgitaco.enchancedcelestials.EnhancedCelestials;
import corgitaco.enchancedcelestials.lunarevent.BloodMoon;
import corgitaco.enchancedcelestials.lunarevent.HarvestMoon;
import fictioncraft.wintersteve25.ece.EnhancedCelestialsEnhancement;
import fictioncraft.wintersteve25.ece.common.config.crops.JsonCropBuilder;
import fictioncraft.wintersteve25.ece.common.config.crops.JsonCropConfig;
import fictioncraft.wintersteve25.ece.common.config.entity.JsonConfig;
import fictioncraft.wintersteve25.ece.common.config.entity.JsonBuilder;
import fictioncraft.wintersteve25.ece.common.helper.MiscHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class ECEEventsHandler {
    public static void serverInit (FMLServerStartingEvent event) {
        EnhancedCelestialsEnhancement.registerCommands(event.getServer().getCommandManager().getDispatcher());
    }

    public static void mobSpawnEvent(LivingSpawnEvent.CheckSpawn event) {
        JsonBuilder blood = JsonConfig.blood;
        JsonBuilder harvest = JsonConfig.harvest;

        if (event.getEntity() != null) {
            if (!event.getWorld().isRemote()) {
                String entityRegName = event.getEntity().getEntityString();

                if (entityRegName != null) {
                    if (blood != null) {
                        if (!blood.getBlood().isEmpty()) {
                            for (JsonBuilder.JsonEntityProperty targetEntity : blood.getBlood()) {
                                if (targetEntity.getName().equals(entityRegName)) {
                                    if (!targetEntity.isOnlyDrop()) {
                                        if (targetEntity.isWhitelist()) {
                                            if (!(EnhancedCelestials.currentLunarEvent instanceof BloodMoon)) {
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.ALLOW);
                                            }
                                        } else if (!targetEntity.isWhitelist()) {
                                            if (EnhancedCelestials.currentLunarEvent instanceof BloodMoon) {
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.ALLOW);
                                            }
                                        }
                                    }
                                }

                                if (targetEntity.getName().startsWith("*")) {
                                    String modid = MiscHelper.removeChaAt(targetEntity.getName(), 0);
                                    if (entityRegName.startsWith(modid)) {
                                        if (!targetEntity.isOnlyDrop()) {
                                            if (targetEntity.isWhitelist()) {
                                                if (!(EnhancedCelestials.currentLunarEvent instanceof BloodMoon)) {
                                                    event.setResult(Event.Result.DENY);
                                                } else {
                                                    event.setResult(Event.Result.ALLOW);
                                                }
                                            } else if (!targetEntity.isWhitelist()) {
                                                if (EnhancedCelestials.currentLunarEvent instanceof BloodMoon) {
                                                    event.setResult(Event.Result.DENY);
                                                } else {
                                                    event.setResult(Event.Result.ALLOW);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (harvest != null) {
                        if (!harvest.getHarvest().isEmpty()) {
                            for (JsonBuilder.JsonEntityProperty targetEntity : harvest.getHarvest()) {
                                if (targetEntity.getName().equals(entityRegName)) {
                                    if (!targetEntity.isOnlyDrop()) {
                                        if (targetEntity.isWhitelist()) {
                                            if (!(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon)) {
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.ALLOW);
                                            }
                                        } else if (!targetEntity.isWhitelist()) {
                                            if (EnhancedCelestials.currentLunarEvent instanceof HarvestMoon) {
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.ALLOW);
                                            }
                                        }
                                    }
                                }

                                if (targetEntity.getName().startsWith("*")) {
                                    String modid = MiscHelper.removeChaAt(targetEntity.getName(), 0);
                                    if (entityRegName.startsWith(modid)) {
                                        if (!targetEntity.isOnlyDrop()) {
                                            if (targetEntity.isWhitelist()) {
                                                if (!(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon)) {
                                                    event.setResult(Event.Result.DENY);
                                                } else {
                                                    event.setResult(Event.Result.ALLOW);
                                                }
                                            } else if (!targetEntity.isWhitelist()) {
                                                if (EnhancedCelestials.currentLunarEvent instanceof HarvestMoon) {
                                                    event.setResult(Event.Result.DENY);
                                                } else {
                                                    event.setResult(Event.Result.ALLOW);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void mobDropsEvent(LivingDropsEvent event) {
        JsonBuilder blood = JsonConfig.blood;
        JsonBuilder harvest = JsonConfig.harvest;

        Entity entity = event.getEntity();
        if (entity != null) {
            if (!entity.getEntityWorld().isRemote()) {
                World world = entity.getEntityWorld();

                String entityRegName = entity.getEntityString();

                if (entityRegName != null) {
                    if (blood != null) {
                        if (!blood.getBlood().isEmpty()) {
                            for (JsonBuilder.JsonEntityProperty targetEntity : blood.getBlood()) {
                                if (targetEntity.getName().equals(entityRegName)) {
                                    if (targetEntity.getDrop() != null) {
                                        for (JsonBuilder.JsonItemStackProperty targetDropItem : targetEntity.getDrop()) {

                                            Item targetDropItemItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(targetDropItem.getName().substring(0, targetDropItem.getName().indexOf(":")), targetDropItem.getName().substring(targetDropItem.getName().indexOf(":") + 1)));

                                            if (targetDropItemItem != null) {
                                                if (targetEntity.isWhitelist()) {
                                                    if (EnhancedCelestials.currentLunarEvent instanceof BloodMoon) {
                                                        if (chanceHandling(targetDropItem.getChance())) {
                                                            if (targetEntity.isOverwriteOriginalDrop()) {
                                                                if (event.isCancelable()) {
                                                                    event.setCanceled(true);
                                                                }
                                                            }
                                                            if (targetEntity.isOverwriteOriginalDrop()) {
                                                                if (event.isCancelable()) {
                                                                    event.setCanceled(true);
                                                                }
                                                            }
                                                            int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                            ItemStack isToSpawn = new ItemStack(targetDropItemItem, amount);
                                                            ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                            world.addEntity(ieToSpawn);
                                                        }
                                                    }
                                                } else if (!targetEntity.isWhitelist()) {
                                                    if (!(EnhancedCelestials.currentLunarEvent instanceof BloodMoon)) {
                                                        if (chanceHandling(targetDropItem.getChance())) {
                                                            if (targetEntity.isOverwriteOriginalDrop()) {
                                                                if (event.isCancelable()) {
                                                                    event.setCanceled(true);
                                                                }
                                                            }

                                                            int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                            ItemStack isToSpawn = new ItemStack(targetDropItemItem, amount);
                                                            ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                            world.addEntity(ieToSpawn);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }

                                if (targetEntity.getName().startsWith("*")) {
                                    String modid = MiscHelper.removeChaAt(targetEntity.getName(), 0);
                                    if (entityRegName.startsWith(modid)) {
                                        if (targetEntity.getDrop() != null) {
                                            for (JsonBuilder.JsonItemStackProperty targetDropItem : targetEntity.getDrop()) {

                                                Item targetDropItemItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(targetDropItem.getName().substring(0, targetDropItem.getName().indexOf(":")), targetDropItem.getName().substring(targetDropItem.getName().indexOf(":") + 1)));

                                                if (targetDropItemItem != null) {
                                                    if (targetEntity.isWhitelist()) {
                                                        if (EnhancedCelestials.currentLunarEvent instanceof BloodMoon) {
                                                            if (chanceHandling(targetDropItem.getChance())) {
                                                                if (targetEntity.isOverwriteOriginalDrop()) {
                                                                    if (event.isCancelable()) {
                                                                        event.setCanceled(true);
                                                                    }
                                                                }

                                                                int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                                ItemStack isToSpawn = new ItemStack(targetDropItemItem, amount);
                                                                ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                                world.addEntity(ieToSpawn);
                                                            }
                                                        }
                                                    } else if (!targetEntity.isWhitelist()) {
                                                        if (!(EnhancedCelestials.currentLunarEvent instanceof BloodMoon)) {
                                                            if (chanceHandling(targetDropItem.getChance())) {
                                                                if (targetEntity.isOverwriteOriginalDrop()) {
                                                                    if (event.isCancelable()) {
                                                                        event.setCanceled(true);
                                                                    }
                                                                }

                                                                int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                                ItemStack isToSpawn = new ItemStack(targetDropItemItem, amount);
                                                                ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                                world.addEntity(ieToSpawn);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (harvest != null) {
                        if (!harvest.getHarvest().isEmpty()) {
                            for (JsonBuilder.JsonEntityProperty targetEntity : harvest.getHarvest()) {
                                if (targetEntity.getName().equals(entityRegName)) {
                                    if (targetEntity.getDrop() != null) {
                                        for (JsonBuilder.JsonItemStackProperty targetDropItem : targetEntity.getDrop()) {
                                            Item targetDropItemItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(targetDropItem.getName().substring(0, targetDropItem.getName().indexOf(":")), targetDropItem.getName().substring(targetDropItem.getName().indexOf(":") + 1)));

                                            if (targetDropItemItem != null) {
                                                if (targetEntity.isWhitelist()) {
                                                    if (EnhancedCelestials.currentLunarEvent instanceof HarvestMoon) {
                                                        if (chanceHandling(targetDropItem.getChance())) {
                                                            int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                            ItemStack isToSpawn = new ItemStack(targetDropItemItem, amount);
                                                            ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                            world.addEntity(ieToSpawn);
                                                        }
                                                    }
                                                } else if (!targetEntity.isWhitelist()) {
                                                    if (!(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon)) {
                                                        if (chanceHandling(targetDropItem.getChance())) {
                                                            int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                            ItemStack isToSpawn = new ItemStack(targetDropItemItem, amount);
                                                            ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                            world.addEntity(ieToSpawn);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }

                                if (targetEntity.getName().startsWith("*")) {
                                    String modid = MiscHelper.removeChaAt(targetEntity.getName(), 0);
                                    if (entityRegName.startsWith(modid)) {
                                        if (targetEntity.getDrop() != null) {
                                            for (JsonBuilder.JsonItemStackProperty targetDropItem : targetEntity.getDrop()) {
                                                Item targetDropItemItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(targetDropItem.getName().substring(0, targetDropItem.getName().indexOf(":")), targetDropItem.getName().substring(targetDropItem.getName().indexOf(":") + 1)));

                                                if (targetDropItemItem != null) {
                                                    if (targetEntity.isWhitelist()) {
                                                        if (EnhancedCelestials.currentLunarEvent instanceof HarvestMoon) {
                                                            if (chanceHandling(targetDropItem.getChance())) {
                                                                int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                                ItemStack isToSpawn = new ItemStack(targetDropItemItem, amount);
                                                                ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                                world.addEntity(ieToSpawn);
                                                            }
                                                        }
                                                    } else if (!targetEntity.isWhitelist()) {
                                                        if (!(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon)) {
                                                            if (chanceHandling(targetDropItem.getChance())) {
                                                                int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                                ItemStack isToSpawn = new ItemStack(targetDropItemItem, amount);
                                                                ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                                world.addEntity(ieToSpawn);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void cropGrowthEvent (BlockEvent.CropGrowEvent.Pre event) {
        World world = (World)event.getWorld();
        ResourceLocation blockRL = world.getBlockState(event.getPos()).getBlock().getRegistryName();

        JsonCropBuilder blood = JsonCropConfig.blood;
        JsonCropBuilder harvest = JsonCropConfig.harvest;

        if (!world.isRemote()) {
            if (blood != null) {
                if (!blood.getBlood().isEmpty()) {
                    for (JsonCropBuilder.JsonCropProperties crops : blood.getBlood()) {
                        if (crops != null) {
                            if (!crops.getName().isEmpty()) {
                                if (blockRL != null) {
                                    if (crops.getName().equals(blockRL.getNamespace() + ":" + blockRL.getPath())) {
                                        if (crops.isWhitelist()) {
                                            if (!(EnhancedCelestials.currentLunarEvent instanceof BloodMoon)) {
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.DEFAULT);
                                            }
                                        } else if (!crops.isWhitelist()) {
                                            if (EnhancedCelestials.currentLunarEvent instanceof BloodMoon) {
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.DEFAULT);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (harvest != null) {
                if (!harvest.getHarvest().isEmpty()) {
                    for (JsonCropBuilder.JsonCropProperties crops : harvest.getHarvest()) {
                        if (crops != null) {
                            if (!crops.getName().isEmpty()) {
                                if (blockRL != null) {
                                    if (crops.getName().equals(blockRL.getNamespace() + ":" + blockRL.getPath())) {
                                        if (crops.isWhitelist()) {
                                            if (!(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon)) {
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.DEFAULT);
                                            }
                                        } else if (!crops.isWhitelist()) {
                                            if (EnhancedCelestials.currentLunarEvent instanceof HarvestMoon) {
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.DEFAULT);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void boneMealEvent (BonemealEvent event) {
        World world = event.getWorld();
        ResourceLocation blockRL = world.getBlockState(event.getPos()).getBlock().getRegistryName();

        JsonCropBuilder blood = JsonCropConfig.blood;
        JsonCropBuilder harvest = JsonCropConfig.harvest;

        if (!world.isRemote()) {
            if (blood != null) {
                if (!blood.getBlood().isEmpty()) {
                    for (JsonCropBuilder.JsonCropProperties crops : blood.getBlood()) {
                        if (crops != null) {
                            if (!crops.getName().isEmpty()) {
                                if (blockRL != null) {
                                    if (crops.getName().equals(blockRL.getNamespace() + ":" + blockRL.getPath())) {
                                        if (crops.isWhitelist()) {
                                            event.setCanceled(!(EnhancedCelestials.currentLunarEvent instanceof BloodMoon));
                                        } else if (!crops.isWhitelist()) {
                                            event.setCanceled(EnhancedCelestials.currentLunarEvent instanceof BloodMoon);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (harvest != null) {
                if (!harvest.getHarvest().isEmpty()) {
                    for (JsonCropBuilder.JsonCropProperties crops : harvest.getHarvest()) {
                        if (crops != null) {
                            if (!crops.getName().isEmpty()) {
                                if (blockRL != null) {
                                    if (crops.getName().equals(blockRL.getNamespace() + ":" + blockRL.getPath())) {
                                        if (crops.isWhitelist()) {
                                            event.setCanceled(!(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon));
                                        } else if (!crops.isWhitelist()) {
                                            event.setCanceled(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean chanceHandling(int chance) {
        Random rand = new Random();
        double randN = rand.nextDouble();

        return randN < (double) chance/100;
    }
}
