package cn.evolvefield.mods.simpleeco.init;

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
    public static ForgeConfigSpec.ConfigValue<Double> LOSS_ON_DEATH;
    public static ForgeConfigSpec.ConfigValue<Integer> SHOP_LEVEL;
    public static ForgeConfigSpec.ConfigValue<Double> SHOP_COST;
    public static ForgeConfigSpec.ConfigValue<String> REQUIRED_ITEM;

    public static ForgeConfigSpec.ConfigValue<String> DATE_PATTERN;
    public static ForgeConfigSpec.BooleanValue IS_FLY_ENABLE;
    public static ForgeConfigSpec.ConfigValue<Integer> FLY_FACTOR;

    public static ForgeConfigSpec.BooleanValue HOLOGRAPHY_ENABLE;


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
        builder.comment("Money Setting").push("Money");
        STARTING_FUNDS = builder.comment("The amount of money a new player starts ")
                .defineInRange("starting_funds", 1000D, 0, Double.MAX_VALUE);
        CURRENCY_SYMBOL = builder.comment("The character of the money values ")
                .define("currency symbol", "\uffe5");
        ADMIN_LEVEL = builder.comment(" Level to use eco commands")
                .defineInRange("admin level", 2, 1, 4, Integer.class);
        LOSS_ON_DEATH = builder.comment("A percentage of the player's account that is lost on death, in decimal form. default = 0%. 0.5 = 50%")
                .defineInRange("loss on death", 0d, 0d, 1d);
        builder.pop();

        builder.comment("Shop Setting").push("Shop");
        SHOP_LEVEL = builder.comment("The minimum permission level to create basic shops.  default= 0 = all players")
                .defineInRange("shop level", 0, 0, 4, Integer.class);
        SHOP_COST = builder.comment("The cost of a SignShop ")
                .defineInRange("shop_cost", 10D, 0, Double.MAX_VALUE);
        REQUIRED_ITEM = builder.comment("Required item to edit signs")
                .define("required_item_id", "");
        HOLOGRAPHY_ENABLE = builder
                .comment("Set it to false to disable shop holographic display",
                        "Default value: true")
                .define("holography_enable", true);

        builder.pop();

        builder.comment("Fly Setting").push("Fly");
        IS_FLY_ENABLE = builder
                .comment("Set it to false to disable /fly command.",
                        "Default value: true",
                        "This option only work after server restarted or typed /reload command")
                .define("IsFlyEnable", true);
        DATE_PATTERN = builder
                .comment("The date format used to display the deadline of flying.",
                        "A valid date format should follow the pattern described in JavaDoc: https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html",
                        "If you don't know what it is, please do not modify it.",
                        "Default value: \"hh:mm:ss MM/dd/yyyy\"")
                .define("DatePattern", "hh:mm:ss MM/dd/yyyy");
        FLY_FACTOR = builder.comment(" The factor of fly time and money")
                .defineInRange("fly factor", 10, 1, 10000, Integer.class);
        builder.pop();




    }


}
