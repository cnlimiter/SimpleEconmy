package cn.evolvefield.mods.simpleeco.init.event;


import cn.evolvefield.mods.simpleeco.SimpleEco;
import cn.evolvefield.mods.simpleeco.api.money.PlayerData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.io.IOException;

@Mod.EventBusSubscriber(modid = SimpleEco.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeLoader {

    private static int counter = 0;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (counter >= 20) {
                counter = 0;
                new Thread(() -> {
                    long now = System.currentTimeMillis();

                    PlayerData.PLAYER_DATA_LIST.stream().filter(player -> player.getPlayer() != null &&
                                    player.isFlyable() &&
                                    player.getCanFlyUntil() != -1 &&
                                    player.getCanFlyUntil() <= now)
                            .forEach(player -> {
                                player.setFlyable(false);
                                player.setCanFlyUntil(-1);
                                player.setCanFlyTime(-1);
                                player.setStartFlyTime(-1);
                                player.getPlayer().sendMessage(new TranslationTextComponent("message.command.fly.cantFlyNow").withStyle(TextFormatting.YELLOW), Util.NIL_UUID);

                            });
                }).start();
            }
            counter++;
        }

    }


    @SubscribeEvent
    public static void onPlayerSaved(PlayerEvent.SaveToFile event) {
        try {
            File dataFile = new File(SimpleEco.PLAYER_DATA_FOLDER.getAbsolutePath() + "/" + event.getPlayer().getGameProfile().getName() + ".dat");
            PlayerData data = PlayerData.getInstance(event.getPlayer());
            CompressedStreamTools.writeCompressed(data.serializeNBT(), dataFile);
            SimpleEco.LOGGER.debug("Successfully save player " + data.getUuid() + " to file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        // delay to remove player because this event is fired before SaveToFile event.
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PlayerData.PLAYER_DATA_LIST.remove(PlayerData.getInstance(event.getPlayer()));
        }).start();
    }

    // Deserialize Player Data
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onPlayerLoaded(PlayerEvent.LoadFromFile event) {
        File dataFile = new File(SimpleEco.PLAYER_DATA_FOLDER.getAbsolutePath() + "/" + event.getPlayer().getGameProfile().getName() + ".dat");
        if (dataFile.exists()) {
            try {
                CompoundNBT dataNbt = CompressedStreamTools.readCompressed(dataFile);
                PlayerData data = PlayerData.getInstance(event.getPlayer());
                data.deserializeNBT(dataNbt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDied(LivingDeathEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (!entity.level.isClientSide) {
            if (entity instanceof PlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) entity;
                PlayerData data = PlayerData.getInstance(player);

            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerData data = PlayerData.getInstance(event.getPlayer());
        data.setFlyable(data.isFlyable());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent e) {
        PlayerData data = PlayerData.getInstance(e.getPlayer());
        data.setFlyable(data.isFlyable());
    }


    @SubscribeEvent
    public static void onPlayerChangeGameMode(PlayerEvent.PlayerChangeGameModeEvent event) {
        if (event.getCurrentGameMode().isCreative() && event.getNewGameMode().isSurvival()) {
            new Thread(() -> {
                // after game mode changed, if player is flyable, then flyable
                PlayerData data = PlayerData.getInstance(event.getPlayer());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.setFlyable(data.isFlyable());
            }).start();
        }
    }
}
