package cn.evolvefield.mods.simpleeco.commands;

import cn.evolvefield.mods.simpleeco.core.SEConfig;
import cn.evolvefield.mods.simpleeco.data.AccountManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.UUID;

public class EcoCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                LiteralArgumentBuilder.<CommandSource>literal("eco")
                .requires(src -> src.hasPermission(SEConfig.ADMIN_LEVEL.get()))
                .then(Commands.literal("balance")
                        .then(Commands.argument("player", StringArgumentType.word())
                                .executes(EcoCommand::balance)))
                .then(Commands.argument("action", StringArgumentType.word())
                        .suggests((c, b) -> b
                                .suggest("balance")
                                .suggest("set")
                                .suggest("give")
                                .suggest("remove")
                                .buildFuture())
                        .then(Commands.argument("player", StringArgumentType.word())
                                .executes(EcoCommand::process)
                                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0d))
                                        .executes(EcoCommand::process))))
                .then(Commands.literal("trans")
                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg(0))
                                .then(Commands.argument("from", StringArgumentType.word())
                                        .then(Commands.argument("to", StringArgumentType.word())
                                                .executes(EcoCommand::transfer))))));
    }


    private static int process(CommandContext<CommandSource> context) {
        AccountManager data = AccountManager.get(context.getSource().getServer().overworld());
        MinecraftServer server = context.getSource().getServer();
        String option = StringArgumentType.getString(context, "action");
        String target = StringArgumentType.getString(context, "player");
        String symbol = SEConfig.CURRENCY_SYMBOL.get();
        double value = DoubleArgumentType.getDouble(context, "amount");
        UUID player = server.getProfileCache().get(target).getId();
        if (player == null) {
            context.getSource().sendFailure(new TranslationTextComponent("message.command.playernotfound", target));
            return 1;
        }
        switch (option) {
            case "set": {
                boolean result = data.setBalance(player, value);
                if (result) {
                    context.getSource().sendSuccess(
                            new TranslationTextComponent("message.command.eco.set.success", target, symbol+ value), true);
                    return 0;
                }
                context.getSource().sendFailure(new TranslationTextComponent("message.command.eco.set.failure"));
                return 1;
            }
            case "give": {
                boolean result = data.changeBalance(player, value);
                if (result) {
                    context.getSource().sendSuccess(
                            new TranslationTextComponent("message.command.eco.give.success", symbol+ value, target), true);
                    return 0;
                }
                context.getSource().sendFailure(new TranslationTextComponent("message.command.eco.change.failure"));
                return 1;
            }
            case "remove": {
                boolean result = data.changeBalance(player, -value);
                if (result) {
                    context.getSource().sendSuccess(
                            new TranslationTextComponent("message.command.eco.remove.success", symbol+ value, target), true);
                    return 0;
                }
                context.getSource().sendFailure(new TranslationTextComponent("message.command.eco.change.failure"));
                return 1;
            }
            default:}
        return 0;
    }

    private static int balance(CommandContext<CommandSource> context) {
        AccountManager data = AccountManager.get(context.getSource().getServer().overworld());
        MinecraftServer server = context.getSource().getServer();
        String target = StringArgumentType.getString(context, "player");
        String symbol = SEConfig.CURRENCY_SYMBOL.get();
        UUID player = server.getProfileCache().get(target).getId();
        if (player == null) {
            context.getSource().sendFailure(new TranslationTextComponent("message.command.playernotfound", target));
            return 1;
        }
        double balP = data.getBalance(player);
        context.getSource().sendSuccess(new StringTextComponent(symbol+ balP), true);
        return 0;
    }


    private static int transfer(CommandContext<CommandSource> context) {
        AccountManager data = AccountManager.get(context.getSource().getServer().overworld());
        MinecraftServer server = context.getSource().getServer();
        String from = StringArgumentType.getString(context, "from");
        String to = StringArgumentType.getString(context, "to");
        String symbol = SEConfig.CURRENCY_SYMBOL.get();
        double value = DoubleArgumentType.getDouble(context, "amount");
        UUID fromplayer = server.getProfileCache().get(from).getId();
        if (fromplayer == null) {
            context.getSource().sendFailure(new TranslationTextComponent("message.command.playernotfound", from));
            return 1;
        }
        UUID toplayer = server.getProfileCache().get(to).getId();
        if (toplayer == null) {
            context.getSource().sendFailure(new TranslationTextComponent("message.command.playernotfound", to));
            return 1;
        }
        boolean result = data.transferBalance( fromplayer,  toplayer, value);
        if (result) {
            context.getSource().sendSuccess(
                    new TranslationTextComponent("message.command.pay.success", symbol+ value, to), true);
            return 0;
        }
        context.getSource().sendFailure(new TranslationTextComponent("message.command.pay.failure"));
        return 1;
    }
}
