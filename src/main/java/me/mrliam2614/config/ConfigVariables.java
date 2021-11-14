package me.mrliam2614.config;

import me.mrliam2614.JoinNotify;
import org.bukkit.Location;

public class ConfigVariables {
    private final JoinNotify plugin;

    public static String lang, NoPermMSG, joinM, kickMSG, titleTitleMSG, titleSubtitleMSG, fireworkType, quitM, InvSubArg, InvArg, PlReload,
            CmdUsage, IpL, PFound, PNotFound, NotPlayer, IPSave, ManIP, NotLocked, UnLPlayer, PReset, NotNumb, Empty, Enabled, Disabled, IpRemove,
            SpawnSave, Updated, NoSpawn;
    public static Location spawnLoc;
    public static boolean joinEnabled, spawnEnabled, fireworkEnabled, titleEnabled, leaveEnabled, autoUpd;
    public static int fireworkDelay, fireworkPower, titleFadeIN, titleStay, titleFadeOUT;

    public ConfigVariables(JoinNotify plugin) {
        this.plugin = plugin;

        storeValues();
    }

    private void storeValues() {
        plugin.reloadConfig();

        lang = plugin.getConfig().getString("lang");

        //BOOLEAN
        joinEnabled = plugin.getConfig().getBoolean("join.enabled", false);
        spawnEnabled = plugin.getConfig().getBoolean("spawn.enabled", false);
        fireworkEnabled = plugin.getConfig().getBoolean("firework.enabled", false);
        leaveEnabled = plugin.getConfig().getBoolean("leave.enabled", false);
        titleEnabled = plugin.getConfig().getBoolean("join.title.enabled", false);

        //FIREWORK
        fireworkDelay = plugin.getConfig().getInt("firework.delay", 0) * 20;
        fireworkType = plugin.getConfig().getString("firework.type");
        fireworkPower = plugin.getConfig().getInt("firework.power", 1);

        //TITLE
        titleTitleMSG = colored(plugin.getConfig().getString("join.title.title"));
        titleSubtitleMSG = colored(plugin.getConfig().getString("join.title.subtitle"));
        titleFadeIN = plugin.getConfig().getInt("join.title.fadeIn", 1) * 20;
        titleStay = plugin.getConfig().getInt("join.title.Stay", 2) * 20;
        titleFadeOUT = plugin.getConfig().getInt("join.title.fadeOut", 1) * 20;

        //Position
        if (plugin.getConfig().getString("spawn.location").isEmpty()) {
            spawnLoc = null;
        } else {
            spawnLoc = (Location) plugin.getConfig().get("spawn.location");
        }

        //MESSAGES
        joinM = colored(plugin.getConfig().getString("join.message"));
        quitM = colored(plugin.getConfig().getString("leave.message"));
        NoPermMSG = colored(plugin.getMessageConfig().getConfig().getString("message.NoPerm"));
        kickMSG = colored(plugin.getMessageConfig().getConfig().getString("message.IpNotMatch"));
        InvArg = colored(plugin.getMessageConfig().getConfig().getString("message.InvalidArg"));
        InvSubArg = colored(plugin.getMessageConfig().getConfig().getString("message.InvalidSubArg"));
        PlReload = colored(plugin.getMessageConfig().getConfig().getString("message.PlReload"));
        CmdUsage = colored(plugin.getMessageConfig().getConfig().getString("message.CmdUsage"));
        IpL = colored(plugin.getMessageConfig().getConfig().getString("message.IpLocked"));
        PFound = colored(plugin.getMessageConfig().getConfig().getString("message.PlayerFound"));
        PNotFound = colored(plugin.getMessageConfig().getConfig().getString("message.PlayerNotFound"));
        NotPlayer = colored(plugin.getMessageConfig().getConfig().getString("message.NotPlayer"));
        IpRemove = colored(plugin.getMessageConfig().getConfig().getString("message.IpRemove"));
        IPSave = colored(plugin.getMessageConfig().getConfig().getString("message.IpSave"));
        ManIP = colored(plugin.getMessageConfig().getConfig().getString("message.ManualIP"));
        NotLocked = colored(plugin.getMessageConfig().getConfig().getString("message.NotLocked"));
        UnLPlayer = colored(plugin.getMessageConfig().getConfig().getString("message.UnLockedPlayer"));
        PReset = colored(plugin.getMessageConfig().getConfig().getString("message.PlayerReset"));
        NotNumb = colored(plugin.getMessageConfig().getConfig().getString("message.NotNumber"));
        Empty = colored(plugin.getMessageConfig().getConfig().getString("message.Empty"));
        Enabled = colored(plugin.getMessageConfig().getConfig().getString("message.Enabled"));
        Disabled = colored(plugin.getMessageConfig().getConfig().getString("message.Disabled"));
        Updated = colored(plugin.getMessageConfig().getConfig().getString("message.Updated"));
        SpawnSave = colored(plugin.getMessageConfig().getConfig().getString("message.SpawnSaved"));
        NoSpawn = colored(plugin.getMessageConfig().getConfig().getString("message.EmptySpawn"));
    }

    private String colored(String string) {
        return plugin.getFacilitisAPI().strUtils.colored(string);
    }
}
