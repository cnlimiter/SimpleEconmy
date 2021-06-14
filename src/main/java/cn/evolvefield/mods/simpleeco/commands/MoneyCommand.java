package cn.evolvefield.mods.simpleeco.commands;

import cn.evolvefield.mods.simpleeco.main.SEConfig;
import cn.evolvefield.mods.simpleeco.core.money.AccountManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

public class MoneyCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("money")
                .requires(src -> src.hasPermissionLevel(0))
                .executes(context -> balance(context.getSource())));
    }

    private static int balance(CommandSource source) throws CommandSyntaxException{
        AccountManager data = AccountManager.get(source.getServer().func_241755_D_());
        String symbol = SEConfig.CURRENCY_SYMBOL.get();
        double balP = data.getBalance(source.asPlayer().getUniqueID());
        source.sendFeedback(new StringTextComponent(symbol+ balP), true);


        return 0;
    }
}
