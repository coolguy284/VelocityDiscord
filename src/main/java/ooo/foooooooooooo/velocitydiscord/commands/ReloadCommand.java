package ooo.foooooooooooo.velocitydiscord.commands;

import com.mojang.brigadier.Command;
import com.velocitypowered.api.command.BrigadierCommand;
import ooo.foooooooooooo.velocitydiscord.VelocityDiscord;

import java.text.MessageFormat;

public final class ReloadCommand {
  public static BrigadierCommand create() {
    var node = BrigadierCommand.literalArgumentBuilder("discord")
      .then(BrigadierCommand.literalArgumentBuilder("reload")
        .requires(source -> source.hasPermission("discord.reload"))
        .executes(source -> {
          String error;

          try {
            error = VelocityDiscord.getInstance().reloadConfig();
          } catch (Exception e) {
            error = e.getMessage();
          }

          if (error == null) {
            source.getSource().sendPlainMessage("Config reloaded");
            return Command.SINGLE_SUCCESS;
          } else {
            source.getSource().sendPlainMessage(MessageFormat.format("Error reloading config:\n{0}\n\nFix the error and reload again", error));
            return 0;
          }
        }))
      .build();

    return new BrigadierCommand(node);
  }
}
