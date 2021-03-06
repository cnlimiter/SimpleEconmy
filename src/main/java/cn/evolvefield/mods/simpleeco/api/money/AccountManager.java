package cn.evolvefield.mods.simpleeco.api.money;

import cn.evolvefield.mods.simpleeco.init.SEConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants.NBT;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;

public class AccountManager extends WorldSavedData implements IMoney {
    private static final String DATA_NAME = MOD_ID + "_data";

    public AccountManager() {super(DATA_NAME);}

    private final Map<UUID, Double> accounts = new HashMap<>();


    @Override
    public double getBalance(UUID owner) {
        accountChecker(owner);
        return accounts.get(owner);
    }

    @Override
    public boolean setBalance(UUID id, double value) {
            if (id != null) {
                accounts.put(id, value);
                this.setDirty();
                return true;
            }
        return false;
    }

    @Override
    public boolean changeBalance(UUID id, double value) {
        if ( id == null) return false;
        double current = getBalance(id);
        double future = current + value;
        return setBalance(id, future);
    }

    @Override
    public boolean transferBalance(UUID fromID, UUID toID, double value) {
        if (fromID == null ||toID == null) return false;
        double funds = Math.abs(value);
        double fromBal = getBalance(fromID);
        if (fromBal < funds) return false;
        if (changeBalance(fromID, -funds) && changeBalance(toID, funds)) {
            this.setDirty();
            return true;
        }
        else
            return false;
    }

    public void accountChecker(UUID owner) {
        if (owner != null && !accounts.containsKey(owner)) {
            accounts.put(owner, SEConfig.STARTING_FUNDS.get());
            this.setDirty();
        }
    }

    @Override
    public void load(CompoundNBT nbt) {
        ListNBT baseList = nbt.getList("players", NBT.TAG_COMPOUND);
        for (int b = 0; b < baseList.size(); b++) {
            CompoundNBT snbt = baseList.getCompound(b);
            UUID id = snbt.getUUID("id");
            double balance = snbt.getDouble("balance");
            accounts.put(id, balance);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        ListNBT baseList = new ListNBT();
        for (Map.Entry<UUID, Double> base : accounts.entrySet()) {
                CompoundNBT nbt1 = new CompoundNBT();
                nbt1.putUUID("id", base.getKey());
                nbt1.putDouble("balance", base.getValue());
                baseList.add(nbt1);
        }
        nbt.put("players", baseList);
        return nbt;
    }

    public static AccountManager get(ServerWorld world) {
        return world.getDataStorage().computeIfAbsent(AccountManager::new, DATA_NAME);
    }
}
