package me.mrliam2614.commands;

import me.mrliam2614.JoinNotify;
import me.mrliam2614.config.ConfigVariables;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetSpawn {
    private final JoinNotify plugin;

    public CmdSetSpawn(JoinNotify plugin) {
        this.plugin = plugin;
    }


    public boolean SetSpawn(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ConfigVariables.NotPlayer);
            return true;
        }
        Player player = (Player) sender;

        plugin.getConfig().set("spawn.location", player.getLocation());
        plugin.saveConfig();
        plugin.reloadConfig();
        player.sendMessage(ConfigVariables.SpawnSave);
        if (ConfigVariables.spawnEnabled) {
            return false;
        }
        player.sendMessage("\n�cRemember to enable the spawn teleport on join with �a/JoinNotify setup spawn true\n ");
        return false;
    }
}
