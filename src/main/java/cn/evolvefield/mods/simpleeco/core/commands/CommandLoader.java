package cn.evolvefield.mods.simpleeco.core.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommandLoader {
    public static boolean init = false;

    private static void register(CommandDispatcher<CommandSource> dispatcher) {
        MoneyCommand.register(dispatcher);
        EcoCommand.register(dispatcher);
        PayCommand.register(dispatcher);
        FlyCommand.register(dispatcher);
    }

    @SubscribeEvent
    public static void register(FMLServerAboutToStartEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getServer().getCommands().getDispatcher();
        if (!init) {
            register(dispatcher);
            init = true;
        }
    }

    /**
     * @param event RegisterCommandsEvent
     */
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        if (init) {
            register(dispatcher);
        }
    }

    @SubscribeEvent
    public static void onServerStopped(FMLServerStoppedEvent event) {
        init = false;
    }
}
