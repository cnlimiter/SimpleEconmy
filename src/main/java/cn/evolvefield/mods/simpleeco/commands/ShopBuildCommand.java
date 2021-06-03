package cn.evolvefield.mods.simpleeco.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class ShopBuildCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(LiteralArgumentBuilder.<CommandSource>literal("shop")
                .then(Commands.literal("builder")
                .executes(ShopBuildCommand::builder)));
    }


    private static int builder(CommandContext<CommandSource> context) throws CommandSyntaxException {
        context.getSource().sendSuccess(new StringTextComponent("WIP"), true);
        return 0;
    }
}
