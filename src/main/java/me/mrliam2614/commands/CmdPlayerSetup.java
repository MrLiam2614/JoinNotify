package me.mrliam2614.commands;

import me.mrliam2614.JoinNotify;
import me.mrliam2614.config.ConfigVariables;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CmdPlayerSetup {
    private final JoinNotify plugin;

    public CmdPlayerSetup(JoinNotify plugin) {
        this.plugin = plugin;
    }


    public void PlayerSetup(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            PlayerSetupC(sender, args);
        } else {
            PlayerSetupP(sender, args);
        }
    }

    public void PlayerSetupC(CommandSender sender, String[] args) {
        ConsoleCommandSender console = (ConsoleCommandSender) sender;
        if (args.length == 1) {
            console.sendMessage("\n&cPlayer Help");
            console.sendMessage("&7Use &a/joinnotify player see [player name] &7to see the player informations");
            console.sendMessage("&7Use &a/joinnotify player lock [player name] [ip] &7to make the ip the unique one for that player (others ip can't join)");
            console.sendMessage("&7you can use &bcurrent &7 in the ip for the current ip &4(only online players)");
            console.sendMessage("&7Use &a/joinnotify player unlock [player name] &7to to unlock the player &aip-lock");
            console.sendMessage("&7Use &a/joinnotify player reset [player name] &7to reset the player informations\n ");
            return;
        }
        //SEE PLAYERS INFO
        //args[1]'s: see, lockip, unlockip, reset
        if (equalsAny(args[1], "see", "lock", "unlock", "reset")) { //Catches the cases where we need to send the usage to them.
            if (args.length < 4 && args[1].equalsIgnoreCase("lockip")) {
                console.sendMessage(ConfigVariables.CmdUsage + "&e/joinnotify player lockip [name] [ip/current]");
                return;
            }
            if (args.length < 3) {
                console.sendMessage(ConfigVariables.CmdUsage + "&e/joinnotify player " + args[1] + " [name]");
                return;
            }
        } else { //Handles invalid args[1]
            console.sendMessage(ConfigVariables.InvSubArg + ": " + args[1] + ".");
            return;
        }

        if (args[1].equalsIgnoreCase("see")) {
            String pName = args[2];
            String IP = plugin.getUserConfig().getConfig().getString("users." + pName + ".IP");
            if (IP == null) {
                return;
            }
            console.sendMessage("\n&a" + pName + " INFO");
            console.sendMessage(ConfigVariables.IpL.replace("{PLAYER}", pName).replace("{IpLocked}", IP));
        }
        //LockIp PLAYERS INFO
        else if (args[1].equalsIgnoreCase("lock")) {
            if (args.length < 4) {
                console.sendMessage(ConfigVariables.CmdUsage + "&e/joinnotify player " + args[1] + " [name] " + " [IP/CURRENT]");
                return;
            }
            String pName = args[2];
            String IP = args[3];

            //IS IP "CURRENT"?
            if (IP.equalsIgnoreCase("current")) {
                Player targPlayer = plugin.getServer().getPlayer(pName); //Fixed a potential NullPointerException problem here

                if (targPlayer != null && targPlayer.isOnline()) {
                    console.sendMessage(ConfigVariables.PFound.replace("{PLAYER}", pName));
                    String CIP = plugin.getServer().getPlayer(pName).getPlayer().getAddress().getHostName();
                    plugin.getUserConfig().getConfig().set("users." + pName + ".IP", CIP);
                    plugin.getUserConfig().saveConfig();
                    console.sendMessage(ConfigVariables.IPSave);
                } else {
                    console.sendMessage(ConfigVariables.PNotFound.replace("{PLAYER}", pName));
                    console.sendMessage(ConfigVariables.ManIP);
                }
            } else //IS IP "NOT CURRENT"?
            {
                plugin.getUserConfig().getConfig().set("users." + pName + ".IP", IP);
                plugin.getUserConfig().saveConfig();
                console.sendMessage(ConfigVariables.IPSave);
            }
        } else if (args[1].equalsIgnoreCase("unlock")) {
            String pName = args[2];
            if (!plugin.getUserConfig().getConfig().contains("users." + pName + ".IP")) {
                console.sendMessage(ConfigVariables.NotLocked);
                return;
            }
            plugin.getUserConfig().getConfig().set("users." + pName + ".IP", null);
            plugin.getUserConfig().saveConfig();
            console.sendMessage(ConfigVariables.IpRemove);
        } else if (args[1].equalsIgnoreCase("reset")) {
            String pName = args[2];
            plugin.getUserConfig().getConfig().set("users." + pName, null);
            plugin.getUserConfig().saveConfig();
            console.sendMessage(ConfigVariables.PReset);
        }
    }

    public void PlayerSetupP(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("joinnotify.player")) {
            player.sendMessage(ConfigVariables.NoPermMSG);
        } else {
            if (args.length == 1) {
                player.sendMessage("\n&cPlayer Help");
                player.sendMessage("&7Use &a/joinnotify player see [player name] &7to see the player informations");
                player.sendMessage("&7Use &a/joinnotify player lock [player name] [ip] &7to make the ip the unique one for that player (others ip can't join)");
                player.sendMessage("&7you can use &bcurrent &7 in the ip for the current ip &4(only online players)");
                player.sendMessage("&7Use &a/joinnotify player unlock [player name] &7to to unlock the player &aip-lock");
                player.sendMessage("&7Use &a/joinnotify player reset [player name] &7to reset the player informations\n ");
                return;
            }
            //SEE PLAYERS INFO
            //args[1]'s: see, lockip, unlockip, reset
            if (equalsAny(args[1], "see", "lock", "unlock", "reset")) { //Catches the cases where we need to send the usage to them.
                if (args.length < 4 && args[1].equalsIgnoreCase("lockip")) {
                    player.sendMessage(ConfigVariables.CmdUsage + "&e/joinnotify player lockip [name] [ip/current]");
                    return;
                }
                if (args.length < 3) {
                    player.sendMessage(ConfigVariables.CmdUsage + "&e/joinnotify player " + args[1] + " [name]");
                    return;
                }
            } else { //Handles invalid args[1]
                player.sendMessage(ConfigVariables.InvSubArg + ": " + args[1] + ".");
                return;
            }

            if (args[1].equalsIgnoreCase("see")) {
                String pName = args[2];
                String IP = plugin.getUserConfig().getConfig().getString("users." + pName + ".IP");
                if (IP == null) {
                    IP = "NO IP";
                }
                player.sendMessage("\n&a" + pName + " INFO");
                player.sendMessage(ConfigVariables.IpL.replace("{PLAYER}", pName).replace("{IpLocked}", IP));
            }
            //LockIp PLAYERS INFO
            else if (args[1].equalsIgnoreCase("lock")) {
                if (args.length < 4) {
                    sender.sendMessage(ConfigVariables.CmdUsage + "&e/joinnotify player " + args[1] + " [name] " + " [IP/CURRENT]");
                    return;
                }
                String pName = args[2];
                String IP = args[3];

                //IS IP "CURRENT"?
                if (IP.equalsIgnoreCase("current")) {
                    Player targPlayer = plugin.getServer().getPlayer(pName); //Fixed a potential NullPointerException problem here

                    if (targPlayer != null && targPlayer.isOnline()) {
                        player.sendMessage(ConfigVariables.PFound.replace("{PLAYER}", pName));
                        String CIP = plugin.getServer().getPlayer(pName).getPlayer().getAddress().getAddress().toString().replaceAll("/", "").substring(0).split(":")[0];
                        plugin.getUserConfig().getConfig().set("users." + pName + ".IP", CIP);
                        plugin.getUserConfig().saveConfig();
                        player.sendMessage(ConfigVariables.IPSave);
                    } else {
                        player.sendMessage(ConfigVariables.PNotFound.replace("{PLAYER}", pName));
                        player.sendMessage(ConfigVariables.ManIP);
                    }
                } else //IS IP "NOT CURRENT"?
                {
                    plugin.getUserConfig().getConfig().set("users." + pName + ".IP", IP);
                    plugin.getUserConfig().saveConfig();
                    player.sendMessage(ConfigVariables.IPSave);
                }
            } else if (args[1].equalsIgnoreCase("unlock")) {
                String pName = args[2];
                if (!plugin.getUserConfig().getConfig().contains("users." + pName + ".IP")) {
                    player.sendMessage(ConfigVariables.NotLocked);
                    return;
                }
                plugin.getUserConfig().getConfig().set("users." + pName + ".IP", null);
                plugin.getUserConfig().saveConfig();
                player.sendMessage(ConfigVariables.IpRemove);
            } else if (args[1].equalsIgnoreCase("reset")) {
                String pName = args[2];
                plugin.getUserConfig().getConfig().set("users." + pName, null);
                plugin.getUserConfig().saveConfig();
                player.sendMessage(ConfigVariables.PReset);
            }
        }
    }


    private boolean equalsAny(String baseComp, String... arr) {
        for (String comp : arr) {
            if (comp.equalsIgnoreCase(baseComp)) return true;
        }
        return false;
    }
}
