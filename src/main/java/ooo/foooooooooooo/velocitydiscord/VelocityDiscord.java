package ooo.foooooooooooo.velocitydiscord;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import ooo.foooooooooooo.velocitydiscord.config.Config;
import ooo.foooooooooooo.velocitydiscord.discord.Discord;
import ooo.foooooooooooo.velocitydiscord.yep.YepListener;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(
  id = "discord",
  name = VelocityDiscord.PluginName,
  description = VelocityDiscord.PluginDescription,
  version = VelocityDiscord.PluginVersion,
  url = VelocityDiscord.PluginUrl,
  authors = {"fooooooooooooooo"},
  dependencies = @Dependency(id = "yeplib", optional = true)
)
public class VelocityDiscord {
  public static final String PluginName = "Velocity Discord Bridge";
  public static final String PluginDescription = "Velocity Discord Chat Bridge";
  public static final String PluginVersion = "1.8.2";
  public static final String PluginUrl = "https://github.com/fooooooooooooooo/VelocityDiscord";

  public static final MinecraftChannelIdentifier YepIdentifier = MinecraftChannelIdentifier.create("velocity", "yep");

  public static boolean pluginDisabled = false;

  private static VelocityDiscord instance;

  private final ProxyServer server;

  @Nullable
  private Discord discord = null;

  @Nullable
  private YepListener yep = null;

  @Inject
  public VelocityDiscord(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
    this.server = server;

    logger.info("Loading " + PluginName + " v" + PluginVersion);

    var config = new Config(dataDirectory);
    pluginDisabled = config.isFirstRun();

    if (pluginDisabled) {
      logger.severe("This is the first time you are running this plugin. Please configure it in the config.yml file. Disabling plugin.");
    } else {
      this.discord = new Discord(this.server, logger, config);
      if (server.getPluginManager().isLoaded("yeplib")) {
        this.yep = new YepListener(logger, config);
      }
    }

    instance = this;
  }

  public static Discord getDiscord() {
    return instance.discord;
  }

  @Subscribe
  public void onProxyInitialization(ProxyInitializeEvent event) {
    if (discord != null) {
      register(discord);
    }

    if (yep != null) {
      register(yep);
    }

    this.server.getChannelRegistrar().register(YepIdentifier);
  }

  @Subscribe
  public void onProxyShutdown(ProxyShutdownEvent event) {
    if (discord != null) {
      discord.shutdown();
    }
  }

  private void register(Object x) {
    this.server.getEventManager().register(this, x);
  }
}
