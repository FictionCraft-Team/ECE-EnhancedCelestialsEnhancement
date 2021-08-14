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
        private boolean allowBonemeal;

        public JsonCropProperties(String name, boolean isWhitelist, boolean allowBonemeal) {
            this.name = name;
            this.isWhitelist = isWhitelist;
            this.allowBonemeal = allowBonemeal;
        }

        public String getName() {
            return name;
        }

        public boolean isWhitelist() {
            return isWhitelist;
        }

        public boolean isAllowBonemeal() {
            return allowBonemeal;
        }
    }
}
