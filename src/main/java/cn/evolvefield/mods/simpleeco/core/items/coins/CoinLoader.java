package cn.evolvefield.mods.simpleeco.core.items.coins;

import cn.evolvefield.mods.simpleeco.core.SEConfig;
import cn.evolvefield.mods.simpleeco.core.SERegistry;
import cn.evolvefield.mods.simpleeco.data.AccountManager;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;

@Mod.EventBusSubscriber( modid=MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class CoinLoader {

    @SubscribeEvent
    public static void onCoinRightClick(PlayerInteractEvent.RightClickItem event) {
        if (!event.getWorld().isClientSide) {
            AccountManager data = AccountManager.get(event.getWorld().getServer().overworld());
            double bal = data.getBalance(event.getPlayer().getUUID());
            ItemStack copperCoin = new ItemStack(SERegistry.copperCoin.get());
            ItemStack sliverCoin = new ItemStack(SERegistry.sliverCoin.get());
            ItemStack goldCoin = new ItemStack(SERegistry.goldCoin.get());
            if (event.getPlayer().isCrouching()) {
//                if (event.getPlayer().getMainHandItem().getItem() == copperCoin.getItem()) {
//                    int count = event.getPlayer().getMainHandItem().getCount();
//                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
//                        data.setBalance(event.getPlayer().getUUID(), bal + count);
//                        copperCoin.setCount(0);
//                    }
//                    event.getPlayer().setItemInHand(Hand.MAIN_HAND, copperCoin);
//                    event.getPlayer().sendMessage(new TranslationTextComponent("message.copperCoin.useMore.success"
//                            , count, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
//
//                }
//                if (event.getPlayer().getMainHandItem().getItem() == sliverCoin.getItem()) {
//                    int count = event.getPlayer().getMainHandItem().getCount();
//                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
//                        data.setBalance(event.getPlayer().getUUID(), bal + 10 * count);
//                        sliverCoin.setCount(0);
//                    }
//                    event.getPlayer().setItemInHand(Hand.MAIN_HAND, sliverCoin);
//                    event.getPlayer().sendMessage(new TranslationTextComponent("message.sliverCoin.useMore.success"
//                            , count, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
//                }
//                if (event.getPlayer().getMainHandItem().getItem() == goldCoin.getItem()) {
//                    int count = event.getPlayer().getMainHandItem().getCount();
//                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
//                        data.setBalance(event.getPlayer().getUUID(), bal + 100 * count);
//                        goldCoin.setCount(0);
//                    }
//                    event.getPlayer().setItemInHand(Hand.MAIN_HAND, goldCoin);
//                    event.getPlayer().sendMessage(new TranslationTextComponent("message.goldCoin.useMore.success"
//                            , count, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
//                }
                //铁
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/iron")).contains(event.getPlayer().getMainHandItem().getItem())
                       // ||ItemTags.getAllTags().getTag(new ResourceLocation("coins:items/coin:1")).contains(event.getPlayer().getMainHandItem().getItem())
                ) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //铜
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/copper")).contains(event.getPlayer().getMainHandItem().getItem())
                //||ItemTags.getAllTags().getTag(new ResourceLocation("coins:items/coin:0")).contains(event.getPlayer().getMainHandItem().getItem())
                ) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 10);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //锡
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/tin")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 20);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //铅
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/lead")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 30);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //镍
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/nickel")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 40);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //殷钢
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/invar")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 42);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //青铜
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/bronze")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 50);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //康铜
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/constantan")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 50);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //银
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/sliver")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 50);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //金
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/gold")).contains(event.getPlayer().getMainHandItem().getItem())
                        //||ItemTags.getAllTags().getTag(new ResourceLocation("coins:items/coin:3")).contains(event.getPlayer().getMainHandItem().getItem())
                ) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count *100);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //琥珀金
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/gold")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 150);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //信素
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/signalum")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 200);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //流明
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/lumium")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 200);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }
                //流明
                if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/enderium")).contains(event.getPlayer().getMainHandItem().getItem())) {
                    int count = event.getPlayer().getMainHandItem().getCount();
                    ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                    if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                        event.getPlayer().getMainHandItem().setCount(0);
                        data.setBalance(event.getPlayer().getUUID(), bal + count * 300);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                }

                } else {
                    //铁
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/iron")).contains(event.getPlayer().getMainHandItem().getItem())
                            //||ItemTags.getAllTags().getTag(new ResourceLocation("coins:items/coin:1")).contains(event.getPlayer().getMainHandItem().getItem())
                           ) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal + 1);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //铜
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/copper")).contains(event.getPlayer().getMainHandItem().getItem())
                            //||ItemTags.getAllTags().getTag(new ResourceLocation("coins:items/coin:0")).contains(event.getPlayer().getMainHandItem().getItem())
                    ) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  10);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //锡
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/tin")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal + 20);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //铅
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/lead")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  30);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //镍
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/nickel")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  40);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //殷钢
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/invar")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  42);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //青铜
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/bronze")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  50);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //康铜
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/constantan")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  50);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //银
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/sliver")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  50);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //金
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/gold")).contains(event.getPlayer().getMainHandItem().getItem())
                            //||ItemTags.getAllTags().getTag(new ResourceLocation("coins:items/coin:3")).contains(event.getPlayer().getMainHandItem().getItem())
                    ) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal + 100);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //琥珀金
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/gold")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal + 150);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //信素
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/signalum")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  200);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //流明
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/lumium")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  200);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
                    //流明
                    if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/enderium")).contains(event.getPlayer().getMainHandItem().getItem())) {
                        int count = event.getPlayer().getMainHandItem().getCount();
                        ITextComponent name = event.getPlayer().getMainHandItem().getDisplayName();
                        if (event.getPlayer().getMainHandItem().getCount() >= 1) {
                            event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
                            data.setBalance(event.getPlayer().getUUID(), bal +  300);
                        }
                        event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                                , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());

                    }
