package fictioncraft.wintersteve25.ece.common.config.blockdrop;

import fictioncraft.wintersteve25.ece.common.config.entity.JsonBuilder;

import java.util.ArrayList;

public class JsonBlockBuilder {
    private ArrayList<JsonBlockProperties> blood = new ArrayList<>();
    private ArrayList<JsonBlockProperties> harvest = new ArrayList<>();

    public JsonBlockBuilder(ArrayList<JsonBlockProperties> blood, ArrayList<JsonBlockProperties> harvest) {
        this.blood = blood;
        this.harvest = harvest;
    }

    public ArrayList<JsonBlockProperties> getBlood() {
        return blood;
    }

    public ArrayList<JsonBlockProperties> getHarvest() {
        return harvest;
    }

    public static class JsonBlockProperties {
        private String name;
        private boolean isWhitelist;
        private ArrayList<JsonBuilder.JsonItemStackProperty> drops;

        public JsonBlockProperties(String name, boolean isWhitelist, ArrayList<JsonBuilder.JsonItemStackProperty> drops) {
            this.name = name;
            this.isWhitelist = isWhitelist;
            this.drops = drops;
        }

        public String getName() {
            return name;
        }

        public boolean isWhitelist() {
            return isWhitelist;
        }

        public ArrayList<JsonBuilder.JsonItemStackProperty> getDrops() {
            return drops;
        }
    }
}
