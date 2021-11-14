package me.mrliam2614.commands;

import me.mrliam2614.JoinNotify;
import me.mrliam2614.config.ConfigVariables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor/*, TabCompleter */ {
    private final JoinNotify plugin;
    private final CmdHelp cmdHelp;
    private final CmdSpawn cmdSpawn;
    private final CmdSetSpawn cmdSetSpawn;
    private final CmdPlayerSetup cmdPlayerSetup;
    private final CmdSetup cmdSetup;

    public CommandHandler(JoinNotify plugin) {
        this.plugin = plugin;
        cmdHelp = new CmdHelp(plugin);
        cmdSpawn = new CmdSpawn(plugin);
        cmdSetSpawn = new CmdSetSpawn(plugin);
        cmdPlayerSetup = new CmdPlayerSetup(plugin);
        cmdSetup = new CmdSetup(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Spawn command
        if (command.getName().equalsIgnoreCase("spawn")) {
            if (!sender.hasPermission("joinnotify.spawn")) {
                sender.sendMessage(ConfigVariables.NoPermMSG);
                return false;
            } else {
                cmdSpawn.Spawn(sender, args);
                return true;
            }
        } else if (command.getName().equalsIgnoreCase("setspawn")) {
            if (!sender.hasPermission("joinnotify.setspawn")) {
                sender.sendMessage(ConfigVariables.NoPermMSG);
                return false;
            } else {
                cmdSetSpawn.SetSpawn(sender, args);
                return true;
            }
        }

        //AllJoinNotify Command
        else if (!command.getName().equalsIgnoreCase("JoinNotify"))
            return false; //Checking command.getName() checks if the commandLabel is an alias or the actual plugin.yml command. This means that this if statement takes care of /joinnotify and /jn!

        //Help Command
        if (args.length == 0) {
            if (!sender.hasPermission("joinnotify.help")) {
                sender.sendMessage(ConfigVariables.NoPermMSG);
                return false;
            } else {
                cmdHelp.Help(sender);
                return true;
            }
        }
        //Help
        else if (args[0].equalsIgnoreCase("help")) {
            if (!sender.hasPermission("joinnotify.help")) {
                sender.sendMessage(ConfigVariables.NoPermMSG);
                return false;
            } else {
                cmdHelp.Help(sender);
                return true;
            }
        }
        //Reload
        else if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("joinnotify.reload")) {
                sender.sendMessage(ConfigVariables.NoPermMSG);
                return false;
            } else {
                plugin.reloadConfig();
                sender.sendMessage(ConfigVariables.PlReload);
                return true;
            }
        }
        //SetSpawn
        else if (args[0].equalsIgnoreCase("setspawn")) {
            if (!sender.hasPermission("joinnotify.setspawn")) {
                sender.sendMessage(ConfigVariables.NoPermMSG);
                return false;
            } else {
                cmdSetSpawn.SetSpawn(sender, args);
                return true;
            }
        }
        //Spawn
        else if (args[0].equalsIgnoreCase("spawn")) {
            if (!sender.hasPermission("joinnotify.spawn")) {
                sender.sendMessage(ConfigVariables.NoPermMSG);
                return false;
            } else {
                cmdSpawn.Spawn(sender, args);
                return true;
            }
        }
        //Player setup
        else if (args[0].equalsIgnoreCase("player")) {
            cmdPlayerSetup.PlayerSetup(sender, args);
            return true;
        }
        //Setup
        else if (args[0].equalsIgnoreCase("setup")) {
            cmdSetup.Setup(sender, args);
            return true;
        } else {
            sender.sendMessage(ConfigVariables.InvArg);
            return true;
        }
        //Player Command
    }

    /*@Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }*/
}
