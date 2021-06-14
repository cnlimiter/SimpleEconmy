package cn.evolvefield.mods.simpleeco.main;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;

public class SETab extends ItemGroup {
    public SETab() {
        super(MOD_ID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(SERegistry.goldCoin.get().getItem());
    }

    public static ItemGroup itemTab= new SETab();


}
