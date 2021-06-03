package cn.evolvefield.mods.simpleeco.commands;

import cn.evolvefield.mods.simpleeco.core.SEConfig;
import cn.evolvefield.mods.simpleeco.data.AccountManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;

public class MoneyCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("money")
                .requires(src -> src.hasPermission(1))
                .executes(context -> balance(context.getSource())));
    }

    private static int balance(CommandSource source) throws CommandSyntaxException{
        AccountManager data = AccountManager.get(source.getServer().overworld());
        String symbol = SEConfig.CURRENCY_SYMBOL.get();
        double balP = data.getBalance(source.getPlayerOrException().getUUID());
        source.sendSuccess(new StringTextComponent(symbol+ balP), true);


        return 0;
    }
}
