package cn.evolvefield.mods.simpleeco.api.money;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.StringReader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerData {
    public static final List<PlayerData> PLAYER_DATA_LIST = new ArrayList<>();

    private PlayerEntity player;
    private UUID uuid;
    private String playerName;

    private boolean isFlyable;
    private long canFlyUntil = -1;

    private PlayerData(@NotNull UUID uuid, String playerName) {
        this.uuid = uuid;
        this.playerName = playerName;
    }

    public static @NotNull PlayerData getInstance(@NotNull PlayerEntity player) {
        GameProfile gameProfile = player.getGameProfile();
        PlayerData data = new PlayerData(gameProfile.getId(), gameProfile.getName());
        int i = PLAYER_DATA_LIST.indexOf(data);
        if (i != -1) {
            data = PLAYER_DATA_LIST.get(i);
        } else {
            PLAYER_DATA_LIST.add(data);
        }
        data.player = player;
        data.setFlyable(data.isFlyable);
        return data;
    }

    public @NotNull CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        // Info
        nbt.putString("uuid", this.uuid.toString());
        nbt.putString("name", this.playerName);

        // Fly
        nbt.putBoolean("flyable", this.isFlyable);
        nbt.putLong("canFlyUntil", this.canFlyUntil);


        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt) {
        try {
            this.uuid = UUID.fromString(nbt.getString("uuid"));
        } catch (IllegalArgumentException ignore) {}
        this.playerName = nbt.getString("name");
        this.isFlyable = nbt.getBoolean("flyable");
        this.canFlyUntil = nbt.getLong("canFlyUntil");


    }

    public boolean isFlyable() {
        return this.isFlyable;
    }

    /**
     * This method will do nothing if player is null
     * @param flyable flyable
     */
    public void setFlyable(boolean flyable) {
        if (this.player.isCreative()) {
            this.isFlyable = true;
            return;
        }
        if (this.player != null) {
            if (flyable) {
                this.player.abilities.mayfly = true;
            } else {
                this.player.abilities.mayfly = false;
                this.player.abilities.flying = false;
                //this.canFlyUntil = -1;
            }
            this.player.onUpdateAbilities();
            this.isFlyable = flyable;
        }
    }

    public long getCanFlyUntil() {
        return canFlyUntil;
    }

    public void setCanFlyUntil(long canFlyUntil) {
        this.canFlyUntil = canFlyUntil;
    }

    public @Nullable PlayerEntity getPlayer() {
        return this.player;
    }

    public static @Nullable PlayerEntity getPlayer(String playerName) {
        for (PlayerData data : PLAYER_DATA_LIST) {
            if (data.getName().equals(playerName)) {
                return data.getPlayer();
            }
        }
        return null;
    }

    /**
     * Get all names of online players
     * If the name of a player could not be input without quotation marks, format a quotation mark.
     * @return Formatted player names
     */
    public static @NotNull List<String> getAllPlayerNamesFormatted() {
        List<String> result = new ArrayList<>();
        PLAYER_DATA_LIST.forEach(data -> {
            StringBuilder name = new StringBuilder(data.getName());
            for (char c : name.toString().toCharArray()) {
                if (!StringReader.isAllowedInUnquotedString(c)) {
                    name = new StringBuilder("\"" + name + "\"");
                    break;
                }
            }
            result.add(name.toString());
        });
        return result;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerData)) return false;
        PlayerData that = (PlayerData) o;
        return this.uuid.equals(that.uuid);
    }


}
