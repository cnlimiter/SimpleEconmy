package cn.evolvefield.mods.simpleeco.init;

import cn.evolvefield.mods.simpleeco.core.items.coins.CopperCoin;
import cn.evolvefield.mods.simpleeco.core.items.coins.GoldCoin;
import cn.evolvefield.mods.simpleeco.core.items.coins.SilverCoin;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);



    public static final RegistryObject<Item> copperCoin = ITEMS.register("copper_coin", CopperCoin::new);
    public static final RegistryObject<Item> sliverCoin = ITEMS.register("silver_coin", SilverCoin::new);
    public static final RegistryObject<Item> goldCoin = ITEMS.register("gold_coin", GoldCoin::new);

    public static final RegistryObject<SoundEvent> coinSounds = SOUNDS.register("item.coin_sound", () -> new SoundEvent(new ResourceLocation(MOD_ID, "item.coin_sound")));

}
