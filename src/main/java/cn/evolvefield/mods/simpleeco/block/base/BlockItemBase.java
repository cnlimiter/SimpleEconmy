package cn.evolvefield.mods.simpleeco.block.base;

import cn.evolvefield.mods.simpleeco.main.SETab;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;


public class BlockItemBase extends BlockItem {

    public BlockItemBase(Block block, boolean onCreativeTab) {
        super(block, onCreativeTab ? new Properties().group(SETab.itemTab) : new Properties());
    }

    public BlockItemBase(Block block) {
        this(block, true);
    }
}
