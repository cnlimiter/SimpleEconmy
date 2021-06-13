package cn.evolvefield.mods.simpleeco.commands;

import cn.evolvefield.mods.simpleeco.data.AccountManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

import java.util.UUID;

public class PayCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("pay")
                .requires(src -> src.hasPermissionLevel(1))
                .then(Commands.argument("target", StringArgumentType.word())
                        .then(Commands.argument("value", DoubleArgumentType.doubleArg(0d))
                .executes(PayCommand::pay))));
    }

    public static int pay(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        ServerWorld world = context.getSource().getServer().func_241755_D_();
        double value = DoubleArgumentType.getDouble(context, "value");
        UUID recipient = context.getSource().getServer().getPlayerProfileCache().getGameProfileForUsername(StringArgumentType.getString(context, "recipient")).getId();
        if (AccountManager.get(world).transferBalance(player.getUniqueID(), recipient, value))
            context.getSource().sendFeedback(new TranslationTextComponent("message.command.pay.success", Math.abs(value), StringArgumentType.getString(context, "recipient")), true);
        else
            context.getSource().sendFeedback(new TranslationTextComponent("message.command.pay.failure"), false);
        return 0;
    }
}
