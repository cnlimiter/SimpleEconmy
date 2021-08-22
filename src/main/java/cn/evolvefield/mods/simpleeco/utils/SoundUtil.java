package cn.evolvefield.mods.simpleeco.utils;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class SoundUtil {

    public static void playBlockPlaceSound (World world, PlayerEntity player, IForgeBlockState state, Location location) {
        world.playSound(player, location.getBlockPos(), state.getBlockState().getBlock().getSoundType(state.getBlockState(), world, location.getBlockPos(), player).getPlaceSound(), SoundCategory.NEUTRAL, 1.5F, 0.9F);
    }



    public static void playDing (World world, PlayerEntity player) {
        world.playSound(player, player.getX(),player.getY(),player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundCategory.NEUTRAL, 1, 1);
    }

//    public static void playClick (World world, PlayerEntity player) {
//        world.playSound(player, player.getX(),player.getY(),player.getZ(), SoundEvents.BLOCK_LEVER_CLICK, player.getSoundCategory(), 1, 1);
//    }
//
//    public static void playClang (World world, PlayerEntity player) {
//        world.playSound(player, player.getX(),player.getY(),player.getZ(), SoundEvents.BLOCK_ANVIL_LAND, player.getSoundCategory(), 0.9F, 1.1F);
//    }
//
//    public static void playWarp (World world, PlayerEntity player) {
//        world.playSound(player, player.getX(),player.getY(),player.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, player.getSoundCategory(), 0.9F, 1.1F);
//    }
//
//    public static void playWarp (World world, PlayerEntity player, Location location) {
//        world.playSound(player, location.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, player.getSoundCategory(), 0.9F, 1.1F);
//    }
//
//    public static void playEraser (World world, PlayerEntity player, Location location) {
//        world.playSound(player, location.getBlockPos(), SoundEvents.ENTITY_SLIME_ATTACK, player.getSoundCategory(), 0.9F, 1.0F);
//    }
//
//    public static void playBlueprint (World world, PlayerEntity player, Location location) {
//        world.playSound(player, location.getBlockPos(), SoundEvents.BLOCK_STONE_PLACE, player.getSoundCategory(), 0.9F, 2.0F);
//    }
}
