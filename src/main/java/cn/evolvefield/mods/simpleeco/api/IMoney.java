package cn.evolvefield.mods.simpleeco.api;

import java.util.UUID;

public interface IMoney {
    double getBalance( UUID id);
    boolean setBalance( UUID id, double value);
    boolean changeBalance(UUID id, double value);
    boolean transferBalance(UUID fromID, UUID toID, double value);

}
