package cn.evolvefield.mods.simpleeco.core.tile.base;


import cn.evolvefield.mods.simpleeco.api.security.ISecurity;
import cn.evolvefield.mods.simpleeco.utils.Location;
import cn.evolvefield.mods.simpleeco.utils.UnitChatMessage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public abstract class TileEntityBase extends TileEntity implements ITickableTileEntity {

    public boolean enable;

    public TileEntityBase(final TileEntityType<?> tileEntityType) {
        super(tileEntityType);

        enable = true;
    }

    @Override
    public void tick () {

    }

    protected UnitChatMessage getUnitName (PlayerEntity player) {
        return new UnitChatMessage(getLocation().getBlock().getName().getString(), player);
    }

    public Location getLocation () {
        return new Location(level, getBlockPos());
    }

    public void markForUpdate () {

        if (level != null) {
            setChanged();
            level.setBlock(getBlockPos(), getBlockState(), 1, 1);
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 0);
            level.setBlockAndUpdate(getBlockPos(), getBlockState());
        }
    }

    @Override
    public void onDataPacket (NetworkManager net, SUpdateTileEntityPacket pkt) {
        load(getBlockState(), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        load(state, tag);
    }


    @Override
    public void load (BlockState state, CompoundNBT nbt) {

        if (this instanceof ISecurity) {

            ISecurity security = (ISecurity) this;
            security.getSecurityProfile().readFromNBT(nbt);
        }


        enable = nbt.getBoolean("enable");
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save (CompoundNBT nbt) {

        if (this instanceof ISecurity) {

            ISecurity security = (ISecurity) this;
            security.getSecurityProfile().writeToNBT(nbt);
        }



        nbt.putBoolean("enable", enable);
        return super.save(nbt);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket () {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        save(nbtTagCompound);
        int tileEntityType = 64;
        return new SUpdateTileEntityPacket(getBlockPos(), tileEntityType, nbtTagCompound);
    }

    @Override
    public CompoundNBT getUpdateTag () {
        CompoundNBT nbt = new CompoundNBT();
        save(nbt);
        return nbt;
    }

    @Override
    public CompoundNBT getTileData () {
        CompoundNBT nbt = new CompoundNBT();
        save(nbt);
        return nbt;
    }
}
