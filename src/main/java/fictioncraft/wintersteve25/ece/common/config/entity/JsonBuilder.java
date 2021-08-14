package fictioncraft.wintersteve25.ece.common.config.entity;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class JsonBuilder {
    private ArrayList<JsonEntityProperty> blood;
    private ArrayList<JsonEntityProperty> harvest;

    public JsonBuilder(ArrayList<JsonEntityProperty> blood, ArrayList<JsonEntityProperty> harvest) {
        this.blood = blood;
        this.harvest = harvest;
    }

    public ArrayList<JsonEntityProperty> getBlood() {
        return blood;
    }

    public ArrayList<JsonEntityProperty> getHarvest() {
        return harvest;
    }

    public static class JsonEntityProperty {
        private String name;
        private boolean whitelist;
        private boolean onlyDrop;
        private boolean overwriteOriginalDrop;
        private int experienceToDrop;
        private boolean removeOriginalXPDrop;

        @Nullable
        private ArrayList<JsonItemStackProperty> drops;

        public JsonEntityProperty(String name, @Nullable ArrayList<JsonItemStackProperty> drops, boolean whitelist, boolean onlyDrop, boolean overwriteOriginalDrop, int experienceToDrop, boolean removeOriginalXPDrop) {
            this.name = name;
            this.drops = drops;
            this.whitelist = whitelist;
            this.onlyDrop = onlyDrop;
            this.overwriteOriginalDrop = overwriteOriginalDrop;
            this.experienceToDrop = experienceToDrop;
            this.removeOriginalXPDrop = removeOriginalXPDrop;
        }

        @Nullable
        public ArrayList<JsonItemStackProperty> getDrop() {
            return drops;
        }

        public String getName() {
            return name;
        }

        public boolean isWhitelist() {
            return whitelist;
        }

        public boolean isOnlyDrop() {
            return onlyDrop;
        }

        public boolean isOverwriteOriginalDrop() {
            return overwriteOriginalDrop;
        }

        public int getExperienceToDrop() {
            return experienceToDrop;
        }

        public boolean isRemoveOriginalXPDrop() {
            return removeOriginalXPDrop;
        }
    }

    public static class JsonItemStackProperty {
        private String name;
        private int minAmount;
        private int maxAmount;
        private int chance;

        public JsonItemStackProperty(String name, int minAmount, int maxAmount, int chance) {
            this.name = name;
            this.minAmount = minAmount;
            this.maxAmount = maxAmount;
            this.chance = chance;
        }

        public String getName() {
            return name;
        }

        public int getChance() {
            return chance;
        }

        public int getMaxAmount() {
            return maxAmount;
        }

        public int getMinAmount() {
            return minAmount;
        }
    }
}
