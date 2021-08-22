package cn.evolvefield.mods.simpleeco.core.network;

import cn.evolvefield.mods.simpleeco.api.money.AccountManager;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SetBalancePack {
    private final UUID uuid;
    private final Double money;

    public SetBalancePack(UUID uuid1, Double setMoney){
        this.money = setMoney;
        this.uuid = uuid1;

    }

    public SetBalancePack(PacketBuffer buffer){
        uuid = buffer.readUUID();
        money = buffer.readDouble();
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeUUID(this.uuid);
        buffer.writeDouble(this.money);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        AccountManager data = AccountManager.get(ctx.get().getSender().getServer().overworld());
        ctx.get().enqueueWork(() -> {
            data.setBalance(this.uuid, this.money);


        });
        ctx.get().setPacketHandled(true);
    }
}
