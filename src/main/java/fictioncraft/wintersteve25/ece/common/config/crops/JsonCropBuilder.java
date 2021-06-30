package fictioncraft.wintersteve25.ece.common.config.crops;

import java.util.ArrayList;

public class JsonCropBuilder {
    private ArrayList<JsonCropProperties> blood = new ArrayList<>();
    private ArrayList<JsonCropProperties> harvest = new ArrayList<>();

    public JsonCropBuilder(ArrayList<JsonCropProperties> blood, ArrayList<JsonCropProperties> harvest) {
        this.blood = blood;
        this.harvest = harvest;
    }

    public ArrayList<JsonCropProperties> getBlood() {
        return blood;
    }

    public ArrayList<JsonCropProperties> getHarvest() {
        return harvest;
    }

    public static class JsonCropProperties {
        private String name;
        private boolean isWhitelist;

        public JsonCropProperties(String name, boolean isWhitelist) {
            this.name = name;
            this.isWhitelist = isWhitelist;
        }

        public String getName() {
            return name;
        }

        public boolean isWhitelist() {
            return isWhitelist;
        }
    }
}