//                        if (event.getPlayer().getMainHandItem().getItem() == copperCoin.getItem()) {
//                            if (event.getPlayer().getMainHandItem().getCount() >= 1) {
//                                copperCoin.setCount(event.getPlayer().getMainHandItem().getCount() - 1);
//                                event.getPlayer().setItemInHand(Hand.MAIN_HAND, copperCoin);
//                                data.setBalance(event.getPlayer().getUUID(), bal + 1);
//                            }
//                            event.getPlayer().sendMessage(new TranslationTextComponent("message.copperCoin.use.success"
//                                    , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
//
//                        }
//                        if (event.getPlayer().getMainHandItem().getItem() == sliverCoin.getItem()) {
//                            if (event.getPlayer().getMainHandItem().getCount() >= 1) {
//                                sliverCoin.setCount(event.getPlayer().getMainHandItem().getCount() - 1);
//                                event.getPlayer().setItemInHand(Hand.MAIN_HAND, sliverCoin);
//                                data.setBalance(event.getPlayer().getUUID(), bal + 10);
//                            }
//                            event.getPlayer().sendMessage(new TranslationTextComponent("message.sliverCoin.use.success"
//                                    , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
//
//                        }
//                        if (event.getPlayer().getMainHandItem().getItem() == goldCoin.getItem()) {
//                            if (event.getPlayer().getMainHandItem().getCount() >= 1) {
//                                goldCoin.setCount(event.getPlayer().getMainHandItem().getCount() - 1);
//                                event.getPlayer().setItemInHand(Hand.MAIN_HAND, goldCoin);
//                                data.setBalance(event.getPlayer().getUUID(), bal + 100);
//                            }
//                            event.getPlayer().sendMessage(new TranslationTextComponent("message.goldCoin.use.success"
//                                    , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
//                        }
//
//                        if (ItemTags.getAllTags().getTag(new ResourceLocation("forge:coins/iron")).contains(event.getPlayer().getMainHandItem().getItem())) {
//                            int count = event.getPlayer().getMainHandItem().getCount();
//                            if (event.getPlayer().getMainHandItem().getCount() >= 1) {
//                                event.getPlayer().getMainHandItem().setCount(event.getPlayer().getMainHandItem().getCount() - 1);
//                                //event.getPlayer().setItemInHand(Hand.MAIN_HAND, goldCoin);
//                                data.setBalance(event.getPlayer().getUUID(), bal + 100);
//                            }
//                            event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
//                                    , 1, event.getPlayer().getMainHandItem().getDisplayName(), SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUUID())), event.getPlayer().getUUID());
//
//
//                        }


                    }

                }


        }

}
