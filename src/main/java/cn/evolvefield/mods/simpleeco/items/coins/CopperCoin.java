package cn.evolvefield.mods.simpleeco.items.coins;

import cn.evolvefield.mods.simpleeco.main.SETab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class CopperCoin extends Item {
    public CopperCoin() {
        super(new Properties().group(SETab.itemTab));
    }


    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltipList, ITooltipFlag flag) {
        tooltipList.add(new TranslationTextComponent("tooltip.item.copperCoin.value"));
    }



}

