package cn.evolvefield.mods.simpleeco.api.money;

import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public interface IMoneyData {
    double getBalance();
    boolean setBalance(Double value);
    boolean changeBalance(Double value);
    boolean transferBalance(PlayerEntity fromPlayer, PlayerEntity toPlayer, double value);
}
