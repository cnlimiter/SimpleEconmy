package cn.evolvefield.mods.simpleeco;

import cn.evolvefield.mods.simpleeco.init.SEConfig;
import cn.evolvefield.mods.simpleeco.init.ModItems;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.storage.FolderName;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
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

import static net.minecraft.world.storage.FolderName.PLAYER_DATA_DIR;


@Mod("simpleeco")
public class SimpleEco {
    public static final String MOD_ID = "simpleeco";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static MinecraftServer SERVER = ServerLifecycleHooks.getCurrentServer();

    public static File MAIN_FOLDER;
    public static File PLAYER_DATA_FOLDER;

    public static IEventBus MOD_EVENT_BUS;

    public SimpleEco() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, this::onServerAboutToStart);
        MinecraftForge.EVENT_BUS.addListener(this::onWorldSave);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SEConfig.SERVER_CONFIG);

        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }


    private void onServerAboutToStart(FMLServerAboutToStartEvent event) {
        SERVER = event.getServer();
        //MAIN_FOLDER = new File(FMLPaths.GAMEDIR.get().toFile() + "/" + "SimpleEco") ;
        MAIN_FOLDER = SERVER.getWorldPath(new FolderName("SimpleEco")).toFile();
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
        PLAYER_DATA_FOLDER = new File(MAIN_FOLDER.getAbsolutePath() + "/" + "playerData");
        if (!PLAYER_DATA_FOLDER.exists()) {
            if (!PLAYER_DATA_FOLDER.mkdirs()) {
                throw new RuntimeException("Failed to create necessary player data folder!");
            }
        }
    }

}
