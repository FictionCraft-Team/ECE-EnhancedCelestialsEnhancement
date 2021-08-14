package fictioncraft.wintersteve25.ece.common.config.blockdrop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fictioncraft.wintersteve25.ece.EnhancedCelestialsEnhancement;
import fictioncraft.wintersteve25.ece.common.config.crops.JsonCropConfig;
import fictioncraft.wintersteve25.ece.common.config.entity.JsonBuilder;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JsonBlockConfig {

    public static JsonBlockBuilder blood;
    public static JsonBlockBuilder harvest;

    public static void createJsonConfig() {
        PrintWriter writer;
        File file = new File(JsonCropConfig.directory.getPath() + File.separator + "ece_block_config.json");

        if (!file.exists()) {
            try {
                writer = new PrintWriter(JsonCropConfig.directory.getPath() + File.separator + "ece_block_config.json");
            } catch (Exception e) {
                EnhancedCelestialsEnhancement.LOGGER.log(Level.ERROR, "Error writing ece_block_config.json");
                e.printStackTrace();
                return;
            }

            ArrayList<JsonBlockBuilder.JsonBlockProperties> blood = new ArrayList<>();
            ArrayList<JsonBlockBuilder.JsonBlockProperties> harvest = new ArrayList<>();

            JsonBlockBuilder exampleBuild = new JsonBlockBuilder(blood, harvest);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.print(gson.toJson(exampleBuild));
            writer.close();

            EnhancedCelestialsEnhancement.LOGGER.log(Level.INFO, "Written ece_block_config.json");
        }
    }

    public static void printExample() {
        PrintWriter writer;

        try {
            writer = new PrintWriter(JsonCropConfig.directory.getPath() + File.separator + "ece_block_config_example.json");
        } catch (Exception e) {
            EnhancedCelestialsEnhancement.LOGGER.log(Level.ERROR, "Error writing ece_block_config_example.json");
            e.printStackTrace();
            return;
        }

        ArrayList<JsonBlockBuilder.JsonBlockProperties> blood = new ArrayList<>();
        ArrayList<JsonBuilder.JsonItemStackProperty> itemStackProperties = new ArrayList<>();
        itemStackProperties.add(new JsonBuilder.JsonItemStackProperty("minecraft:iron_ingot", 1, 2, 40));
        blood.add(new JsonBlockBuilder.JsonBlockProperties("minecraft:stone", true, itemStackProperties));
        ArrayList<JsonBlockBuilder.JsonBlockProperties> harvest = new ArrayList<>();

        JsonBlockBuilder exampleBuild = new JsonBlockBuilder(blood, harvest);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        writer.print(gson.toJson(exampleBuild));
        writer.close();

        EnhancedCelestialsEnhancement.LOGGER.log(Level.INFO, "Written ece_block_config_example.json");
    }

    public static void read() {
        File file = new File(JsonCropConfig.directory.getPath() + File.separator + "ece_block_config.json");
        if (!file.exists()) {
            EnhancedCelestialsEnhancement.LOGGER.log(Level.ERROR, "ECE Config json file not found! Creating a new one..");
            createJsonConfig();
        } else {
            try {
                EnhancedCelestialsEnhancement.LOGGER.info("Attempting to read ece_block_config.json file...");
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonBlockBuilder builder = gson.fromJson(new FileReader(file), JsonBlockBuilder.class);
                if (builder != null) {
                    if (!builder.getBlood().isEmpty()) {
                        blood = builder;
                    }

                    if (!builder.getHarvest().isEmpty()) {
                        harvest = builder;
                    }
                }
            } catch (FileNotFoundException e) {
                EnhancedCelestialsEnhancement.LOGGER.log(Level.ERROR, "ECE Config json file not found! Creating a new one..");
                e.printStackTrace();
                createJsonConfig();
            }
        }
    }
}
