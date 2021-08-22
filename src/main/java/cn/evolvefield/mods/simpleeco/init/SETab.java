package cn.evolvefield.mods.simpleeco.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static cn.evolvefield.mods.simpleeco.SimpleEco.MOD_ID;

public class SETab extends ItemGroup {
    public SETab() {
        super(MOD_ID);
    }


    public static ItemGroup itemTab= new SETab();


    @Override
    public ItemStack makeIcon() {
        return new ItemStack(SERegistry.goldCoin.get().getItem());    }
}
