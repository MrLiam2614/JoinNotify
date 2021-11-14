package me.mrliam2614.commands;

import me.mrliam2614.JoinNotify;
import org.bukkit.command.CommandSender;

public class CmdHelp {
    private final JoinNotify plugin;

    public CmdHelp(JoinNotify plugin) {
        this.plugin = plugin;
    }

    public void Help(CommandSender sender) {
        sender.sendMessage(plugin.getFacilitisAPI().strUtils.colored("\n&7JoinNotify Help"));
        sender.sendMessage(plugin.getFacilitisAPI().strUtils.colored("&7Alias for &a/joinnotify &7is &c/jn"));
        sender.sendMessage(plugin.getFacilitisAPI().strUtils.colored("&a/JoinNotify help &7- &6see commands list"));
        sender.sendMessage(plugin.getFacilitisAPI().strUtils.colored("&a/JoinNotify reload &7- &6reload plugin's config"));
        sender.sendMessage(plugin.getFacilitisAPI().strUtils.colored("&a/JoinNotify setspawn &7- &6set the server's spawn"));
        sender.sendMessage(plugin.getFacilitisAPI().strUtils.colored("&a/JoinNotify spawn &7- &6teleport to server's spawn"));
        sender.sendMessage(plugin.getFacilitisAPI().strUtils.colored("&a/JoinNotify setup [Join - Leave - Firework - Spawn] &7- &6Setup your config file from the game!"));
        sender.sendMessage(plugin.getFacilitisAPI().strUtils.colored("&a/JoinNotify player [see - lock - unlock - reset] [player] &7- &6Setup your players info and restrictions!\n "));
    }
}
