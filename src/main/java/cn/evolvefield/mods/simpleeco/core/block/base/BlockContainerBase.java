package cn.evolvefield.mods.simpleeco.core.block.base;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;


public abstract class BlockContainerBase extends ContainerBlock {

    /**
     * @param properties The specific properties for the Block. (Creative Tab, hardness, material, etc.)
     */
    public BlockContainerBase(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity (BlockState state) {
        return true;
    }

    public BlockRenderType getRenderType (BlockState state) {
        return BlockRenderType.MODEL;
    }
}
