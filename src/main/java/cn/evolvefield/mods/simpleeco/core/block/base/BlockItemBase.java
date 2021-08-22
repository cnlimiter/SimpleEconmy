package cn.evolvefield.mods.simpleeco.core.block.base;

import cn.evolvefield.mods.simpleeco.init.SETab;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;


public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block, boolean onCreativeTab) {
        super(block, onCreativeTab ? new Properties().tab(SETab.itemTab) : new Properties());
    }

    public BlockItemBase(Block block) {
        this(block, true);
    }
}
