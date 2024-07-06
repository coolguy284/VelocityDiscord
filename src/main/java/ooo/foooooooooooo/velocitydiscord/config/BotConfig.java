package ooo.foooooooooooo.velocitydiscord.config;

import com.electronwill.nightconfig.core.Config;

public class BotConfig extends BaseConfig {
  public BotConfig(Config config) {
    loadConfig(config);
  }

  private static final String DefaultToken = "TOKEN";
  private static final String DefaultChannelId = "000000000000000000";
  private static final String DefaultWebhookUrl = "";
  private static final String DefaultAvatarUrl = "https://crafatar.com/avatars/{uuid}?overlay";

  // bot
  public String DISCORD_TOKEN = DefaultToken;
  public String CHANNEL_ID = DefaultChannelId;

  // webhooks
  public String WEBHOOK_URL = DefaultWebhookUrl;
  public String WEBHOOK_AVATAR_URL = DefaultAvatarUrl;
  public String WEBHOOK_USERNAME = "{username}";

  // pings
  public Boolean ENABLE_MENTIONS = true;
  public Boolean ENABLE_EVERYONE_AND_HERE = false;

  // bot activity
  public Boolean SHOW_ACTIVITY = true;
  public String ACTIVITY_FORMAT = "with {amount} players online";


  @Override
  protected void loadConfig(com.electronwill.nightconfig.core.Config config) {
    // bot
    DISCORD_TOKEN = get(config, "discord.token", DISCORD_TOKEN);
    CHANNEL_ID = get(config, "discord.channel", CHANNEL_ID);

    // webhooks
    WEBHOOK_URL = get(config, "discord.webhook.webhook_url", WEBHOOK_URL);
    WEBHOOK_AVATAR_URL = get(config, "discord.webhook.avatar_url", WEBHOOK_AVATAR_URL);
    WEBHOOK_USERNAME = get(config, "discord.webhook.webhook_username", WEBHOOK_USERNAME);

    // pings
    ENABLE_MENTIONS = get(config, "discord.enable_mentions", ENABLE_MENTIONS);
    ENABLE_EVERYONE_AND_HERE = get(config, "discord.enable_everyone_and_here", ENABLE_EVERYONE_AND_HERE);

    // bot activity
    SHOW_ACTIVITY = get(config, "discord.show_activity", SHOW_ACTIVITY);
    ACTIVITY_FORMAT = get(config, "discord.activity_text", ACTIVITY_FORMAT);
  }

  public boolean isDefaultValues() {
    return DISCORD_TOKEN.equals(DefaultToken) || CHANNEL_ID.equals(DefaultChannelId);
  }
}
