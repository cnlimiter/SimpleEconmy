package cn.evolvefield.mods.simpleeco.core.items.coins;

import cn.evolvefield.mods.simpleeco.core.SEConfig;
import cn.evolvefield.mods.simpleeco.core.SERegistry;
import cn.evolvefield.mods.simpleeco.data.AccountManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;

@Mod.EventBusSubscriber( modid=MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class CoinLoader {

    @SubscribeEvent
    public static void onCoinRightClick(PlayerInteractEvent.RightClickItem event){
        if(!event.getWorld().isClientSide){
            AccountManager data = AccountManager.get(event.getWorld().getServer().overworld());
            double bal = data.getBalance(event.getPlayer().getUUID());
            ItemStack copperCoin = new ItemStack(SERegistry.copperCoin.get());
            ItemStack sliverCoin = new ItemStack(SERegistry.sliverCoin.get());
            ItemStack goldCoin = new ItemStack(SERegistry.goldCoin.get());
            if(event.getPlayer().isCrouching())
            {
                if (event.getPlayer().getMainHandItem().getItem() == copperCoin.getItem()) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        data.setBalance(event.getPlayer().getUUID(), bal + count);
                        copperCoin.setCount(0);
                    }
                    event.getPlayer().setItemInHand(Hand.MAIN_HAND, copperCoin);
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.copperCoin.useMore.success"
                            ,count , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                if (event.getPlayer().getMainHandItem().getItem() == sliverCoin.getItem()) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        data.setBalance(event.getPlayer().getUUID(), bal + 10 * count);
                        sliverCoin.setCount(0);
                    }
                    event.getPlayer().setItemInHand(Hand.MAIN_HAND, sliverCoin);
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.sliverCoin.useMore.success"
                            ,count , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
                }
                if (event.getPlayer().getMainHandItem().getItem() == goldCoin.getItem()) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        data.setBalance(event.getPlayer().getUUID(), bal + 100 * count);
                        goldCoin.setCount(0);
                    }
                    event.getPlayer().setItemInHand(Hand.MAIN_HAND, goldCoin);
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.goldCoin.useMore.success"
                            ,count , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
                }
            }
            else
            {
                if (event.getPlayer().getMainHandItem().getItem() == copperCoin.getItem()) {
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        copperCoin.setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                        event.getPlayer().setItemInHand(Hand.MAIN_HAND, copperCoin);
                        data.setBalance(event.getPlayer().getUUID(), bal + 1);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.copperCoin.use.success"
                            , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                if (event.getPlayer().getMainHandItem().getItem() == sliverCoin.getItem()) {
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        sliverCoin.setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                        event.getPlayer().setItemInHand(Hand.MAIN_HAND, sliverCoin);
                        data.setBalance(event.getPlayer().getUUID(), bal + 10);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.sliverCoin.use.success"
                            , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                if (event.getPlayer().getMainHandItem().getItem() == goldCoin.getItem()) {
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        goldCoin.setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                        event.getPlayer().setItemInHand(Hand.MAIN_HAND, goldCoin);
                        data.setBalance(event.getPlayer().getUUID(), bal + 100);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.goldCoin.use.success"
                            , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
                }



            }

        }


    }
}
