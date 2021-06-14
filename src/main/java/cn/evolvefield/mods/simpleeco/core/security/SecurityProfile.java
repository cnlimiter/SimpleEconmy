package cn.evolvefield.mods.simpleeco.core.security;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class SecurityProfile {

    private String ownerName = "";

    public String getOwnerName () {
        return ownerName;
    }

    public void setOwner (PlayerEntity player) {
        ownerName = player.getName().getString();
    }

    public boolean hasOwner () {
        return ownerName.isEmpty();
    }

    public boolean isOwner (String ownerName) {
        return this.ownerName.equalsIgnoreCase(ownerName);
    }

    public void readFromNBT (CompoundNBT nbt) {

        if (!nbt.getString("ownerName").isEmpty()) {
            ownerName = nbt.getString("ownerName");
        }
    }

    public void writeToNBT (CompoundNBT nbt) {

        if (!ownerName.isEmpty()) {
            nbt.putString("ownerName", ownerName);
        }
    }
}
