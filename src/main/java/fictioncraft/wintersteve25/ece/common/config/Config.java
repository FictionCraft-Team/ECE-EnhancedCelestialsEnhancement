package fictioncraft.wintersteve25.ece.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final String CAT_GEN = "general";
    public static ForgeConfigSpec SERVER_CONFIG;

    public static ForgeConfigSpec.BooleanValue PRINT_EXAMPLE;
    public static ForgeConfigSpec.BooleanValue PRINT_ERRORS;

    static {
        ForgeConfigSpec.Builder SERVERBUILDER = new ForgeConfigSpec.Builder();

        SERVERBUILDER.comment("General").push(CAT_GEN);
        PRINT_EXAMPLE = SERVERBUILDER.comment("Should Example file be printed?").define("printExample", true);
        PRINT_ERRORS = SERVERBUILDER.comment("Should script errors be printed in chat when player log in?").define("printErrors", true);
        SERVERBUILDER.pop();

        SERVER_CONFIG = SERVERBUILDER.build();
    }

}
