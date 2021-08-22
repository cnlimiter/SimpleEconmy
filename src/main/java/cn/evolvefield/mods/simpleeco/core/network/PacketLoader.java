package cn.evolvefield.mods.simpleeco.core.network;

import cn.evolvefield.mods.simpleeco.SimpleEco;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketLoader {
    public static SimpleChannel INSTANCE;
    public static final String VERSION = "1.0";
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessage() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(SimpleEco.MOD_ID, "main_networking"),
                () -> VERSION,
                (version) -> version.equals(VERSION),
                (version) -> version.equals(VERSION)
        );
        INSTANCE.messageBuilder(TestPack.class, nextID())
                .encoder(TestPack::encode)
                .decoder(TestPack::new)
                .consumer(TestPack::handle)
                .add();
        INSTANCE.messageBuilder(SetBalancePack.class, nextID())
                .encoder(SetBalancePack::encode)
                .decoder(SetBalancePack::new)
                .consumer(SetBalancePack::handler)
                .add();
        INSTANCE.messageBuilder(GetMoneyPack.class, nextID())
                .encoder(GetMoneyPack::encode)
                .decoder(GetMoneyPack::new)
                .consumer(GetMoneyPack::handler)
                .add();
    }
}
