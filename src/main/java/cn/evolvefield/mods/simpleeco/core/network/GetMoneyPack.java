package cn.evolvefield.mods.simpleeco.core.network;

import cn.evolvefield.mods.simpleeco.api.money.AccountManager;
import cn.evolvefield.mods.simpleeco.init.SEConfig;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class GetMoneyPack {
    private final UUID uuid;

    public GetMoneyPack(UUID uuid1){
        this.uuid = uuid1;

    }

    public GetMoneyPack(PacketBuffer buffer){
        uuid = buffer.readUUID();
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeUUID(this.uuid);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        AccountManager data = AccountManager.get(ctx.get().getSender().getServer().overworld());
        String symbol = SEConfig.CURRENCY_SYMBOL.get();
                ctx.get().enqueueWork(() -> {
                    double bal =  data.getBalance(this.uuid);
                    ctx.get().getSender().sendMessage(new StringTextComponent(symbol+ bal), ctx.get().getSender().getUUID());
                });
               ctx.get().setPacketHandled(true);
    }
}
