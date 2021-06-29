package fictioncraft.wintersteve25.ece.common.events;

import corgitaco.enchancedcelestials.EnhancedCelestials;
import corgitaco.enchancedcelestials.lunarevent.BloodMoon;
import corgitaco.enchancedcelestials.lunarevent.HarvestMoon;
import fictioncraft.wintersteve25.ece.EnhancedCelestialsEnhancement;
import fictioncraft.wintersteve25.ece.common.config.JsonConfig;
import fictioncraft.wintersteve25.ece.common.config.JsonBuilder;
import fictioncraft.wintersteve25.ece.common.helper.MiscHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class ECEEventsHandler {
    public static void mobSpawnEvent(LivingSpawnEvent.CheckSpawn event) {
        JsonBuilder blood = JsonConfig.blood;
        JsonBuilder harvest = JsonConfig.harvest;

        if (event.getEntity() != null) {

            String entityName = MiscHelper.langToReg(event.getEntity().getName().getString());
            String entityRegName = event.getEntity().getEntityString();

            if (entityRegName != null) {
                if (blood != null) {
                    if (!blood.getBlood().isEmpty()) {
                        for (JsonBuilder.JsonEntityProperty targetEntity : blood.getBlood()) {
                            if (targetEntity.getName().equals(entityName) || targetEntity.getName().equals(entityRegName)) {
                                if (!targetEntity.isOnlyDrop()) {
                                    if (targetEntity.isWhitelist()) {
                                        if (!(EnhancedCelestials.currentLunarEvent instanceof BloodMoon)) {
                                            EnhancedCelestialsEnhancement.LOGGER.info("Blocked " + entityRegName + " from spawning since it is not blood moon");
                                            event.setResult(Event.Result.DENY);
                                        } else {
                                            event.setResult(Event.Result.ALLOW);
                                        }
                                    } else if (!targetEntity.isWhitelist()) {
                                        if (EnhancedCelestials.currentLunarEvent instanceof BloodMoon) {
                                            EnhancedCelestialsEnhancement.LOGGER.info("Blocked " + entityRegName + " from spawning since it is blood moon");
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
                                                EnhancedCelestialsEnhancement.LOGGER.info("Blocked " + entityRegName + " from spawning since it is not blood moon");
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.ALLOW);
                                            }
                                        } else if (!targetEntity.isWhitelist()) {
                                            if (EnhancedCelestials.currentLunarEvent instanceof BloodMoon) {
                                                EnhancedCelestialsEnhancement.LOGGER.info("Blocked " + entityRegName + " from spawning since it is blood moon");
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
                            if (targetEntity.getName().equals(entityName) || targetEntity.getName().equals(entityRegName)) {
                                if (!targetEntity.isOnlyDrop()) {
                                    if (targetEntity.isWhitelist()) {
                                        if (!(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon)) {
                                            EnhancedCelestialsEnhancement.LOGGER.info("Blocked " + entityRegName + " from spawning since it is not harvest moon");
                                            event.setResult(Event.Result.DENY);
                                        } else {
                                            event.setResult(Event.Result.ALLOW);
                                        }
                                    } else if (!targetEntity.isWhitelist()) {
                                        if (EnhancedCelestials.currentLunarEvent instanceof HarvestMoon) {
                                            EnhancedCelestialsEnhancement.LOGGER.info("Blocked " + entityRegName + " from spawning since it is harvest moon");
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
                                                EnhancedCelestialsEnhancement.LOGGER.info("Blocked " + entityRegName + " from spawning since it is not harvest moon");
                                                event.setResult(Event.Result.DENY);
                                            } else {
                                                event.setResult(Event.Result.ALLOW);
                                            }
                                        } else if (!targetEntity.isWhitelist()) {
                                            if (EnhancedCelestials.currentLunarEvent instanceof HarvestMoon) {
                                                EnhancedCelestialsEnhancement.LOGGER.info("Blocked " + entityRegName + " from spawning since it is harvest moon");
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

    public static void mobDropsEvent(LivingDropsEvent event) {
        JsonBuilder blood = JsonConfig.blood;
        JsonBuilder harvest = JsonConfig.harvest;

        Entity entity = event.getEntity();
        if (entity != null) {

            World world = entity.getEntityWorld();

            String entityRegName = entity.getEntityString();
            String entityName = MiscHelper.langToReg(entity.getName().getString());

            if (entityRegName != null) {
                if (blood != null) {
                    if (!blood.getBlood().isEmpty()) {
                        for (JsonBuilder.JsonEntityProperty targetEntity : blood.getBlood()) {
                            if (targetEntity.getName().equals(entityName) || targetEntity.getName().equals(entityRegName)) {
                                if (targetEntity.getDrop() != null) {
                                    for (JsonBuilder.JsonItemStackProperty targetDropItem : targetEntity.getDrop()) {
                                        MutableRegistry<Item> itemMutableRegistry = world.func_241828_r().getRegistry(ForgeRegistries.Keys.ITEMS);
                                        AtomicReference<Item> targetDropItemItem = new AtomicReference<>();

                                        itemMutableRegistry.getOptional(new ResourceLocation(targetDropItem.getName().substring(0, targetDropItem.getName().indexOf(":")), targetDropItem.getName().substring(targetDropItem.getName().indexOf(":")))).ifPresent(targetDropItemItem::set);

                                        if (targetDropItemItem.get() != null) {
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
                                                        ItemStack isToSpawn = new ItemStack(targetDropItemItem.get(), amount);
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
                                                        ItemStack isToSpawn = new ItemStack(targetDropItemItem.get(), amount);
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
                                            MutableRegistry<Item> itemMutableRegistry = world.func_241828_r().getRegistry(ForgeRegistries.Keys.ITEMS);
                                            AtomicReference<Item> targetDropItemItem = new AtomicReference<>();
                                            itemMutableRegistry.getOptional(new ResourceLocation(targetDropItem.getName().substring(0, targetDropItem.getName().indexOf(":")), targetDropItem.getName().substring(targetDropItem.getName().indexOf(":")))).ifPresent(targetDropItemItem::set);

                                            if (targetDropItemItem.get() != null) {
                                                if (targetEntity.isWhitelist()) {
                                                    if (EnhancedCelestials.currentLunarEvent instanceof BloodMoon) {
                                                        if (chanceHandling(targetDropItem.getChance())) {
                                                            if (targetEntity.isOverwriteOriginalDrop()) {
                                                                if (event.isCancelable()) {
                                                                    event.setCanceled(true);
                                                                }
                                                            }

                                                            int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                            ItemStack isToSpawn = new ItemStack(targetDropItemItem.get(), amount);
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
                                                            ItemStack isToSpawn = new ItemStack(targetDropItemItem.get(), amount);
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
                            if (targetEntity.getName().equals(entityName) || targetEntity.getName().equals(entityRegName)) {
                                if (targetEntity.getDrop() != null) {
                                    for (JsonBuilder.JsonItemStackProperty targetDropItem : targetEntity.getDrop()) {
                                        MutableRegistry<Item> itemMutableRegistry = world.func_241828_r().getRegistry(ForgeRegistries.Keys.ITEMS);
                                        AtomicReference<Item> targetDropItemItem = new AtomicReference<>();
                                        itemMutableRegistry.getOptional(new ResourceLocation(targetDropItem.getName().substring(0, targetDropItem.getName().indexOf(":")), targetDropItem.getName().substring(targetDropItem.getName().indexOf(":")))).ifPresent(targetDropItemItem::set);

                                        if (targetDropItemItem.get() != null) {
                                            if (targetEntity.isWhitelist()) {
                                                if (EnhancedCelestials.currentLunarEvent instanceof HarvestMoon) {
                                                    if (chanceHandling(targetDropItem.getChance())) {
                                                        int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                        ItemStack isToSpawn = new ItemStack(targetDropItemItem.get(), amount);
                                                        ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                        world.addEntity(ieToSpawn);
                                                    }
                                                }
                                            } else if (!targetEntity.isWhitelist()) {
                                                if (!(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon)) {
                                                    if (chanceHandling(targetDropItem.getChance())) {
                                                        int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                        ItemStack isToSpawn = new ItemStack(targetDropItemItem.get(), amount);
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
                                            MutableRegistry<Item> itemMutableRegistry = world.func_241828_r().getRegistry(ForgeRegistries.Keys.ITEMS);
                                            AtomicReference<Item> targetDropItemItem = new AtomicReference<>();
                                            itemMutableRegistry.getOptional(new ResourceLocation(targetDropItem.getName().substring(0, targetDropItem.getName().indexOf(":")), targetDropItem.getName().substring(targetDropItem.getName().indexOf(":")))).ifPresent(targetDropItemItem::set);

                                            if (targetDropItemItem.get() != null) {
                                                if (targetEntity.isWhitelist()) {
                                                    if (EnhancedCelestials.currentLunarEvent instanceof HarvestMoon) {
                                                        if (chanceHandling(targetDropItem.getChance())) {
                                                            int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                            ItemStack isToSpawn = new ItemStack(targetDropItemItem.get(), amount);
                                                            ItemEntity ieToSpawn = new ItemEntity(world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), isToSpawn);

                                                            world.addEntity(ieToSpawn);
                                                        }
                                                    }
                                                } else if (!targetEntity.isWhitelist()) {
                                                    if (!(EnhancedCelestials.currentLunarEvent instanceof HarvestMoon)) {
                                                        if (chanceHandling(targetDropItem.getChance())) {
                                                            int amount = MiscHelper.randomInRange(targetDropItem.getMinAmount(), targetDropItem.getMaxAmount());
                                                            ItemStack isToSpawn = new ItemStack(targetDropItemItem.get(), amount);
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

    public static boolean chanceHandling(int chance) {
        Random rand = new Random();
        double randN = rand.nextDouble();

        return randN < (double) chance/100;
    }
}
