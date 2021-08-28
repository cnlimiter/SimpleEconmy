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

    public static void playClick (World world, PlayerEntity player) {
        world.playSound(player, player.getX(),player.getY(),player.getZ(), SoundEvents.LEVER_CLICK, player.getSoundSource(), 1, 1);
    }

    public static void playClang (World world, PlayerEntity player) {
        world.playSound(player, player.getX(),player.getY(),player.getZ(), SoundEvents.ANVIL_LAND, player.getSoundSource(), 0.9F, 1.1F);
    }

    public static void playWarp (World world, PlayerEntity player) {
        world.playSound(player, player.getX(),player.getY(),player.getZ(), SoundEvents.ENDERMAN_TELEPORT, player.getSoundSource(), 0.9F, 1.1F);
    }

    public static void playWarp (World world, PlayerEntity player, Location location) {
        world.playSound(player, location.getBlockPos(), SoundEvents.ENDERMAN_TELEPORT, player.getSoundSource(), 0.9F, 1.1F);
    }

    public static void playEraser (World world, PlayerEntity player, Location location) {
        world.playSound(player, location.getBlockPos(), SoundEvents.SLIME_ATTACK, player.getSoundSource(), 0.9F, 1.0F);
    }

    public static void playBlueprint (World world, PlayerEntity player, Location location) {
        world.playSound(player, location.getBlockPos(), SoundEvents.STONE_PLACE, player.getSoundSource(), 0.9F, 2.0F);
    }
}
