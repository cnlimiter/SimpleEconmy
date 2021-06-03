package cn.evolvefield.mods.simpleeco.data;

import cn.evolvefield.mods.simpleeco.api.IMoney;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

public class MoneyManager implements IMoney {

    private static final MoneyManager INSTANCE = new MoneyManager();
    private MoneyManager() {}
    public static MoneyManager get() {return INSTANCE;}
    private ServerWorld world;

    public void setWorld(ServerWorld world) {this.world = world;}

    @Override
    public double getBalance(UUID id) {
        return AccountManager.get(world).getBalance( id);
    }
    @Override
    public boolean setBalance(UUID id, double value) {
        return AccountManager.get(world).setBalance( id, value);
    }
    @Override
    public boolean changeBalance(UUID id, double value) {
        return AccountManager.get(world).changeBalance( id, value);
    }
    @Override
    public boolean transferBalance(UUID fromID, UUID toID,
                                   double value) {
        return AccountManager.get(world).transferBalance(fromID,  toID, value);
    }
}
