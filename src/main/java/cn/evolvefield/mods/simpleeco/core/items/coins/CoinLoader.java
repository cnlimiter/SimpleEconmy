package cn.evolvefield.mods.simpleeco.core.items.coins;

import cn.evolvefield.mods.simpleeco.core.SEConfig;
import cn.evolvefield.mods.simpleeco.data.AccountManager;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;

@Mod.EventBusSubscriber( modid=MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class CoinLoader {

    @SubscribeEvent
    public static void onCoinRightClick(PlayerInteractEvent.RightClickItem event) {

        if (!event.getWorld().isRemote ) {
            AccountManager data = AccountManager.get(event.getWorld().getServer().func_241755_D_());
            double bal = data.getBalance(event.getPlayer().getUniqueID());
            if (event.getPlayer().isCrouching() ){
//                if (event.getPlayer().getHeldItemMainhand().getItem() == copperCoin.getItem()) {
//                    int count = event.getPlayer().getHeldItemMainhand().getCount();
//                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
//                        data.setBalance(event.getPlayer().getUniqueID(), bal + count);
//                        copperCoin.setCount(0);
//                    }
//                    event.getPlayer().setItemInHand(Hand.MAIN_HAND, copperCoin);
//                    event.getPlayer().sendMessage(new TranslationTextComponent("message.copperCoin.useMore.success"
//                            , count, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());
//
//                }
//                if (event.getPlayer().getHeldItemMainhand().getItem() == sliverCoin.getItem()) {
//                    int count = event.getPlayer().getHeldItemMainhand().getCount();
//                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
//                        data.setBalance(event.getPlayer().getUniqueID(), bal + 10 * count);
//                        sliverCoin.setCount(0);
//                    }
//                    event.getPlayer().setItemInHand(Hand.MAIN_HAND, sliverCoin);
//                    event.getPlayer().sendMessage(new TranslationTextComponent("message.sliverCoin.useMore.success"
//                            , count, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());
//                }
//                if (event.getPlayer().getHeldItemMainhand().getItem() == goldCoin.getItem()) {
//                    int count = event.getPlayer().getHeldItemMainhand().getCount();
//                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
//                        data.setBalance(event.getPlayer().getUniqueID(), bal + 100 * count);
//                        goldCoin.setCount(0);
//                    }
//                    event.getPlayer().setItemInHand(Hand.MAIN_HAND, goldCoin);
//                    event.getPlayer().sendMessage(new TranslationTextComponent("message.goldCoin.useMore.success"
//                            , count, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());
//                }
                //铁
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/iron")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                    // ||ItemTags.getCollection().getTagByID(new ResourceLocation("coins:items/coin:1")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                ) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //铜
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/copper")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                    //||ItemTags.getCollection().getTagByID(new ResourceLocation("coins:items/coin:0")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                ) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 10);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //锡
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/tin")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 20);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //铅
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/lead")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 30);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //镍
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/nickel")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 40);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //殷钢
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/invar")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 42);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //青铜
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/bronze")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 50);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //康铜
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/constantan")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 50);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //银
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/silver")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 50);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //金
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/gold")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                    //||ItemTags.getCollection().getTagByID(new ResourceLocation("coins:items/coin:3")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                ) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 100);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //琥珀金
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/gold")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 150);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //信素
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/signalum")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 200);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //流明
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/lumium")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 200);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //流明
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/enderium")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(0);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + count * 300);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , count, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }

            } else {
                //铁
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/iron")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                    //||ItemTags.getCollection().getTagByID(new ResourceLocation("coins:items/coin:1")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                ) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 1);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //铜
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/copper")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                    //||ItemTags.getCollection().getTagByID(new ResourceLocation("coins:items/coin:0")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                ) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 10);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //锡
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/tin")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 20);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //铅
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/lead")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 30);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //镍
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/nickel")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 40);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //殷钢
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/invar")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 42);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //青铜
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/bronze")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 50);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //康铜
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/constantan")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 50);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //银
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/silver")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 50);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //金
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/gold")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                    //||ItemTags.getCollection().getTagByID(new ResourceLocation("coins:items/coin:3")).contains(event.getPlayer().getHeldItemMainhand().getItem())
                ) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 100);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //琥珀金
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/gold")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 150);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //信素
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/signalum")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 200);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //流明
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/lumium")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 200);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
                //末影
                if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/enderium")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
                    int count = event.getPlayer().getHeldItemMainhand().getCount();
                    ITextComponent name = event.getPlayer().getHeldItemMainhand().getDisplayName();
                    if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
                        event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
                        data.setBalance(event.getPlayer().getUniqueID(), bal + 300);
                    }
                    event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
                            , 1, name, SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());

                }
//                        if (event.getPlayer().getHeldItemMainhand().getItem() == copperCoin.getItem()) {
//                            if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
//                                copperCoin.setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
//                                event.getPlayer().setItemInHand(Hand.MAIN_HAND, copperCoin);
//                                data.setBalance(event.getPlayer().getUniqueID(), bal + 1);
//                            }
//                            event.getPlayer().sendMessage(new TranslationTextComponent("message.copperCoin.use.success"
//                                    , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());
//
//                        }
//                        if (event.getPlayer().getHeldItemMainhand().getItem() == sliverCoin.getItem()) {
//                            if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
//                                sliverCoin.setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
//                                event.getPlayer().setItemInHand(Hand.MAIN_HAND, sliverCoin);
//                                data.setBalance(event.getPlayer().getUniqueID(), bal + 10);
//                            }
//                            event.getPlayer().sendMessage(new TranslationTextComponent("message.sliverCoin.use.success"
//                                    , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());
//
//                        }
//                        if (event.getPlayer().getHeldItemMainhand().getItem() == goldCoin.getItem()) {
//                            if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
//                                goldCoin.setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
//                                event.getPlayer().setItemInHand(Hand.MAIN_HAND, goldCoin);
//                                data.setBalance(event.getPlayer().getUniqueID(), bal + 100);
//                            }
//                            event.getPlayer().sendMessage(new TranslationTextComponent("message.goldCoin.use.success"
//                                    , SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());
//                        }
//
//                        if (ItemTags.getCollection().getTagByID(new ResourceLocation("forge:coins/iron")).contains(event.getPlayer().getHeldItemMainhand().getItem())) {
//                            int count = event.getPlayer().getHeldItemMainhand().getCount();
//                            if (event.getPlayer().getHeldItemMainhand().getCount() >= 1) {
//                                event.getPlayer().getHeldItemMainhand().setCount(event.getPlayer().getHeldItemMainhand().getCount() - 1);
//                                //event.getPlayer().setItemInHand(Hand.MAIN_HAND, goldCoin);
//                                data.setBalance(event.getPlayer().getUniqueID(), bal + 100);
//                            }
//                            event.getPlayer().sendMessage(new TranslationTextComponent("message.coin.use.success"
//                                    , 1, event.getPlayer().getHeldItemMainhand().getDisplayName(), SEConfig.CURRENCY_SYMBOL.get() + data.getBalance(event.getPlayer().getUniqueID())), event.getPlayer().getUniqueID());
//
//
//                        }


            }

        }


    }

}
