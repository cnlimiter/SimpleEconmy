package cn.evolvefield.mods.simpleeco.api.money;


import cn.evolvefield.mods.simpleeco.SimpleEco;
import cn.evolvefield.mods.simpleeco.main.SEConfig;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MoneyData implements IMoneyData{
    public static final List<MoneyData> PLAYER_DATA_LIST = new ArrayList<>();

    private PlayerEntity player;
    private UUID uuid;
    private String playerName;
    private Double money = SEConfig.STARTING_FUNDS.get();

    private MoneyData(@NotNull UUID uuid, String playerName) {
        this.uuid = uuid;
        this.playerName = playerName;
    }
    /**
     * This method should be used only after player loaded.
     * AttachCapability event happened before player loaded.
     */
    public static @NotNull MoneyData getInstance(@NotNull PlayerEntity player) {
        GameProfile gameProfile = player.getGameProfile();
        MoneyData data = new MoneyData(gameProfile.getId(), gameProfile.getName());
        int i = PLAYER_DATA_LIST.indexOf(data);
        if (i != -1) {
            data = PLAYER_DATA_LIST.get(i);
        } else {
            PLAYER_DATA_LIST.add(data);
        }
        data.player = player;
        return data;
    }

    @Override
    public double getBalance() {
        return this.money;
    }

    @Override
    public boolean setBalance(Double setMoney) {
        this.money = setMoney;
        return true;
    }

    @Override
    public boolean changeBalance(Double toMoney) {
        this.money += toMoney;
        return true;
    }

    @Override
    public boolean transferBalance(PlayerEntity fromPlayer, PlayerEntity toPlayer,  double value) {

        double funds = Math.abs(value);
        double fromBal = MoneyData.getInstance(fromPlayer).getBalance();
        if (fromBal < funds) return false;

        MoneyData.getInstance(fromPlayer).changeBalance(-funds);
        MoneyData.getInstance(toPlayer).changeBalance(funds);

        return true;
    }



    public @NotNull CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();

        // Info
        nbt.putString("uuid", this.uuid.toString());
        nbt.putString("name", this.playerName);

        nbt.putDouble("money", this.money);

        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        try {
            this.uuid = UUID.fromString(nbt.getString("uuid"));
        } catch (IllegalArgumentException ignore) {}
        this.playerName = nbt.getString("name");

        this.money = nbt.getDouble("money");


    }

    public @Nullable
    PlayerEntity getPlayer() {
        return this.player;
    }

    public static @Nullable PlayerEntity getPlayer(String playerName) {
        for (MoneyData data : PLAYER_DATA_LIST) {
            if (data.getName().equals(playerName)) {
                return data.getPlayer();
            }
        }
        return null;
    }

    public String getName() {
        if (this.playerName == null) {
            this.playerName =  this.player.getGameProfile().getName();
        }
        return this.playerName;
    }

    public UUID getUuid() {
        return this.uuid;
    }




    @Mod.EventBusSubscriber(modid = SimpleEco.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class EventHandler {

        @SubscribeEvent
        public static void onPlayerSaved(PlayerEvent.SaveToFile event) {
            try {
                File dataFile = new File(SimpleEco.PLAYER_DATA_FOLDER.getAbsolutePath() + "/" + event.getPlayerUUID() + ".dat");
                MoneyData data = MoneyData.getInstance(event.getPlayer());
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
                MoneyData.PLAYER_DATA_LIST.remove(MoneyData.getInstance(event.getPlayer()));
            }).start();
        }

        // Deserialize Player Data
        @SubscribeEvent(priority = EventPriority.HIGH)
        public static void onPlayerLoaded(PlayerEvent.LoadFromFile event) {
            File dataFile = new File(SimpleEco.PLAYER_DATA_FOLDER.getAbsolutePath() + "/" + event.getPlayerUUID() + ".dat");
            if (dataFile.exists()) {
                try {
                    CompoundNBT dataNbt = CompressedStreamTools.readCompressed(dataFile);
                    MoneyData data = MoneyData.getInstance(event.getPlayer());
                    data.deserializeNBT(dataNbt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
