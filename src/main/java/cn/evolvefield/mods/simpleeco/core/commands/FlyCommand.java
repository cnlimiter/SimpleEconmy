package cn.evolvefield.mods.simpleeco.core.commands;

import cn.evolvefield.mods.simpleeco.SimpleEco;
import cn.evolvefield.mods.simpleeco.api.money.AccountManager;
import cn.evolvefield.mods.simpleeco.api.money.PlayerData;
import cn.evolvefield.mods.simpleeco.init.SEConfig;
import cn.evolvefield.mods.simpleeco.utils.TextUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.Util;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlyCommand {

    public static String datePattern = SEConfig.DATE_PATTERN.get();
    public static Integer flyFactor = SEConfig.FLY_FACTOR.get();

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("fly")
                .executes(FlyCommand::process)
                .requires(src -> src.hasPermission(0))
                .then(Commands.literal("buy")
                        .then(Commands.argument("minutes", IntegerArgumentType.integer())
                                .executes(context -> buyMinutes(context.getSource().getPlayerOrException(), IntegerArgumentType.getInteger(context, "minutes")))))






        );
    }

    private static int buyMinutes(ServerPlayerEntity player,int... minutes)throws CommandSyntaxException {
        AccountManager wsd = AccountManager.get(player.level.getServer().overworld());
        PlayerData data = PlayerData.getInstance(player);
        String symbol = SEConfig.CURRENCY_SYMBOL.get();
        double balP = wsd.getBalance(player.getUUID());


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        long flyUntil = data.getCanFlyUntil();
        long flyTime = data.getCanFlyTime();

        long minutes1 = flyUntil/(60 * 1000L);
        long canFlyUntil = System.currentTimeMillis() + minutes[0] * 60 * 1000L;
        double balU = minutes[0] * flyFactor;
        wsd.setBalance(player.getUUID(),balP - balU);
        player.sendMessage(new TranslationTextComponent("message.command.fly.buy.success",symbol + balU,minutes[0]),Util.NIL_UUID);
        if (flyUntil ==  -1) {
            data.setCanFlyUntil(canFlyUntil);
            data.setCanFlyTime(minutes[0] * 60* 1000L);//ms
            Date date = new Date(canFlyUntil);
            String formattedDate = simpleDateFormat.format(date);
            player.sendMessage(new TranslationTextComponent("message.command.fly.flyTemp",data.getName(),  formattedDate).withStyle(TextFormatting.GREEN), Util.NIL_UUID);
        }
        else {
            data.setCanFlyUntil(System.currentTimeMillis() + (minutes1+minutes[0])* 60 * 1000L);
            data.setCanFlyTime(flyTime + minutes[0] * 60* 1000L);//ms
            Date date = new Date(System.currentTimeMillis() + (minutes1+minutes[0])* 60 * 1000L);
            String formattedDate = simpleDateFormat.format(date);
            player.sendMessage(new TranslationTextComponent("message.command.fly.flyTemp",data.getName(),  formattedDate).withStyle(TextFormatting.GREEN), Util.NIL_UUID);
        }

        try {
            File dataFile = new File(SimpleEco.PLAYER_DATA_FOLDER.getAbsolutePath() + "/" + player.getGameProfile().getName() + ".dat");
            CompressedStreamTools.writeCompressed(data.serializeNBT(), dataFile);
            SimpleEco.LOGGER.debug("Successfully save player " + data.getUuid() + " to file!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
    private static int process(CommandContext<CommandSource> source)throws CommandSyntaxException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        PlayerEntity player = source.getSource().getPlayerOrException();
        CommandSource ctx = source.getSource();
        PlayerData data = PlayerData.getInstance(player);
        long now = System.currentTimeMillis();
        long flyUntil = data.getCanFlyUntil();
        long flyTime = data.getCanFlyTime();
        if (player.isCreative()) {
            player.sendMessage(new TranslationTextComponent("message.command.fly.cantSetFly",data.getName()).withStyle(TextFormatting.YELLOW), Util.NIL_UUID);
            return 1;
        } else if (data.isFlyable()) {
            data.setFlyable(false);
            long useTime = now - data.getStartFlyTime();
            data.setCanFlyTime(flyTime - useTime);
            long  date = (flyTime -useTime)/ 1000L;
            ctx.sendSuccess(new TranslationTextComponent("message.command.fly.cantFly",date).withStyle(TextFormatting.YELLOW), true);
            try {
                File dataFile = new File(SimpleEco.PLAYER_DATA_FOLDER.getAbsolutePath() + "/" + player.getGameProfile().getName() + ".dat");
                CompressedStreamTools.writeCompressed(data.serializeNBT(), dataFile);
                SimpleEco.LOGGER.debug("Successfully save player " + data.getUuid() + " to file!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        } else if(data.getCanFlyTime() > 0){
            data.setFlyable(true);
            data.setStartFlyTime(now);
            data.setCanFlyUntil(now + flyTime);
            ctx.sendSuccess(new TranslationTextComponent("message.command.fly.canFly").withStyle(TextFormatting.GREEN), true);
            try {
                File dataFile = new File(SimpleEco.PLAYER_DATA_FOLDER.getAbsolutePath() + "/" + player.getGameProfile().getName() + ".dat");
                CompressedStreamTools.writeCompressed(data.serializeNBT(), dataFile);
                SimpleEco.LOGGER.debug("Successfully save player " + data.getUuid() + " to file!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }
        else {
            player.sendMessage(new TranslationTextComponent("message.command.fly.cantSetFly",data.getName()).withStyle(TextFormatting.YELLOW), Util.NIL_UUID);
        }
        return 0;
    }

}
