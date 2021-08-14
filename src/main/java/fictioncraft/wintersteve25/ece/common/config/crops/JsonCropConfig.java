package fictioncraft.wintersteve25.ece.common.config.crops;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fictioncraft.wintersteve25.ece.EnhancedCelestialsEnhancement;
import org.apache.logging.log4j.Level;
import org.lwjgl.system.CallbackI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JsonCropConfig {

    public static JsonCropBuilder blood;
    public static JsonCropBuilder harvest;
    public static File directory = new File("ece_configs");

    public static void createJsonConfig() {
        PrintWriter writer;
        File file = new File(directory.getPath() + File.separator + "ece_crop_config.json");

        if (!directory.exists()) {
            directory.mkdir();
        }

        if (!file.exists()) {
            try {
                writer = new PrintWriter(directory.getPath() + File.separator + "ece_crop_config.json");
            } catch (Exception e) {
                EnhancedCelestialsEnhancement.LOGGER.log(Level.ERROR, "Error writing ece_crop_config.json");
                e.printStackTrace();
                return;
            }

            ArrayList<JsonCropBuilder.JsonCropProperties> blood = new ArrayList<>();
            ArrayList<JsonCropBuilder.JsonCropProperties> harvest = new ArrayList<>();

            JsonCropBuilder exampleBuild = new JsonCropBuilder(blood, harvest);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.print(gson.toJson(exampleBuild));
            writer.close();

            EnhancedCelestialsEnhancement.LOGGER.log(Level.INFO, "Written ece_crop_config.json");
        }
    }

    public static void printExample() {
        PrintWriter writer;

        try {
            writer = new PrintWriter(directory.getPath() + File.separator + "ece_crop_config_example.json");
        } catch (Exception e) {
            EnhancedCelestialsEnhancement.LOGGER.log(Level.ERROR, "Error writing ece_crop_config_example.json");
            e.printStackTrace();
            return;
        }

        ArrayList<JsonCropBuilder.JsonCropProperties> blood = new ArrayList<>();
        blood.add(new JsonCropBuilder.JsonCropProperties("minecraft:nether_wart", true, true));

        ArrayList<JsonCropBuilder.JsonCropProperties> harvest = new ArrayList<>();
        harvest.add(new JsonCropBuilder.JsonCropProperties("minecraft:wheat", true, false));

        JsonCropBuilder exampleBuild = new JsonCropBuilder(blood, harvest);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        writer.print(gson.toJson(exampleBuild));
        writer.close();

        EnhancedCelestialsEnhancement.LOGGER.log(Level.INFO, "Written ece_crop_config_example.json");
    }

    public static void read() {
        File file = new File(directory.getPath() + File.separator + "ece_crop_config.json");
        if (!file.exists()) {
            EnhancedCelestialsEnhancement.LOGGER.log(Level.ERROR, "ECE Config json file not found! Creating a new one..");
            createJsonConfig();
        } else {
            try {
                EnhancedCelestialsEnhancement.LOGGER.info("Attempting to read ece_crop_config.json file...");
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonCropBuilder builder = gson.fromJson(new FileReader(file), JsonCropBuilder.class);
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
