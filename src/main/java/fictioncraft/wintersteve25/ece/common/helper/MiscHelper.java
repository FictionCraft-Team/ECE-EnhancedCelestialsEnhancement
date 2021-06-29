package fictioncraft.wintersteve25.ece.common.helper;

import java.util.Random;

public class MiscHelper {

    public static String langToReg(String lang) {
        String reg = lang.toLowerCase().replace(' ', '_').replace('-', '_');
        return reg;
    }

    public static double randomInRange(double min, double max) {
        return (java.lang.Math.random() * (max - min)) + min;
    }

    public static float randomInRange(float min, float max) {
        return (float) ((java.lang.Math.random() * (max - min)) + min);
    }

    public static int randomInRange(int min, int max) {
        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }

    public static String removeChaAt (String string, int index) {
        StringBuilder sb = new StringBuilder(string);

        sb.deleteCharAt(index);

        return sb.toString();
    }
}
