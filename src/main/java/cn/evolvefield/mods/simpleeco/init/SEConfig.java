package cn.evolvefield.mods.simpleeco.init;

import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Collection;

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
        LOSS_ON_DEATH = builder.comment("a percentage of the player's account that is lost on death, in decimal form. default = 0%. 0.5 = 50%")
                .defineInRange("loss on death", 0d, 0d, 1d);
        SHOP_LEVEL = builder.comment("The minimum permission level to create basic shops.  default= 0 = all players")
                .defineInRange("shop level", 0, 0, 4, Integer.class);
        SHOP_COST = builder.comment("The cost of a SignShop ")
                .defineInRange("shop_cost", 10D, 0, Double.MAX_VALUE);
        REQUIRED_ITEM = builder.comment("Required item to edit signs")
                .define("required_item_id", "");

        builder.pop();
    }


}
