package cn.evolvefield.mods.simpleeco.utils;

import net.minecraft.world.World;

public class LogUtil {
    public static void logCommon (World world, Object message) {
        System.out.println((world.isClientSide ? "CLIENT: " : "SERVER: ") + message);
    }

    public static void log (Object message) {
        System.out.println(message);
    }
}
