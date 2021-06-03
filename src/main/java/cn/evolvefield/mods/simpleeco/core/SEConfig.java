package cn.evolvefield.mods.simpleeco.core;

import net.minecraftforge.common.ForgeConfigSpec;

public class SEConfig {
    public static ForgeConfigSpec SERVER_CONFIG;

    public static final String CATEGORY_SERVER = "server";

    public static final String SUB_CATEGORY_SERVER = "Server";

    //Data Storage Scheme Variables
    public static ForgeConfigSpec.ConfigValue<Boolean> SETTING;
    //Misc variables
    public static ForgeConfigSpec.ConfigValue<Double> STARTING_FUNDS;
    public static ForgeConfigSpec.ConfigValue<String> CURRENCY_SYMBOL;
    public static ForgeConfigSpec.ConfigValue<Integer> ADMIN_LEVEL;

    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

        SERVER_BUILDER.comment("Server Settings").push(CATEGORY_SERVER);
        setupServer(SERVER_BUILDER);
        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
    }

    private static void setupServer(ForgeConfigSpec.Builder builder) {
        builder.comment("Server Settings").push(SUB_CATEGORY_SERVER);
        //Misc Variables
        STARTING_FUNDS = builder.comment("The amount of money a new player starts ")
                .defineInRange("starting_funds", 1000D, 0, Double.MAX_VALUE);
        CURRENCY_SYMBOL = builder.comment("the character of the money values ")
                .define("currency symbol", "\uffe5");
        ADMIN_LEVEL = builder.comment(" level to use eco commands")
                .defineInRange("admin level", 2, 1, 4, Integer.class);

        builder.pop();
    }
}
