package cn.evolvefield.mods.simpleeco.core.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class TestPack extends AbstractPacket {
    private final String message;
    private static final Logger LOGGER = LogManager.getLogger();

    public TestPack(PacketBuffer buffer) {
        message = buffer.readUtf(Short.MAX_VALUE);
    }

    public TestPack(String message) {
        this.message = message;
    }

    public void encode(PacketBuffer buf) {
        buf.writeUtf(this.message);
    }

    @Override
    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            LOGGER.info(this.message);
        });
        context.get().setPacketHandled(true);

    }


}
