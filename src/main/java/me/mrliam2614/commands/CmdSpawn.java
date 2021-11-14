package me.mrliam2614.commands;

import me.mrliam2614.JoinNotify;
import me.mrliam2614.config.ConfigVariables;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSpawn {
    private final JoinNotify plugin;

    public CmdSpawn(JoinNotify plugin) {
        this.plugin = plugin;
    }

    public boolean Spawn(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ConfigVariables.NotPlayer);
            return true;
        }
        if (ConfigVariables.spawnLoc == null) {
            Player player = (Player) sender;
            player.sendMessage(ConfigVariables.NoSpawn);
        } else {
            Player player = (Player) sender;
            player.teleport(ConfigVariables.spawnLoc);
            return false;
        }
        return false;
    }
}
