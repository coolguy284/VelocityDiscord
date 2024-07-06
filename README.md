# Velocity Discord

Chat from all servers gets bridged with a discord channel

## Features

- Configurable
- Webhooks or normal chat for messages
- Player count in bot status
- List command
- Templating syntax for all messages
- Death and Advancement messages shown

> **Note**
> This requires a [companion Velocity plugin](https://github.com/unilock/YepLib)
> and [companion backend mod/plugin](https://github.com/unilock/YepTwo) for advancement/death messages

## Installation

1. Create a bot application [here](https://discordapp.com/developers/applications/)
    - Go to the `Bot` tab and click `Add bot`
2. Enable the `SERVER MEMBERS INTENT` and `MESSAGE CONTENT INTENT` under `Privileged Gateway Intents`
3. Copy the bot's token, you might have to click `Reset Token` first
4. Install the plugin on your server, start the server once, then stop the server again
5. Open the plugin config file at `plugins/discord/config.toml`
6. Under `[discord]`, paste your token in place of `TOKEN`
7. Under `[discord]`, paste the channel id you want to use
    - To get a channel id, you have to enable developer mode in Discord
    - Open Discord settings, go to `Advanced`, then turn on `Developer Mode`
    - Now right-click the channel you want to use and click `Copy ID`
8. Set any additional config options you want
9. Start the server and check if it works

### For Webhooks

1. Create a webhook in the channel you want to use
    - Right-click the channel, click `Edit Channel`, go to `Integrations`, click `Create Webhook`
    - Copy the webhook URL
2. Paste the webhook URL under `[discord.webhook]` in the config file

### For advancements/deaths

1. Install the [YepLib](https://github.com/unilock/YepLib) plugin alongside this plugin
2. Install the [YepTwo](https://github.com/unilock/YepTwo) mod/plugin on each of your backend servers that you want to
   receive advancements/deaths from

## Configuration

Default config generated on startup:

```toml
# Don't change this
config_version = "1.9"

# Comma separated list of server names to exclude from the bridge (defined under [servers] inside your velocity.toml)
# e.g., exclude_servers = ["lobby", "survival"]
exclude_servers = []
excluded_servers_receive_messages = false

[discord]
# Bot token from https://discordapp.com/developers/applications/
token = "TOKEN"
# Channel ID to send Minecraft chat messages to
channel = "000000000000000000"

# Show messages from bots in Minecraft chat
show_bot_messages = false
# Show clickable links for attachments in Minecraft chat
show_attachments_ingame = true

# Show a text as playing activity of the bot
show_activity = true
# Activity text of the bot to show in Discord
# Placeholder {amount} is available
activity_text = "with {amount} players online"

# Enable mentioning Discord users from Minecraft chat
enable_mentions = true
# Enable @everyone and @here pings from Minecraft chat
enable_everyone_and_here = false

[discord.webhook]
# Full webhook URL to send more fancy Minecraft chat messages to
webhook_url = ""
# Full URL of an avatar service to get the player's avatar from
# Placeholders {uuid} and {username} are available
avatar_url = "https://visage.surgeplay.com/face/96/{uuid}"

# The format of the webhook's username
# Placeholders {username} and {server} are available
webhook_username = "{username}"

# Minecraft > Discord message formats
# Uses the same formatting as the Discord client (a subset of markdown)
# Messages can be disabled with empty string ("") or false
#
# x_message_type can be one of the following:
# "text"    - Normal text only message with the associated x_message format
# "embed"   - Discord embed with the associated x_message format as the description field
# Default for all is "text"
#
# x_message_embed_color is the color of the embed, in #RRGGBB format
[discord.chat]

# Placeholders {username}, {server}, and {message} are available
# Can be disabled
message = "{username}: {message}"

# for user messages, the following types can be used
# "text"    - Normal text only message with the above
#
# "webhook" - Use a Discord webhook to have the bot use the player's username and avatar when sending messages
#             Requires a webhook URL to be set below
#             Ignores the above message format, and just sends the message as the content of the webhook
#
# "embed"   - Discord embed with the above format as the description field
message_type = "text"
# Can be disabled
message_embed_color = ""

# Placeholders {username} and {server} are available
# Can be disabled
join_message = "**{username} joined the game**"
join_message_type = "text"
# Can be disabled
join_message_embed_color = "#40bf4f"

leave_message = "**{username} left the game**"
leave_message_type = "text"
# Can be disabled
leave_message_embed_color = "#bf4040"

# Possible different format for timeouts or other terminating connections
# Placeholder {username} is available
# Can be disabled
disconnect_message = "**{username} disconnected**"
disconnect_message_type = "text"
# Can be disabled
disconnect_message_embed_color = "#bf4040"

# Placeholders {username}, {current}, and {previous} are available
# Can be disabled
server_switch_message = "**{username} moved to {current} from {previous}**"
server_switch_message_type = "text"
# Can be disabled
server_switch_message_embed_color = "#40bf4f"

# Placeholders {username} and {death_message} are available
# death_message includes the username just as it is shown ingame
# Can be disabled
death_message = "**{death_message}**"
death_message_type = "text"
# Can be disabled
death_message_embed_color = "#bf4040"

# Placeholders {username}, {advancement_title}, and {advancement_description} are available
# Can be disabled
advancement_message = "**{username} has made the advancement __{advancement_title}__**\n_{advancement_description}_"
advancement_message_type = "text"
# Can be disabled
advancement_message_embed_color = "#40bf4f"

[discord.commands.list]
enabled = true

# Ephemeral messages are only visible to the user who sent the command
ephemeral = true

# Placeholders {server_name}, {online_players} and {max_players} are available
server_format = "[{server_name} {online_players}/{max_players}]"

# Placeholder {username} is available
player_format = "- {username}"

# Can be disabled
no_players = "No players online"

# Can be disabled
server_offline = "Server offline"
codeblock_lang = "asciidoc"

# Discord > Minecraft message formats
# Uses XML-like formatting with https://docs.advntr.dev/minimessage/format.html
[minecraft]
# Placeholder {discord} is available
discord_chunk = "<dark_gray>[<{discord_color}>Discord<dark_gray>]<reset>"

# Placeholders {role_color}, {display_name}, {username} and {nickname} are available
# <insert> tag allows you to shift right-click the username to insert @{username} in the chat
username_chunk = "<{role_color}><insert:@{username}><hover:show_text:{display_name}>{nickname}</hover></insert><reset>"

# Placeholders {discord_chunk}, {username_chunk}, {attachments} and {message} are available
message = "{discord_chunk} {username_chunk}<dark_gray>: <reset>{message} {attachments}"

# Placeholders {url} and {attachment_color} are available
attachments = "<dark_gray><click:open_url:{url}>[<{attachment_color}>Attachment<dark_gray>]</click><reset>"

# Colors for the <{discord_color}> and <{attachment_color}> tags
discord_color = "#7289da"
attachment_color = "#4abdff"
```
