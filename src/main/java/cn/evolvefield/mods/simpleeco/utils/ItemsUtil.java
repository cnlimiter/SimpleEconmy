package cn.evolvefield.mods.simpleeco.utils;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.world.World;

import java.util.Random;

public class ItemsUtil {
    private static final Random rand = new Random();

    public static void attachNBTFromString (ItemStack stack, String nbtString) {

        try {
            stack.setTag(JsonToNBT.getTagFromJson(nbtString));
        }

        catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
    }

    public static ItemEntity spawnStackAtLocation(World world, Location location, ItemStack stack) {
        return spawnStack(world, location.x + 0.5F, location.y + 0.5F, location.z + 0.5F, stack);
    }

    private static ItemEntity spawnStack(World world, float x, float y, float z, ItemStack stack) {
        ItemEntity item = new ItemEntity(world, x, y, z, stack);
        item.setNoPickupDelay();
        item.setMotion(-0.05F + rand.nextFloat() * 0.1F, -0.05F + rand.nextFloat() * 0.1F, -0.05F + rand.nextFloat() * 0.1F);
        world.addEntity(item);
        return item;
    }
}
