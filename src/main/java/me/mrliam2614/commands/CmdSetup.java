package me.mrliam2614.commands;

import me.mrliam2614.JoinNotify;
import me.mrliam2614.config.ConfigVariables;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CmdSetup {
    private final JoinNotify plugin;

    public CmdSetup(JoinNotify plugin) {
        this.plugin = plugin;
    }

    public void Setup(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            SetupC(sender, args);
        } else {
            SetupP(sender, args);
        }
    }


    public void SetupC(CommandSender sender, String[] args) {
        ConsoleCommandSender console = (ConsoleCommandSender) sender;

        if (args.length == 1) {
            console.sendMessage("\n&cSetup for &aJoinNotify");
            console.sendMessage("&cJoin - Leave - Firework - Spawn\n ");
            return;
        }
        //args.length > 1
        //args[1]'s: join, leave, firework, spawn
        if (equalsAny(args[1], "join", "leave", "firework", "spawn")) { //Handles usage cases for args[1]

        } else { //Handles invalid args[1]
            console.sendMessage(ConfigVariables.InvSubArg + " " + args[1] + ".");
            return;
        }

        if (args[1].equalsIgnoreCase("join")) {
            if (args.length == 2) {
                console.sendMessage("\n&cSetup for Join message");
                console.sendMessage("&cSet &benable &cto &a[True / False]");
                console.sendMessage("&cSet &bmessage");
                console.sendMessage("&cSet &btitle\n ");
            } else if (args[2].equalsIgnoreCase("title")) {
                if (args.length == 3) {
                    console.sendMessage("\n&cSetup for &atitle &con screen");
                    console.sendMessage("&cSet &benabled &cto &a[True / False]");
                    console.sendMessage("&cSet &btitle");
                    console.sendMessage("&cSet &bsubtitle");
                    console.sendMessage("&cSet &bFadeIN");
                    console.sendMessage("&cSet &bStay");
                    console.sendMessage("&cSet &bFadeOUT\n");

                } else if (args[3].equalsIgnoreCase("enabled")) {
                    if (args.length == 4) {
                        console.sendMessage("\n&cSetup for title on screen &benable");
                        console.sendMessage("&cSet &benable &cto &a[True / False]\n ");
                    } else if (args[4].equalsIgnoreCase("true")) {
                        plugin.getConfig().set("join.title.enabled", true);
                        plugin.saveConfig();
                        console.sendMessage(ConfigVariables.Enabled.replace("{OPTION}", "&6Title"));
                    } else if (args[4].equalsIgnoreCase("false")) {
                        plugin.getConfig().set("join.title.enabled", false);
                        plugin.saveConfig();
                        console.sendMessage(ConfigVariables.Disabled.replace("{OPTION}", "Title"));
                    }

                } else if (args[3].equalsIgnoreCase("title")) {
                    if (args.length >= 5) {
                        StringBuilder message = new StringBuilder();
                        for (int i = 4; i < args.length; i++) {
                            message.append(args[i]).append(" ");
                        }
                        String titlem = message.toString();
                        this.plugin.getConfig().set("join.title.title", titlem);
                        plugin.saveConfig();
                        plugin.reloadConfig();
                        console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Title message"));
                    } else {
                        console.sendMessage("\n&cSetup for &bsubtitle message");
                        console.sendMessage("&cSet &bsubtitle message\n ");
                    }
                } else if (args[3].equalsIgnoreCase("subtitle")) {
                    if (args.length >= 5) {
                        StringBuilder message = new StringBuilder();
                        for (int i = 4; i < args.length; i++) {
                            message.append(args[i]).append(" ");
                        }
                        String subtitlem = message.toString();
                        this.plugin.getConfig().set("join.title.subtitle", subtitlem);
                        plugin.saveConfig();
                        plugin.reloadConfig();
                        console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Subtitle message"));
                    } else {
                        console.sendMessage("\n&cSetup for title message");
                        console.sendMessage("&cSet &bmessage\n ");
                    }
                } else if (args[3].equalsIgnoreCase("fadein")) {
                    if (args.length == 4) {
                        console.sendMessage("\n&cSetup for title to &bfadeIn");
                        console.sendMessage("&cSet &bfade-in &ctime in &bseconds\n ");
                    } else if (args.length > 5) {
                        console.sendMessage("\n&cSetup for title to &bfadeIn");
                        console.sendMessage("&cSet &bfade-in &ctime in &bseconds\n ");
                    } else {
                        if (isInt(args[4])) {
                            int fadein = Integer.parseInt(args[4]);
                            this.plugin.getConfig().set("join.title.fadeIn", fadein);
                            this.plugin.saveConfig();
                            plugin.reloadConfig();
                        } else {
                            console.sendMessage(ConfigVariables.NotNumb);
                        }
                    }
                } else if (args[3].equalsIgnoreCase("stay")) {
                    if (args.length == 4) {
                        console.sendMessage("\n&cSetup for title to &bstay");
                        console.sendMessage("&cSet &bstay &ctime in &bseconds\n ");
                    } else if (args.length > 5) {
                        console.sendMessage("\n&cSetup for title to &bstay");
                        console.sendMessage("&cSet &bstay &ctime in &bseconds\n ");
                    } else {
                        if (isInt(args[4])) {
                            int stay = Integer.parseInt(args[4]);
                            this.plugin.getConfig().set("join.title.stay", stay);
                            this.plugin.saveConfig();
                            plugin.reloadConfig();
                        } else {
                            console.sendMessage(ConfigVariables.NotNumb);
                        }
                    }
                } else if (args[3].equalsIgnoreCase("fadeout")) {
                    if (args.length == 4) {
                        console.sendMessage("\n&cSetup for title to &bfadeOut");
                        console.sendMessage("&cSet &bfade-out &ctime in &bseconds\n ");
                    } else if (args.length > 5) {
                        console.sendMessage("\n&cSetup for title to &bfadeOut");
                        console.sendMessage("&cSet &bfade-out &ctime in &bseconds\n ");
                    } else {
                        if (isInt(args[4])) {
                            int fadeout = Integer.parseInt(args[4]);
                            this.plugin.getConfig().set("join.title.fadeOut", fadeout);
                            this.plugin.saveConfig();
                            plugin.reloadConfig();
                        } else {
                            console.sendMessage(ConfigVariables.NotNumb);
                        }
                    }
                }
            } else if (args[2].equalsIgnoreCase("enable")) {
                if (args.length == 3) {
                    console.sendMessage("\n&cSetup for Join message &benable");
                    console.sendMessage("&cSet &benable &cto &a[True / False]\n ");
                } else if (args[3].equalsIgnoreCase("true")) {
                    ConfigVariables.joinEnabled = true;
                    plugin.getConfig().set("join.enabled", true);
                    console.sendMessage(ConfigVariables.Enabled.replace("{OPTION}", "Join message"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[3].equalsIgnoreCase("false")) {
                    ConfigVariables.joinEnabled = false;
                    plugin.getConfig().set("join.enabled", false);
                    console.sendMessage(ConfigVariables.Disabled.replace("{OPTION}", "Join message"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else {
                    console.sendMessage("\n&cSetup for Join message &benable");
                    console.sendMessage("&cSet &benable &cto &a[True / False]\n ");
                }
            } else if (args[2].equalsIgnoreCase("message")) {
                if (args.length >= 4) {
                    StringBuilder message = new StringBuilder();
                    for (int i = 3; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }
                    String joinm = message.toString();
                    this.plugin.getConfig().set("join.message", joinm);
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Join message"));
                } else {
                    console.sendMessage(ConfigVariables.Empty.replace("{OPTION}", "Join message"));
                }
            } else {
                console.sendMessage(ConfigVariables.InvSubArg + args[2] + ".");
            }
        } else if (args[1].equalsIgnoreCase("leave")) {
            if (args.length == 2) {
                console.sendMessage("\n&cSetup for Leave message");
                console.sendMessage("&cSet &benable &cto &a[True / False]");
                console.sendMessage("&cSet &bmessage\n ");
            } else if (args[2].equalsIgnoreCase("enable")) {
                if (args.length == 3) {
                    console.sendMessage("\n&cSetup for Leave message &benable");
                    console.sendMessage("&cSet &benable &cto &a[True / False]\n ");
                } else if (args[3].equalsIgnoreCase("true")) {
                    ConfigVariables.leaveEnabled = true;
                    plugin.getConfig().set("leave.enabled", true);
                    console.sendMessage(ConfigVariables.Enabled.replace("{OPTION}", "Leave message"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[3].equalsIgnoreCase("false")) {
                    ConfigVariables.leaveEnabled = false;
                    plugin.getConfig().set("leave.enabled", false);
                    console.sendMessage(ConfigVariables.Disabled.replace("{OPTION}", "Leave message"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else {
                    console.sendMessage("\n&cSetup for leave message &benable");
                    console.sendMessage("&cSet &benable &cto &a[True / False]\n ");
                }
            } else if (args[2].equalsIgnoreCase("message")) {
                if (args.length >= 4) {
                    StringBuilder message = new StringBuilder();
                    for (int i = 3; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }
                    String leavem = message.toString();
                    this.plugin.getConfig().set("leave.message", leavem);
                    plugin.saveConfig();
                    plugin.reloadConfig();
                    console.sendMessage("\n&7Updated &aLeave message &7to " + leavem.replaceAll("&", "&") + "\n ");
                } else {
                    console.sendMessage(ConfigVariables.Empty);
                }
            }
        } else if (args[1].equalsIgnoreCase("firework")) {
            if (args.length == 2) {
                console.sendMessage("\n&cSetup for firework");
                console.sendMessage("&cSet &benable &cto &a[True / False]");
                console.sendMessage("&cSet &btype &cto &a[BALL / BALL_LARGE / BURST / CREEPER / STAR]");
                console.sendMessage("&cSet &bpower &cto &a[1,2,3]");
                console.sendMessage("&cSet &bdelay &cto &a[time in seconds]\n ");
            } else if (args[2].equalsIgnoreCase("enable")) {
                if (args.length == 3) {
                    console.sendMessage("\n&cSetup for firework &benable");
                    console.sendMessage("&cSet &benable &cto &a[True / False]\n ");
                } else if (args[3].equalsIgnoreCase("true")) {
                    ConfigVariables.fireworkEnabled = true;
                    plugin.getConfig().set("firework.enabled", true);
                    console.sendMessage(ConfigVariables.Enabled.replace("{OPTION}", "Firework"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[3].equalsIgnoreCase("false")) {
                    ConfigVariables.fireworkEnabled = false;
                    plugin.getConfig().set("firework.enabled", false);
                    console.sendMessage(ConfigVariables.Disabled.replace("{OPTION}", "Firework"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else {
                    console.sendMessage("\n&cSetup for firework");
                    console.sendMessage("&cSet &benable &cto &a[True / False]\n ");
                }
            } else if (args[2].equalsIgnoreCase("type")) {
                if (args.length == 3) {
                    console.sendMessage("\n&cSetup for firework &btype");
                    console.sendMessage("&cSet &btype &cto &a[BALL / BALL_LARGE / BURST / CREEPER / STAR]\n ");
                } else if (args[3].equalsIgnoreCase("BALL")) {
                    plugin.getConfig().set("firework.type", "BALL");
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[3].equalsIgnoreCase("BALL_LARGE")) {
                    plugin.getConfig().set("firework.type", "BALL_LARGE");
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[3].equalsIgnoreCase("BURST")) {
                    plugin.getConfig().set("firework.type", "BURST");
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[3].equalsIgnoreCase("CREEPER")) {
                    plugin.getConfig().set("firework.type", "CREEPER");
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[3].equalsIgnoreCase("STAR")) {
                    plugin.getConfig().set("firework.type", "STAR");
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else {
                    console.sendMessage("\n&cSetup for firework &btype");
                    console.sendMessage("&cSet &btype &cto &a[BALL / BALL_LARGE / BURST / CREEPER / STAR]\n ");
                }
            } else if (args[2].equalsIgnoreCase("power")) {
                if (args.length == 3) {
                    console.sendMessage("\n&cSetup for firework &bpower");
                    console.sendMessage("&cSet &bpower &cto &a[1,2,3]\n ");
                } else if (args[3].equalsIgnoreCase("1")) {
                    plugin.getConfig().set("firework.power", 1);
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Firework Power"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[3].equalsIgnoreCase("2")) {
                    plugin.getConfig().set("firework.power", 2);
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Firework Power"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[3].equalsIgnoreCase("3")) {
                    plugin.getConfig().set("firework.power", 3);
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Firework Power"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else {
                    console.sendMessage("\n&cSetup for firework &bpower");
                    console.sendMessage("&cSet &bpower &cto &a[1,2,3]\n ");
                }
            } else if (args[2].equalsIgnoreCase("delay")) {
                if (args.length == 3) {
                    console.sendMessage("\n&cSetup for firework &bdelay");
                    console.sendMessage("&cSet &bdelay &cto &a[time in seconds]\n ");
                }
                if (args.length > 4) {
                    console.sendMessage("\n&cSetup for firework &bdelay");
                    console.sendMessage("&cSet &bdelay &cto &a[time in seconds]\n ");
                }
                if (args.length == 4) {
                    plugin.getConfig().set("firework.delay", args[3]);
                    console.sendMessage(ConfigVariables.Updated.replace("{OPTION}", "Firework delay"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                }
            }
        } else if (args[1].equalsIgnoreCase("spawn")) {
            if (args.length == 2) {
                console.sendMessage("\n&cSetup for spawn");
                console.sendMessage("&cSet &a[True / False] &cfor auto teleport to spawn on player join\n ");
                return;
            }
            if (args[2].equalsIgnoreCase("true")) {
                ConfigVariables.spawnEnabled = true;
                plugin.getConfig().set("spawn.enabled", true);
                console.sendMessage(ConfigVariables.Enabled.replace("{OPTION}", "Spawn On Join"));
                plugin.saveConfig();
                plugin.reloadConfig();
            } else if (args[2].equalsIgnoreCase("false")) {
                ConfigVariables.spawnEnabled = false;
                plugin.getConfig().set("spawn.enabled", false);
                console.sendMessage(ConfigVariables.Disabled.replace("{OPTION}", "Spawn On Join"));
                plugin.saveConfig();
                plugin.reloadConfig();
            } else {
                console.sendMessage("\n&cSetup for spawn");
                console.sendMessage("&cSet &a[True / False] &cfor auto teleport to spawn on player join\n ");
            }
        } else {
            console.sendMessage("\n&cSetup for &aJoinNotify");
            console.sendMessage("&cJoin - Leave - Firework\n ");
        }
    }


    public void SetupP(CommandSender sender, String[] args) {
        Player player = (Player) sender;


        if (!player.hasPermission("joinnotify.setup")) {
            plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.NoPermMSG);
        } else {
            if (args.length == 1) {
                plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for &aJoinNotify");
                plugin.getFacilitisAPI().msg.sendMessage(player, "&cJoin - Leave - Firework - Spawn\n ");
                return;
            }
            //args.length > 1
            //args[1]'s: join, leave, firework, spawn
            if (equalsAny(args[1], "join", "leave", "firework", "spawn")) { //Handles usage cases for args[1]

            } else { //Handles invalid args[1]
                plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.InvSubArg + args[1] + ".");
                return;
            }

            if (args[1].equalsIgnoreCase("join")) {
                if (args.length == 2) {
                    plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for Join message");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bmessage");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &btitle\n ");
                } else if (args[2].equalsIgnoreCase("title")) {
                    if (args.length == 3) {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for &atitle &con screen");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benabled &cto &a[True / False]");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &btitle");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bsubtitle");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bFadeIN");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bStay");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bFadeOUT\n");

                    } else if (args[3].equalsIgnoreCase("enabled")) {
                        if (args.length == 4) {
                            plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for title on screen &benable");
                            plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]\n ");
                        } else if (args[4].equalsIgnoreCase("true")) {
                            plugin.getConfig().set("join.title.enabled", true);
                            plugin.saveConfig();
                            plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Enabled.replace("{OPTION}", "Title"));
                        } else if (args[4].equalsIgnoreCase("false")) {
                            plugin.getConfig().set("join.title.enabled", false);
                            plugin.saveConfig();
                            plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Disabled.replace("{OPTION}", "Title"));
                        }

                    } else if (args[3].equalsIgnoreCase("title")) {
                        boolean version = Bukkit.getServer().getClass().getPackage().getName().contains("1.8");
                        if (!version) {
                            if (args.length >= 5) {
                                StringBuilder message = new StringBuilder();
                                for (int i = 4; i < args.length; i++) {
                                    message.append(args[i]).append(" ");
                                }
                                String titlem = message.toString();
                                this.plugin.getConfig().set("join.title.title", titlem);
                                plugin.saveConfig();
                                plugin.reloadConfig();
                                plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Title Title"));

                                player.sendTitle(plugin.getFacilitisAPI().strUtils.colored(titlem), plugin.getFacilitisAPI().strUtils.colored("&aTitleTest"));
                            } else {
                                plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for &bsubtitle message");
                                plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bsubtitle message\n ");
                            }
                        } else {
                            plugin.getFacilitisAPI().msg.sendMessage(player, "&cYou are using a non supported server version for titles!");
                        }
                    } else if (args[3].equalsIgnoreCase("subtitle")) {
                        boolean version = Bukkit.getServer().getClass().getPackage().getName().contains("1.8");
                        if (!version) {
                            if (args.length >= 5) {
                                StringBuilder message = new StringBuilder();
                                for (int i = 4; i < args.length; i++) {
                                    message.append(args[i]).append(" ");
                                }
                                String subtitlem = message.toString();
                                this.plugin.getConfig().set("join.title.subtitle", subtitlem);
                                plugin.saveConfig();
                                plugin.reloadConfig();
                                plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Title Subtitle"));
                                player.sendTitle(plugin.getFacilitisAPI().strUtils.colored("&aSUBTITLE TEST"), plugin.getFacilitisAPI().strUtils.colored(subtitlem));
                            } else {
                                plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for title message");
                                plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bmessage\n ");
                            }
                        } else {
                            plugin.getFacilitisAPI().msg.sendMessage(player, "&cYou are using a non supported server version for titles!");
                        }
                    } else if (args[3].equalsIgnoreCase("fadein")) {
                        if (args.length == 4) {
                            plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for title to &bfadeIn");
                            plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bfade-in &ctime in &bseconds\n ");
                        } else if (args.length > 5) {
                            plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for title to &bfadeIn");
                            plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bfade-in &ctime in &bseconds\n ");
                        } else {
                            if (isInt(args[4])) {
                                int fadein = Integer.parseInt(args[4]);
                                this.plugin.getConfig().set("join.title.fadeIn", fadein);
                                this.plugin.saveConfig();
                                plugin.reloadConfig();
                            } else {
                                plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.NotNumb);
                            }
                        }
                    } else if (args[3].equalsIgnoreCase("stay")) {
                        if (args.length == 4) {
                            plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for title to &bstay");
                            plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bstay &ctime in &bseconds\n ");
                        } else if (args.length > 5) {
                            plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for title to &bstay");
                            plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bstay &ctime in &bseconds\n ");
                        } else {
                            if (isInt(args[4])) {
                                int stay = Integer.parseInt(args[4]);
                                this.plugin.getConfig().set("join.title.stay", stay);
                                this.plugin.saveConfig();
                                plugin.reloadConfig();
                            } else {
                                plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.NotNumb);
                            }
                        }
                    } else if (args[3].equalsIgnoreCase("fadeout")) {
                        if (args.length == 4) {
                            plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for title to &bfadeOut");
                            plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bfade-out &ctime in &bseconds\n ");
                        } else if (args.length > 5) {
                            plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for title to &bfadeOut");
                            plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bfade-out &ctime in &bseconds\n ");
                        } else {
                            if (isInt(args[4])) {
                                int fadeout = Integer.parseInt(args[4]);
                                this.plugin.getConfig().set("join.title.fadeOut", fadeout);
                                this.plugin.saveConfig();
                                plugin.reloadConfig();
                            } else {
                                plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.NotNumb);
                            }
                        }
                    }
                } else if (args[2].equalsIgnoreCase("enable")) {
                    if (args.length == 3) {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for Join message &benable");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]\n ");
                    } else if (args[3].equalsIgnoreCase("true")) {
                        ConfigVariables.joinEnabled = true;
                        plugin.getConfig().set("join.enabled", true);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Enabled.replace("{OPTION}", "Join message"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("false")) {
                        ConfigVariables.joinEnabled = false;
                        plugin.getConfig().set("join.enabled", false);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Disabled.replace("{OPTION}", "Join message"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for Join message &benable");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]\n ");
                    }
                } else if (args[2].equalsIgnoreCase("message")) {
                    if (args.length >= 4) {
                        StringBuilder message = new StringBuilder();
                        for (int i = 3; i < args.length; i++) {
                            message.append(args[i]).append(" ");
                        }
                        String joinm = message.toString();
                        this.plugin.getConfig().set("join.message", joinm);
                        plugin.saveConfig();
                        plugin.reloadConfig();
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Join message"));
                    } else {
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Empty);
                    }
                } else {
                    plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.InvSubArg + args[2]);
                }
            } else if (args[1].equalsIgnoreCase("leave")) {
                if (args.length == 2) {
                    plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for Leave message");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bmessage\n ");
                } else if (args[2].equalsIgnoreCase("enable")) {
                    if (args.length == 3) {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for Leave message &benable");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]\n ");
                    } else if (args[3].equalsIgnoreCase("true")) {
                        ConfigVariables.leaveEnabled = true;
                        plugin.getConfig().set("leave.enabled", true);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Enabled.replace("{OPTION}", "Leave message"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("false")) {
                        ConfigVariables.leaveEnabled = false;
                        plugin.getConfig().set("leave.enabled", false);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Disabled.replace("{OPTION}", "Leave message"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for leave message &benable");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]\n ");
                    }
                } else if (args[2].equalsIgnoreCase("message")) {
                    if (args.length >= 4) {
                        StringBuilder message = new StringBuilder();
                        for (int i = 3; i < args.length; i++) {
                            message.append(args[i]).append(" ");
                        }
                        String leavem = message.toString();
                        this.plugin.getConfig().set("leave.message", leavem);
                        plugin.saveConfig();
                        plugin.reloadConfig();
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Leave message"));
                    } else {
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Empty);
                    }
                }
            } else if (args[1].equalsIgnoreCase("firework")) {
                if (args.length == 2) {
                    plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for firework");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &btype &cto &a[BALL / BALL_LARGE / BURST / CREEPER / STAR]");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bpower &cto &a[1,2,3]");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bdelay &cto &a[time in seconds]\n ");
                } else if (args[2].equalsIgnoreCase("enable")) {
                    if (args.length == 3) {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for firework &benable");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]\n ");
                    } else if (args[3].equalsIgnoreCase("true")) {
                        ConfigVariables.fireworkEnabled = true;
                        plugin.getConfig().set("firework.enabled", true);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Enabled.replace("{OPTION}", "Firework"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("false")) {
                        ConfigVariables.fireworkEnabled = false;
                        plugin.getConfig().set("firework.enabled", false);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Disabled.replace("{OPTION}", "Firework"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for firework");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &benable &cto &a[True / False]\n ");
                    }
                } else if (args[2].equalsIgnoreCase("type")) {
                    if (args.length == 3) {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for firework &btype");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &btype &cto &a[BALL / BALL_LARGE / BURST / CREEPER / STAR]\n ");
                    } else if (args[3].equalsIgnoreCase("BALL")) {
                        plugin.getConfig().set("firework.type", "BALL");
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("BALL_LARGE")) {
                        plugin.getConfig().set("firework.type", "BALL_LARGE");
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("BURST")) {
                        plugin.getConfig().set("firework.type", "BURST");
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("CREEPER")) {
                        plugin.getConfig().set("firework.type", "CREEPER");
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("STAR")) {
                        plugin.getConfig().set("firework.type", "STAR");
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Firework Type"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for firework &btype");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &btype &cto &a[BALL / BALL_LARGE / BURST / CREEPER / STAR]\n ");
                    }
                } else if (args[2].equalsIgnoreCase("power")) {
                    if (args.length == 3) {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for firework &bpower");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bpower &cto &a[1,2,3]\n ");
                    } else if (args[3].equalsIgnoreCase("1")) {
                        plugin.getConfig().set("firework.power", 1);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Firework Power"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("2")) {
                        plugin.getConfig().set("firework.power", 2);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Firework Power"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else if (args[3].equalsIgnoreCase("3")) {
                        plugin.getConfig().set("firework.power", 3);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Firework Power"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    } else {

                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for firework &bpower");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bpower &cto &a[1,2,3]\n ");
                    }
                } else if (args[2].equalsIgnoreCase("delay")) {
                    if (args.length == 3) {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for firework &bdelay");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bdelay &cto &a[time in seconds]\n ");
                    }
                    if (args.length > 4) {
                        plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for firework &bdelay");
                        plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &bdelay &cto &a[time in seconds]\n ");
                    }
                    if (args.length == 4) {
                        plugin.getConfig().set("firework.delay", args[3]);
                        plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Updated.replace("{OPTION}", "Firework Delay"));
                        plugin.saveConfig();
                        plugin.reloadConfig();
                    }
                }
            } else if (args[1].equalsIgnoreCase("spawn")) {
                if (args.length == 2) {
                    plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for spawn");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &a[True / False] &cfor auto teleport to spawn on player join\n ");
                    return;
                }
                if (args[2].equalsIgnoreCase("true")) {
                    ConfigVariables.spawnEnabled = true;
                    plugin.getConfig().set("spawn.enabled", true);
                    plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Enabled.replace("{OPTION}", "Spawn On Join"));
                    plugin.getFacilitisAPI().msg.sendMessage(player, "\n&7Updated &aSpawn teleport &7to &aTrue\n ");
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else if (args[2].equalsIgnoreCase("false")) {
                    ConfigVariables.spawnEnabled = false;
                    plugin.getConfig().set("spawn.enabled", false);
                    plugin.getFacilitisAPI().msg.sendMessage(player, ConfigVariables.Disabled.replace("{OPTION}", "Spawn On Join"));
                    plugin.saveConfig();
                    plugin.reloadConfig();
                } else {
                    plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for spawn");
                    plugin.getFacilitisAPI().msg.sendMessage(player, "&cSet &a[True / False] &cfor auto teleport to spawn on player join\n ");
                }
            } else {
                plugin.getFacilitisAPI().msg.sendMessage(player, "\n&cSetup for &aJoinNotify");
                plugin.getFacilitisAPI().msg.sendMessage(player, "&cJoin - Leave - Firework\n ");
            }
        }
    }


    private boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean equalsAny(String baseComp, String... arr) {
        for (String comp : arr) {
            if (comp.equalsIgnoreCase(baseComp)) return true;
        }
        return false;
    }
}
