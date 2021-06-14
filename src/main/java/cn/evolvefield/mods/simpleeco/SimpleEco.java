package cn.evolvefield.mods.simpleeco;

import cn.evolvefield.mods.simpleeco.main.SEConfig;
import cn.evolvefield.mods.simpleeco.main.SERegistry;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


@Mod("simpleeco")
public class SimpleEco {
    public static final String MOD_ID = "simpleeco";
    private static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static MinecraftServer SERVER = ServerLifecycleHooks.getCurrentServer();

    public static File MAIN_FOLDER;
    public static File PLAYER_DATA_FOLDER;

    public SimpleEco() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, this::onServerAboutToStart);
        MinecraftForge.EVENT_BUS.addListener(this::onWorldSave);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SEConfig.SERVER_CONFIG);

        SERegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }


    private void onServerAboutToStart(FMLServerAboutToStartEvent event) {
        SERVER = event.getServer();
        MAIN_FOLDER = new File(FMLPaths.GAMEDIR.get().toFile() + "/" + "SimpleEco") ;
        fileInit();
        LOGGER.info("SE Successfully initialize directories!");
    }

    private void onWorldSave(WorldEvent.Save event) {
        fileInit();
    }

    public static void resetData() {
        //SEPlayerData.PLAYER_DATA_LIST.clear();
        LOGGER.debug("Successfully reset all data!");
    }

    public static void fileInit() {
        if (MAIN_FOLDER == null) return;
        if (!MAIN_FOLDER.exists()) {
            if (!MAIN_FOLDER.mkdirs()) {
                throw new RuntimeException("Failed to create necessary SimpleEco folder!");
            }
        }
    }

}
